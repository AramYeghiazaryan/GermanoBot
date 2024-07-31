package com.telegram.mongo.repository;

import com.telegram.mongo.entity.LexemeEntity;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomLexemeRepository {

  LexemeEntity getRandomWordsFromDictionary();
}
