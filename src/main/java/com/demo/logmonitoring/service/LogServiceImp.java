package com.demo.logmonitoring.service;

import com.demo.logmonitoring.model.Log;
import com.demo.logmonitoring.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LogServiceImp implements LogService {

    @Autowired
    LogRepository logRepository;

    @Override
    public Iterable<Log> findAll() {
        return logRepository.findAll();
    }

    @Override
    public Log save(Log log) {
        return logRepository.save(log);
    }

    @Override
    public Optional<Log> findByID(String id) {
        return logRepository.findById(id);
    }

}
