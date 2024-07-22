package com.telegram.duolingo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class PathSectioned {
  @JsonProperty("units")
  private List<Units> units;
}
