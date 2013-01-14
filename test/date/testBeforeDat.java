package date;

import java.util.Date;

public class testBeforeDat {
public static void main(String args[])
{
	Date now=new Date();
	long expireLong=1000*60;
	Date expireDate=new Date(now.getTime()-expireLong);
	if(expireDate.before(new Date()))
	{
		System.out.println("before");
	}
	else{
		System.out.println("After");
	}
	Date testDate=new Date();
	testDate.setTime(1358121540000L);
	System.out.println(testDate.toString());
}
}
