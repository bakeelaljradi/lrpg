package com.github.mpetersen.lrpg.config;

import com.github.mpetersen.lrpg.template.Template;

import java.util.ArrayList;
import java.util.Collection;

public class Preset {
  private Template name;
  private String template;
  private Collection<Setting> settings = new ArrayList<>();

  public Preset name(final String name) {
    this.name = new Template(name);
    return this;
  }

  public Preset template(final String template) {
    this.template = template;
    return this;
  }

  public Preset setting(Setting setting) {
    settings.add(setting);
    return this;
  }

  public Template getName() {
    return name;
  }

  public String getTemplate() {
    return template;
  }

  public Collection<Setting> getSettings() {
    return settings;
  }
}
