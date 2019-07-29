package com.demo.logmonitoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.List;

@Controller
public class LoggerController {
    private static final Logger logger = LoggerFactory.getLogger(LoggerController.class);

    @Autowired
    LogGenerator logGenerator;

    @Autowired
    LogService logService;

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

    @GetMapping("/api")
    public Iterable<LogModel> studentV2() {
        return logService.findAll();
    }

}
