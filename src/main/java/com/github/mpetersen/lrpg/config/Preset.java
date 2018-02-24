package com.github.mpetersen.lrpg.config;

import com.github.mpetersen.lrpg.template.Template;
import com.github.mpetersen.lrpg.value.Value;

import java.util.ArrayList;
import java.util.Collection;

public class Preset {

  private Template name;
  private String setting;
  private Value min;
  private Value max;
  private Value increment;
  private Template template;
  private Collection<Setting> settings = new ArrayList<>();

  public Preset name(final String name) {
    this.name = new Template(name);
    return this;
  }

  public Preset setting(final String setting) {
    this.setting = setting;
    return this;
  }

  public Preset min(final String min) {
    this.min = new Value(min);
    return this;
  }

  public Preset max(final String max) {
    this.max = new Value(max);
    return this;
  }

  public Preset increment(final String increment) {
    this.increment = new Value(increment);
    return this;
  }

  public Preset template(final String template) {
    this.template = new Template(template);
    return this;
  }

  public Preset setting(Setting setting) {
    settings.add(setting);
    return this;
  }
}
