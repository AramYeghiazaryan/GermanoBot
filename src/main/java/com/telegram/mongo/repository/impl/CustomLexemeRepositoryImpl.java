package com.telegram.mongo.repository.impl;

import com.telegram.mongo.entity.LexemeEntity;
import com.telegram.mongo.repository.CustomLexemeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomLexemeRepositoryImpl implements CustomLexemeRepository {

  @Value("${spring.data.mongodb.collections.lexeme-collection-name}")
  private String lexemeCollectionName;

  private final MongoTemplate mongoTemplate;

  public LexemeEntity getRandomWordsFromDictionary() {
    Aggregation aggregation = Aggregation.newAggregation(
        Aggregation.sample(1)
    );

    AggregationResults<LexemeEntity> results = mongoTemplate.aggregate(aggregation,
        lexemeCollectionName, LexemeEntity.class);
    return results.getUniqueMappedResult();
  }
}
