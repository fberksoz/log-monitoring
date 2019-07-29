package com.demo.logmonitoring;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class LogModel {

    @Id
    private String id;
    private String serverName;
    private String logLevel;

    public LogModel(String id, String serverName, String logLevel) {
        super();
        this.id = id;
        this.serverName = serverName;
        this.logLevel = logLevel;
    }

    public LogModel(String serverName, String logLevel) {
        super();
        this.serverName = serverName;
        this.logLevel = logLevel;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}
