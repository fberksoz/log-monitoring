package com.demo.logmonitoring.service;

import com.demo.logmonitoring.model.Log;

import java.util.Optional;

public interface LogService {


    Iterable<Log> findAll();

    Log save(Log log);

    Optional<Log> findByID(String id);

}
