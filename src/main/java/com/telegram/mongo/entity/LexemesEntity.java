package com.telegram.mongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "Lexemes")
public class LexemesEntity {
  @Id
  private String lexemeId;
  private String word;
  private String translation;
  private boolean isNew;
}
