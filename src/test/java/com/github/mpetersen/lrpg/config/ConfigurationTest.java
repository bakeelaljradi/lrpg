package com.github.mpetersen.lrpg.config;

import org.junit.jupiter.api.Test;

public class ConfigurationTest {

  @Test
  public void test() {
    new Configuration()
      .preset(
        new Preset()
          .name("Temperature")
          .setting(new Setting()
            .key("WhiteBalance = \"Custom\", Temperature")
            .min("2000")
            .max("12000")
            .increment("500"))
          .template("basic.template"))
      .preset(
        new Preset()
          .name("Tint")
          .setting(new Setting()
            .key("WhiteBalance = \"Custom\", Tint")
            .min("-100")
            .max("100")
            .increment("10"))
          .template("basic.template"));
  }
}
