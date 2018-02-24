package com.github.mpetersen.lrpg.value;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValueTest {
  @Test
  public void testCreateWithoutDecimalPoint() {
    new Value("123");
  }

  @Test
  public void testCreateWithDecimalPoint() {
    new Value("12.34");
  }

  @Test
  public void testCreateWithNegativeNumber() {
    new Value("-1.23");
  }

  @Test
  public void testInvalidInputDecimalPointNoFraction() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Value("1.");
    });
  }

  @Test
  public void testInvalidInputTooManyDecimalPoints() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Value("1.2.3");
    });
  }

  @Test
  public void testInvalidInputIllegalCharacters() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Value("ab.12");
    });
  }

  @Test
  public void testToString() {
    assertThat(new Value("0400").toString(), is("400"));
    assertThat(new Value("0400.00").toString(), is("400.00"));
  }

  @Test
  public void testInc() {
    final Value value = new Value("-1.00");
    value.inc(new Value("0.10"));
    assertThat(value.toString(), is("-0.90"));
  }

  @Test
  public void testIsLessThanOrEquals() {
    final Value value = new Value("-1.00");
    final Value max = new Value("1.00");
    assertTrue(value.isLessThanOrEquals(max));
  }

  @Test
  public void testWithSign() {
    final Value value = new Value("-1.00");
    value.inc(new Value("2"));
    assertThat(value.toString(), is("1.00"));
  }
}
