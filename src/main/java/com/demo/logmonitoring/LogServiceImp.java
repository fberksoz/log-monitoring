package com.demo.logmonitoring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LogServiceImp implements LogService {

    @Autowired
    LogRepository logRepository;

    @Override
    public Iterable<LogModel> findAll() {
        return logRepository.findAll();
    }

    @Override
    public LogModel save(LogModel log) {
        return logRepository.save(log);
    }

    @Override
    public Optional<LogModel> findByID(String id) {
        return logRepository.findById(id);
    }

}
