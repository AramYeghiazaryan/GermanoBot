package com.telegram.duolingo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserResponse {

  @JsonProperty("currentCourse")
  private CurrentCourse currentCourse;

  @JsonProperty("username")
  private String username;
}
