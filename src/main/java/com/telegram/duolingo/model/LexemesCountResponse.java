package com.telegram.duolingo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LexemesCountResponse {

  @JsonProperty("lexemeCount")
  private Integer lexemeCount;
}
