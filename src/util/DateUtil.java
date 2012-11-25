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

    public static final SimpleDateFormat millisecFormat = new SimpleDateFormat("H:m:s.S dd/MM/yy");
    public static final SimpleDateFormat sleepFormat = new SimpleDateFormat("H:m dd.MM.yy");
    public static final SimpleDateFormat YearMonthDay_DateFormat=new SimpleDateFormat("yyyy-MM-dd");
   
    public static final TimeZone UTC = TimeZone.getTimeZone("UTC");
    public DateUtil()
    {
    	millisecFormat.setTimeZone(UTC);
    	sleepFormat.setTimeZone(UTC);
    	YearMonthDay_DateFormat.setTimeZone(UTC);
    	
    }
    public static long parseMillisecFormatToLong(String str) throws ParseException {
        Date date = millisecFormat.parse(str);
        return date.getTime();
    }

    public static String toMillisecFormatString(Date date) throws ParseException {
        return millisecFormat.format(date);
    }
}
