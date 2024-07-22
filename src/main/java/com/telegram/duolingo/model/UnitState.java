package com.telegram.duolingo.model;

import lombok.Getter;

public enum UnitState {
  PASSED("passed"),
  ACTIVE("active");

  @Getter
  private final String state;

  UnitState(String state) {
    this.state = state;
  }

  public static boolean isValidState(String state) {
    for (UnitState unitState : UnitState.values()) {
      if (unitState.state.equals(state)) {
        return true;
      }
    }
    return false;
  }
}
