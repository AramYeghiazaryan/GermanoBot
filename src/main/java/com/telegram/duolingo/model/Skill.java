package com.telegram.duolingo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class Skill {
  @JsonProperty("accessible")
  private boolean accessible;

  @JsonProperty("experimentIds")
  private List<String> experimentIds;

  @JsonProperty("experimentalLessons")
  private List<String> experimentalLessons;

  @JsonProperty("explanation")
  private Explanation explanation;

  @JsonProperty("finalLevelTimeLimit")
  private int finalLevelTimeLimit;

  @JsonProperty("finishedLessons")
  private int finishedLessons;

  @JsonProperty("finishedLevels")
  private int finishedLevels;

  @JsonProperty("hasLevelReview")
  private boolean hasLevelReview;

  @JsonProperty("hasFinalLevel")
  private boolean hasFinalLevel;

  @JsonProperty("iconId")
  private int iconId;

  @JsonProperty("id")
  private String id;

  @JsonProperty("lastLessonPerfect")
  private boolean lastLessonPerfect;

  @JsonProperty("lessons")
  private int lessons;

  @JsonProperty("levels")
  private int levels;

  @JsonProperty("name")
  private String name;

  @JsonProperty("perfectLessonStreak")
  private int perfectLessonStreak;

  @JsonProperty("shortName")
  private String shortName;

  @JsonProperty("skillType")
  private String skillType;

  @JsonProperty("strength")
  private String strength;

  @JsonProperty("tipsAndNotes")
  private String tipsAndNotes;

  @JsonProperty("urlName")
  private String urlName;
}
