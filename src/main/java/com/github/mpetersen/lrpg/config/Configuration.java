package com.github.mpetersen.lrpg.config;

import java.util.ArrayList;
import java.util.Collection;

public class Configuration {

  private final Collection<Preset> presets = new ArrayList<>();

  public Configuration preset(final Preset preset) {
    presets.add(preset);
    return this;
  }

  public Collection<Preset> getPresets() {
    return presets;
  }
}
