package com.telegram.duolingo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Pagination {

  @JsonProperty("totalLexemes")
  private Integer totalLexemes;
  @JsonProperty("requestedPageSize")
  private Integer requestedPageSize;
  @JsonProperty("pageSize")
  private Integer pageSize;
  @JsonProperty("previousStartIndex")
  private Integer previousStartIndex;
  @JsonProperty("nextStartIndex")
  private Integer nextStartIndex;

}
