package com.telegram.mongo.repository;

import com.telegram.duolingo.model.Lexeme;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LexemeRepository extends MongoRepository<Lexeme,String> {

}
