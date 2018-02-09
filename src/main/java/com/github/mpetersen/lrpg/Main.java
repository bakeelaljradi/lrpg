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
    Files.lines(Paths.get("templates", "settings.batch")).map(line -> line.split("\t")).forEach(input -> {
      final Template name = new Template(input[0]);
      final String setting = input[1];
      final Value start = new Value(input[2]);
      final Value max = new Value(input[3]);
      final Value increment = new Value(input[4]);
      final String tmplFile = input[5];

      final Template template = new Template(Paths.get("templates", tmplFile));

      int i = 0;
      for (final Value value = start; value.isLessThanOrEquals(max); value.inc(increment)) {
        name.reset();
        template.reset();

        name.replace("i", String.format("%02d", i++));

        template.replace("value", value);
        template.replace("setting", setting);
        template.replace("name", name);

        final String preset = template.toString();

        try {
          Files.write(Paths.get("presets", name + "_" + value + ".lrtemplate"), preset.getBytes());
        } catch (final IOException e) {
          throw new IllegalStateException(e);
        }
      }
    });
  }
}
