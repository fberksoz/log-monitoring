package com.demo.logmonitoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LogGenerator {
    private static final Logger logger = LoggerFactory.getLogger(LogService.class);

    public LogModel generateLogs(String serverName, LogLevel logLevel) {

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

        return new LogModel(serverName, logLevel.name());
    }

}
