package com.demo.logmonitoring;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<LogModel, String> {
}
