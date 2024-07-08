package com.EyEmilyKim.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	// String -> java.util.Date 변환
	public static Date stringToDate(String dateString) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		return dateFormat.parse(dateString);
	}
	
	// java.util.Date -> java.sql.Timestamp 변환
	public static Timestamp dateToTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}
	
//String -> java.sql.Timestamp 직접 변환
	public static Timestamp stringToTimestamp(String dateString) throws ParseException {
		Date utilDate = stringToDate(dateString);
		return dateToTimestamp(utilDate);
	}
	
}
