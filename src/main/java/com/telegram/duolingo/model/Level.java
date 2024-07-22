package com.telegram.duolingo.model;

import lombok.Data;

@Data
public class Level {
  private String id;
  private String state;
  private int finishedSessions;
  private PathLevelMetadata pathLevelMetadata;
  private int totalSessions;
  private String debugName;
  private boolean hasLevelReview;
  private String type;
  private String subtype;
  private boolean isInProgressSequence;
  private Object dailyRefreshInfo; // Assuming this can be any type, adjust as needed
  private int absoluteNodeIndex;
}
