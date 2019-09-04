package com.davidredondo.util;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateUtils {
	
	public static final Integer SECONDS_IN_A_DAY = 86400;
	
	private static final String JSON_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	private static final String JSON_HOUR_SEPARATOR = ":";
	
	private static final Integer SECONDS_IN_HOUR = 3600;
	
	private static final Integer MINUTES_IN_HOUR = 60;
	
	private static DateTimeFormatter getJsonDateTimeFormatter() {
		return DateTimeFormat.forPattern(JSON_DATE_FORMAT);
	}
	
	public static DateTime getDateTimeFromJsonDateFormat(String date) {
		return DateTime.parse(date, getJsonDateTimeFormatter());
	}
	
	public static String getJsonDateFormatFromDateTime(DateTime dateTime) {
		return dateTime.toString(getJsonDateTimeFormatter());
	}
	
	public static Seconds getSecondsFromJsonHourFormat(String hour) {
		List<Integer> splitedHour = Arrays.stream(hour.split(JSON_HOUR_SEPARATOR)).map(Integer::valueOf).collect(Collectors.toList());
		return Minutes.minutes(splitedHour.get(0) * MINUTES_IN_HOUR + splitedHour.get(1)).toStandardSeconds();
	}
	
	public static Integer secondsToHours(Integer seconds) {
		return seconds / SECONDS_IN_HOUR;
	}
}
