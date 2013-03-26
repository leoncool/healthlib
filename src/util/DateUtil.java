/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/**
 * 
 * @author Leon
 */
public class DateUtil {

	public final SimpleDateFormat millisecFormat = new SimpleDateFormat(
			"H:m:s.S dd/MM/yy");
	public final SimpleDateFormat isoformat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss.SSS");
	public final SimpleDateFormat sleepFormat = new SimpleDateFormat(
			"H:m dd.MM.yy");
    public final SimpleDateFormat utcFormat = new SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss'Z'");
	public final SimpleDateFormat YearMonthDay_DateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");
	public final SimpleDateFormat birthdayFormat = new SimpleDateFormat(
			"dd/MM/yyyy");
	public static final TimeZone UTC = TimeZone.getTimeZone("UTC");
	public final String YearMonthDay_DateFormat_pattern="yyyy-MM-dd";
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
	public Date convert(String source, String format)
			throws ParseException {
		synchronized (format) {
			DateTime dt = DateTime.parse(source, DateTimeFormat.forPattern(format));
			return dt.toDate();
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
