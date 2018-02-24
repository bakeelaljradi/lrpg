package com.github.mpetersen.lrpg;

import com.github.mpetersen.lrpg.config.Configuration;
import com.github.mpetersen.lrpg.config.Preset;
import com.github.mpetersen.lrpg.config.Setting;
import com.github.mpetersen.lrpg.template.Template;
import com.github.mpetersen.lrpg.value.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
  /**
   * Runs the program.
   *
   * @param args
   * @throws IOException
   */
  public static void main(final String[] args) throws IOException {
    final Path templatesPath = Paths.get("templates");
    final Path presetsPath = Paths.get("presets");
    if (!Files.exists(presetsPath)) {
      Files.createDirectory(presetsPath);
    }

    Configuration config = new Configuration();
    Files.lines(templatesPath.resolve("config.csv")).map(line -> line.split(";")).forEach(input -> {
      config.preset(new Preset()
        .name(input[0])
        .setting(new Setting()
          .key(input[1])
          .min(input[2])
          .max(input[3])
          .increment(input[4]))
        .template(input[5]));
    });

    config.getPresets().forEach(preset -> {
      final Template template = new Template(templatesPath.resolve(preset.getTemplate()));
      Template name = preset.getName();

      preset.getSettings().forEach(setting -> {
        int i = 0;
        for (final Value value = setting.getMin(); value.isLessThanOrEquals(setting.getMax()); value.inc(setting.getIncrement())) {
          name.reset();
          name.replace("i", i++);

          template.reset();
          template.replace("value", value);
          template.replace("setting", setting);
          template.replace("name", name);

          try {
            Files.write(presetsPath.resolve(name + "_" + value + ".lrtemplate"), template.toString().getBytes());
          } catch (final IOException e) {
            throw new IllegalStateException(e);
          }
        }
      });
    });
  }
}
