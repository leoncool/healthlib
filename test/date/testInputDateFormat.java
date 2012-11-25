package date;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import util.DateUtil;

public class testInputDateFormat {
public static void main(String args[]) throws ParseException
{
	System.out.println(Arrays.asList(TimeZone.getDefault()));
	Date date = DateUtil.YearMonthDay_DateFormat
			.parse("2012-11-24");
	System.out.println("DateRequest:"+date);
	Calendar calStart = Calendar.getInstance();
	Calendar calEnd = Calendar.getInstance();
	calStart.setTime(date);
	calEnd.setTime(date);
	calStart.set(Calendar.HOUR_OF_DAY, 0);
	calStart.set(Calendar.MINUTE, 0);
	long start = calStart.getTimeInMillis();
	calEnd.set(Calendar.HOUR_OF_DAY, 23);
	calEnd.set(Calendar.MINUTE, 59);
	long end = calEnd.getTimeInMillis();
	System.out.println("Date Request start"+calStart.getTime());
	System.out.println("Date Request end"+calEnd.getTime());
	System.out.println("using Date Request:"+start+" "+end);
	
 Date testDate=new Date(Long.parseLong("1353733200000"));
 Date testDate2=new Date();
 
 Long valueLong=1353733200000L; 
 testDate2.setTime(valueLong);
 System.out.println(testDate);
 System.out.println(DateUtil.millisecFormat.format(testDate));
 System.out.println(testDate2);
 System.out.println(DateUtil.millisecFormat.format(testDate2));
}
}
