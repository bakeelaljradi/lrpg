package com.github.mpetersen.lrpg;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
    final Path templatesPath = Paths.get("templates");
    final Path presetsPath = Paths.get("presets");
    if (!Files.exists(presetsPath)) {
      Files.createDirectory(presetsPath);
    }

    Files.lines(templatesPath.resolve("settings.batch")).map(line -> line.split("\t")).forEach(input -> {
      final Template name = new Template(input[0]);
      final String setting = input[1];
      final Value start = new Value(input[2]);
      final Value max = new Value(input[3]);
      final Value increment = new Value(input[4]);
      final String tmplFile = input[5];

      final Template template = new Template(templatesPath.resolve(tmplFile));

      int i = 0;
      for (final Value value = start; value.isLessThanOrEquals(max); value.inc(increment)) {
        name.reset();
        name.replace("i", String.format("%02d", i++));

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
  }
}
