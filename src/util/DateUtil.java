/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 
 * @author Leon
 */
public class DateUtil {

	public final SimpleDateFormat millisecFormat = new SimpleDateFormat(
			"H:m:s.S dd/MM/yy");
	public final SimpleDateFormat sleepFormat = new SimpleDateFormat(
			"H:m dd.MM.yy");
	public final SimpleDateFormat YearMonthDay_DateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");
	public final SimpleDateFormat birthdayFormat = new SimpleDateFormat(
			"dd/MM/yyyy");
	public static final TimeZone UTC = TimeZone.getTimeZone("UTC");

	public DateUtil() {
		millisecFormat.setTimeZone(UTC);
		sleepFormat.setTimeZone(UTC);
		YearMonthDay_DateFormat.setTimeZone(UTC);

	}
	public Long convert_to_long(String source, SimpleDateFormat format) throws ParseException
	{
		synchronized (format) {
			Date d = format.parse(source);
			return d.getTime();
		}
	}
	public Date convert(String source, SimpleDateFormat format)
			throws ParseException {
		synchronized (format) {
			Date d = format.parse(source);
			return d;
		}
	}
	public String format(String source, SimpleDateFormat format)
			throws ParseException {
		synchronized (format) {
			String d = format.format(source);
			return d;
		}
	}
	public String format(Date date, SimpleDateFormat format)
			throws ParseException {
		synchronized (format) {
			String d = format.format(date);
			return d;
		}
	}

}
