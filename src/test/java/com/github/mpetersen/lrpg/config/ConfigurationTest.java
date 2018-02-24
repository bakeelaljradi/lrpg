package com.github.mpetersen.lrpg.config;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConfigurationTest {

  @Test
  public void test() {
    Configuration config = new Configuration()
      .preset(
        new Preset()
          .name("00.$[%02d]i Temperature")
          .setting(new Setting()
            .key("WhiteBalance = \"Custom\", Temperature")
            .min("2000")
            .max("12000")
            .increment("500"))
          .template("basic.template"))
      .preset(
        new Preset()
          .name("01.$[%02d]i Tint")
          .setting(new Setting()
            .key("WhiteBalance = \"Custom\", Tint")
            .min("-100")
            .max("100")
            .increment("10"))
          .template("basic.template"));

    assertThat(config.getPresets().size(), is(2));
  }
}
