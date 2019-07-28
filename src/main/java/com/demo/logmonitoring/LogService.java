package com.demo.logmonitoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LogService {
    private static final Logger logger = LoggerFactory.getLogger(LogService.class);

    public void generateLogs(String serverName, LogLevel logLevel) {

        String logMessage = "[" + serverName + "]" + " Hello from - " + serverName;

        switch (logLevel) {
            case INFO:
                logger.info(logMessage);
            case DEBUG:
                logger.debug(logMessage);
            case WARN:
                logger.warn(logMessage);
            case ERROR:
                logger.error(logMessage);
            case TRACE:
                logger.trace(logMessage);
        }
    }
}
