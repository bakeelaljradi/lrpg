package com.github.mpetersen.lrpg.config;

public class Setting {
  private String key;
  private String min;
  private String max;
  private String increment;

  public Setting key(String key) {
    this.key = key;
    return this;
  }

  public Setting min(String min) {
    this.min = min;
    return this;
  }

  public Setting max(String max) {
    this.max = max;
    return this;
  }

  public Setting increment(String increment) {
    this.increment = increment;
    return this;
  }
}
