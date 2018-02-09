package com.github.mpetersen.lrpg.template;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class Template {
  private final String source;
  private String result;

  public Template(final Path path) {
    this(new String(readBytes(path), Charset.defaultCharset()));
  }

  private static byte[] readBytes(final Path path) {
    try {
      return Files.readAllBytes(path);
    } catch (final IOException e) {
      throw new IllegalArgumentException("Unable to read template: " + path, e);
    }
  }

  public Template(final String source) {
    this.source = source;
    reset();
  }

  public void reset() {
    result = source;
  }

  public void replace(final String name, final Object value) {
    result = result.replace("$" + name, String.valueOf(value));
  }

  @Override
  public String toString() {
    return result;
  }
}
