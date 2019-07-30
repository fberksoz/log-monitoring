package com.demo.logmonitoring.utils;

import com.demo.logmonitoring.model.Log;
import com.demo.logmonitoring.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LogGenerator {
    private static final Logger logger = LoggerFactory.getLogger(LogService.class);

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

        return new Log(serverName, logLevel.name());
    }

}
