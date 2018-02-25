package com.github.mpetersen.lrpg.generator;

import com.github.mpetersen.lrpg.config.Configuration;
import com.github.mpetersen.lrpg.template.Template;
import com.github.mpetersen.lrpg.value.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PresetGenerator {
  private final Configuration config;
  private final Path templatesPath;
  private final Path presetsPath;

  public PresetGenerator(final Configuration config) throws IOException {
    this.config = config;
    this.templatesPath = config.getTemplatesPath();
    this.presetsPath = config.getPresetsPath();
    if (!Files.exists(presetsPath)) {
      Files.createDirectory(presetsPath);
    }
  }

  public void generate() {
    config.getPresets().forEach(preset -> {
      final Template presetTemplate = new Template(templatesPath.resolve(preset.getTemplate()));
      final Template presetName = preset.getName();

      preset.getSettings().forEach(setting -> {
        int i = 0;
        for (final Value value = setting.getMin(); value.isLessThanOrEquals(setting.getMax()); value.inc(setting.getIncrement())) {
          presetName.reset();
          presetName.replace("i", i++);

          presetTemplate.reset();
          presetTemplate.replace("value", value);
          presetTemplate.replace("setting", setting.getName());
          presetTemplate.replace("name", presetName);

          try {
            Files.write(presetsPath.resolve(presetName + "_" + value + ".lrtemplate"), presetTemplate.toString().getBytes());
          } catch (final IOException e) {
            throw new IllegalStateException(e);
          }
        }
      });
    });
  }
}
