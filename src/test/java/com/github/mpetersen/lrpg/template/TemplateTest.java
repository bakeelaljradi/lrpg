package com.github.mpetersen.lrpg.template;


import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TemplateTest {

  @Test
  public void testReplace() {
    final Template template = new Template("Hello, $name!");
    template.replace("name", "World");
    assertThat(template.toString(), is("Hello, World!"));
    template.reset();
    template.replace("name", "Horst");
    assertThat(template.toString(), is("Hello, Horst!"));
    template.reset();
    assertThat(template.toString(), is("Hello, $name!"));
  }

  @Test
  public void testMultiReplace() {
    final Template template = new Template("$a, $b");
    template.replace("a", "123");
    template.replace("b", "abc");
    assertThat(template.toString(), is("123, abc"));
  }

  @Test
  public void testWithFormat() {
    final Template template = new Template("I am $[%02d]age years old.");
    template.replace("age", 3);
    assertThat(template.toString(), is("I am 03 years old."));
  }

}
