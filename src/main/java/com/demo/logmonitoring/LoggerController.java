package com.demo.logmonitoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class LoggerController {
    private static final Logger logger = LoggerFactory.getLogger(LoggerController.class);

    @Autowired
    LogService logService;

    @GetMapping("/")
    public String home(Model model) throws InterruptedException {

        for(int i = 0; i < 50000; i++) {
            logService.generateLogs("İstanbul", LogLevel.INFO);
            logService.generateLogs("Tokyo", LogLevel.WARN);
            logService.generateLogs("Moskow", LogLevel.TRACE);
            logService.generateLogs("Beijing", LogLevel.DEBUG);
            logService.generateLogs("London", LogLevel.ERROR);

            Thread.sleep(100);
        }

        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5);

        model.addAttribute("num", data);


        return "index";
    }
}
