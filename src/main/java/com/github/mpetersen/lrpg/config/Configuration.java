package com.github.mpetersen.lrpg.config;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

public class Configuration {

  private final Collection<Preset> presets = new ArrayList<>();
  private Path templatesPath;
  private Path presetsPath;

  public Configuration preset(final Preset preset) {
    presets.add(preset);
    return this;
  }

  public Configuration templatesPath(final String templatesPath) {
    this.templatesPath = Paths.get(templatesPath);
    return this;
  }

  public Configuration presetsPath(final String presetsPath) {
    this.presetsPath = Paths.get(presetsPath);
    return this;
  }

  public Collection<Preset> getPresets() {
    return presets;
  }

  public Path getTemplatesPath() {
    return templatesPath;
  }

  public Path getPresetsPath() {
    return presetsPath;
  }
}
