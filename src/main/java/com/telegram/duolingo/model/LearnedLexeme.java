package com.telegram.duolingo.model;

import java.util.List;
import lombok.Data;

@Data
public class LearnedLexeme {
  private List<String> translations;
  private String audioURL;
  private Boolean isNew;
}
