package com.demo.logmonitoring;

import java.util.Optional;

public interface LogService {


    Iterable<LogModel> findAll();

    LogModel save(LogModel log);

    Optional<LogModel> findByID(String id);

}
