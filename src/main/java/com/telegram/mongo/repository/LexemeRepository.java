package com.telegram.mongo.repository;

import com.telegram.mongo.entity.LexemeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LexemeRepository extends MongoRepository<LexemeEntity, String> {
}
