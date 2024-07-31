package com.telegram.mongo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "#{@environment.getProperty('spring.data.mongodb.collections.lexeme-collection-name')}")
public class LexemeEntity {

  @Id
  private Long lexemeId;
  private String word;
  private String translation;
  private Boolean isNew;
  private String audioURL;
}
