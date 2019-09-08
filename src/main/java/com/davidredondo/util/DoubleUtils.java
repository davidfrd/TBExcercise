package com.davidredondo.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DoubleUtils {

	public static Double withPrecision(Double value, Integer precision) {
		return BigDecimal.valueOf(value).setScale(precision, RoundingMode.UP).doubleValue();
	}

}
