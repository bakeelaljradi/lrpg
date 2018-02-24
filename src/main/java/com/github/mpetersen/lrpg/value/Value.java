package com.github.mpetersen.lrpg.value;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Value {
  private static final Pattern REGEX_INPUT = Pattern.compile("^((-)?\\d+)(\\.(\\d+))?$");
  private final ValueFormat format;
  private BigDecimal value;

  /**
   * Creates a new value. The input String defines the actual value and format of this instance.
   *
   * The format can contain any number, a leading minus sign and a decimal separator ".".
   *
   * @param input The input String.
   */
  public Value(final String input) {
    final Matcher m = REGEX_INPUT.matcher(input);
    if (m.matches()) {
      final String digits = m.group(4);
      final int digitCount = digits == null ? 0 : digits.length();
      final boolean withSign = false;
      format = new ValueFormat(digitCount, withSign);
      try {
        value = (BigDecimal) format.parse(input);
      } catch (final ParseException e) {
        throw new RuntimeException(e);
      }
    }
    else {
      throw new IllegalArgumentException("Illegal format: " + input);
    }
  }

  @Override
  public String toString() {
    return format.format(value);
  }

  public void inc(final Value other) {
    value = value.add(other.value);
  }

  public boolean isLessThanOrEquals(final Value other) {
    final int result = value.compareTo(other.value);
    return result == -1 || result == 0;
  }
}
