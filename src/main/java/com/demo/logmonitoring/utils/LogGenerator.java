package com.demo.logmonitoring.utils;

import com.demo.logmonitoring.kafka.Receiver;
import com.demo.logmonitoring.kafka.Sender;
import com.demo.logmonitoring.model.Log;
import com.demo.logmonitoring.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class LogGenerator {
    private static final Logger logger = LoggerFactory.getLogger(LogService.class);

    @Autowired
    private Receiver receiver;

    @Autowired
    private Sender sender;

    public Log generateLogs(String serverName, LogLevel logLevel) {

        String logMessage = "[" + serverName + "]" + " Hello from - " + serverName;

        switch (logLevel) {
            case INFO:
                logger.info(logMessage);
                break;

            case DEBUG:
                logger.debug(logMessage);
                break;

            case WARN:
                logger.warn(logMessage);
                break;
            case ERROR:
                logger.error(logMessage);
                break;

            case TRACE:
                logger.trace(logMessage);
                break;
        }


        sender.send(logMessage);

        try {
            receiver.getLatch().await(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new Log(serverName, logLevel.name());
    }

}
