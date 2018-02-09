package com.github.mpetersen.lrpg.value;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

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

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInputDecimalPointNoFraction() {
    new Value("1.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInputTooManyDecimalPoints() {
    new Value("1.2.3");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInputIllegalCharacters() {
    new Value("ab.12");
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
