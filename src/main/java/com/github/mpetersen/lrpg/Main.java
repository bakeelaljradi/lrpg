package com.github.mpetersen.lrpg;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.github.mpetersen.lrpg.template.Template;
import com.github.mpetersen.lrpg.value.Value;

public class Main {
  /**
   * Runs the program.
   *
   * @param args
   * @throws IOException
   */
  public static void main(final String[] args) throws IOException {
    Files.lines(Paths.get("templates", "settings.batch")).forEach(line -> {
      final String[] input = line.split(" ");
      final String name = input[0];
      final String setting = input[1];
      final String start = input[2];
      final String max = input[3];
      final String increment = input[4];
      final String tmplFile = input[5];

      final Template template = new Template(Paths.get("templates", tmplFile));

      int i = 0;
      for (final Value value = new Value(start); value.isLessThanOrEquals(new Value(max)); value
          .inc(new Value(increment))) {
        template.replace("value", value.toString());
        template.replace("setting", setting);
        template.replace("name", name);
        template.replace("i", String.format("%02d", i++));
        final String preset = template.toString();
        template.reset();
        try {
          Files.write(Paths.get("presets", name + "_" + value + ".lrtemplate"), preset.getBytes());
        } catch (final IOException e) {
          throw new IllegalStateException(e);
        }
      }
    });
  }
}
