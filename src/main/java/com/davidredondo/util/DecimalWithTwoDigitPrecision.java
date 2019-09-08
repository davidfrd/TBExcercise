package com.davidredondo.util;

import java.io.IOException;
import java.util.Locale;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DecimalWithTwoDigitPrecision extends JsonSerializer<Double> {

	@Override
	public void serialize(Double value, JsonGenerator generator, SerializerProvider serializers) throws IOException {
		generator.writeNumber(String.format(Locale.US, "%.2f", value));
	}
}