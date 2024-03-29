package com.demo.logmonitoring.controller;

import com.demo.logmonitoring.utils.LogLevel;
import com.demo.logmonitoring.model.Log;
import com.demo.logmonitoring.service.LogService;
import com.demo.logmonitoring.utils.LogGenerator;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Controller
public class LoggerController {
    private static final Logger logger = LoggerFactory.getLogger(LoggerController.class);

    @Autowired
    LogGenerator logGenerator;

    @Autowired
    LogService logService;

    private final CountDownLatch latch = new CountDownLatch(3);

    @GetMapping("/")
    public String home(Model model) throws InterruptedException {

        for(int i = 0; i < 50000; i++) {
            logService.save(logGenerator.generateLogs("İstanbul", LogLevel.INFO));
            logService.save(logGenerator.generateLogs("Tokyo", LogLevel.WARN));
            logService.save(logGenerator.generateLogs("Moskow", LogLevel.TRACE));
            logService.save(logGenerator.generateLogs("Beijing", LogLevel.DEBUG));
            logService.save(logGenerator.generateLogs("London", LogLevel.ERROR));

            Thread.sleep(100);

        }

        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5);
        model.addAttribute("num", data);
        return "index";
    }

    @KafkaListener(topics = "myTopic")
    public void listen(ConsumerRecord<?, ?> cr) throws Exception {
        System.out.println(cr.toString());
        latch.countDown();
    }

    @GetMapping("/api")
    public Iterable<Log> studentV2() {
        return logService.findAll();
    }

}
