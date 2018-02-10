package com.github.mpetersen.lrpg.value;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

@SuppressWarnings("serial")
class ValueFormat extends DecimalFormat {

  ValueFormat(final int digitCount, final boolean withSign) {
    super(createFormat(digitCount, withSign), new DecimalFormatSymbols(Locale.US));
    setParseBigDecimal(true);
  }

  private static String createFormat(final int digitCount, final boolean withSign) {
    final StringBuilder sb = new StringBuilder("0");
    if (digitCount > 0) {
      sb.append('.');
      for (int i = 0; i < digitCount; i++) {
        sb.append('0');
      }
    }
    final String format = sb.toString();
    if (withSign) {
      return "+" + format + ";-" + format;
    }
    return format;
  }
}
