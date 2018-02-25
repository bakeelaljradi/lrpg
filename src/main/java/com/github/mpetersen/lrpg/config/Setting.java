package com.github.mpetersen.lrpg.config;

import com.github.mpetersen.lrpg.value.Value;

public class Setting {
  private String name;
  private Value min;
  private Value max;
  private Value increment;

  public Setting name(String name) {
    this.name = name;
    return this;
  }

  public Setting min(String min) {
    this.min = new Value(min);
    return this;
  }

  public Setting max(String max) {
    this.max = new Value(max);
    return this;
  }

  public Setting increment(String increment) {
    this.increment = new Value(increment);
    return this;
  }

  public String getName() {
    return name;
  }

  public Value getMin() {
    return min;
  }

  public Value getMax() {
    return max;
  }

  public Value getIncrement() {
    return increment;
  }
}
