package com.huhui.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 时间工具类
 * @author Zark
 */
public class DateUtil {
	
	static protected SimpleDateFormat englishsdf=new SimpleDateFormat("EEE MMM d HH:mm:ss 'UTC' Z yyyy", Locale.US);
	static protected SimpleDateFormat chsdf=new SimpleDateFormat("yyyyMMdd-HH:mm:ss", Locale.US);
	static protected SimpleDateFormat usa=new SimpleDateFormat("yyyyMMdd-HH:mm:ss", Locale.US);

	static{
		englishsdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		chsdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
	}
	
	public static String getFixTime(long time){
		SimpleDateFormat sd=new SimpleDateFormat("yyyyMMdd-HH:mm:ss",Locale.getDefault());
		return sd.format(new Date(time))+".000";
	}
	
	public static Date toDay(long time){
		SimpleDateFormat sd=new SimpleDateFormat("yyyyMMdd",Locale.getDefault());
		try {
			return sd.parse(sd.format(new Date(time)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static long getIBHistoryTime(String time){
		String format="yyyyMMdd";
		if(time.length()==10){
			format="yyyyMMddHH";
		}else if(time.length()==12){
			format="yyyyMMddHHmm";
		}else if(time.length()==14){
			format="yyyyMMddHHmmss";
		}else if(time.length()==17){
			format="yyyyMMddHHmmssSSS";
		}
		SimpleDateFormat sd=new SimpleDateFormat(format,Locale.getDefault());
		try {
			return sd.parse(time).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/*
	 * 是否周3，收3天利息
	 */
	public static boolean isWednesday(){
		Calendar c = Calendar.getInstance();
		try {
			c.clear();
			c.setTime(new Date());
		} catch (Exception exp) {
			System.out.println("Parse Exception in DateUtil: isWednesday :" + exp);
			return false;
		}
		int weekDay = c.get(Calendar.DAY_OF_WEEK);
		if (weekDay == 4)
			return true;

		return false;
	}
	/*
	 * 1=Sun,7=Sat
	 */
	public static int weekday(){
		Calendar c = Calendar.getInstance();
		try {
			c.clear();
			c.setTime(new Date());
		} catch (Exception exp) {
			System.out.println("Parse Exception in DateUtil: isWednesday :" + exp);
			return -1;
		}
		return c.get(Calendar.DAY_OF_WEEK);
	}
	
	/*
	 * 1=Sun,7=Mon
	 */
	public static int weekdayCh(){
		Calendar c = Calendar.getInstance();
		try {
			c.clear();
			String day=chsdf.format(new Date(System.currentTimeMillis()));
			c.setTime(usa.parse(day));
		} catch (Exception exp) {
			System.out.println("Parse Exception in DateUtil: isWednesday :" + exp);
			return -1;
		}
		return c.get(Calendar.DAY_OF_WEEK);
	}
	
	public static Date getFirstDayOfMonth(){
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM",Locale.getDefault());
		String date=sd.format(new Date())+"-01";
		sd=new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
		try {
			return sd.parse(date);
		} catch (ParseException e) {
			return new Date();
		}
	}
	
	public static String getTime(long time){
		if(time==0){
			return "-:-:-";
		}
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault());
		return sd.format(new Date(time));
	}
	
	public static String englishDay(long time){
		return englishsdf.format(new Date(time));
	}
	
	public static String chDay(long time){
		return chsdf.format(new Date(time));
	}
	
    public static int getDayCountBetweenTwoDay(long fromDay,long toDay){
        if(fromDay==0||toDay==0){
          return 0;
        }
        Date dDate1 = toDay(fromDay);
        Date dDate2 = toDay(toDay);
        long a=1000*60*60*24;
        long m=dDate2.getTime()-dDate1.getTime();
        return  (int)(m/a);
      }
    
	public static int year(){
		Calendar c = Calendar.getInstance();
		try {
			c.clear();
			c.setTime(new Date());
		} catch (Exception exp) {
			System.out.println("Parse Exception in DateUtil: isWednesday :" + exp);
			return -1;
		}
		return c.get(Calendar.YEAR);
	}
	
	/**
	 * @param dateFormat:yyyy MM月dd日HH:mm       
	 * @date:2012 1月7日01:46
	 */
	public static long getTime(String dateFormat,String date){
		try{
		 SimpleDateFormat sd=new SimpleDateFormat(dateFormat,Locale.getDefault());
		 return sd.parse(date).getTime();
		}catch(Exception e){
			return System.currentTimeMillis();
		}
	}
	
	public static long getEnTime(String dateFormat,String date){
		try{
		 SimpleDateFormat sd=new SimpleDateFormat(dateFormat,Locale.ENGLISH);
		 return sd.parse(date).getTime();
		}catch(Exception e){
			return System.currentTimeMillis();
		}
	}
	
	//today is 2005-11-4, if beforeDay=-30 return 2005-10-04
    public static long getFromDateString(int beforeDay){
      GregorianCalendar gregorianCalendar=new GregorianCalendar(Locale.US);//一定要new ,否则在原来的时间基础上增加
      gregorianCalendar.add(GregorianCalendar.DATE, beforeDay);
      Date beforeday = gregorianCalendar.getTime();
      return beforeday.getTime();
    }
	
    public static long getDateString(long time,int beforeDay){
        GregorianCalendar gregorianCalendar=new GregorianCalendar(Locale.US);//一定要new ,否则在原来的时间基础上增加
        gregorianCalendar.setTimeInMillis(time);
        gregorianCalendar.add(GregorianCalendar.DATE, beforeDay);
        Date beforeday = gregorianCalendar.getTime();
        return beforeday.getTime();
    }
    
	public static String getTime(long time,String format){
		SimpleDateFormat sd=new SimpleDateFormat(format,Locale.getDefault());
		return sd.format(new Date(time));
	}
	
	public static String getTime(long time,String format,Locale locale){
		SimpleDateFormat sd=new SimpleDateFormat(format,locale);
		sd.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		return sd.format(new Date(time));
	}
	
	public	static void main(String[] arg){
		//SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
		//try {
			//System.out.println(getDayCountBetweenTwoDay(dateFormatter.parse("2011-09-09 01:01:01").getTime(),dateFormatter.parse("2011-09-09 23:23:01").getTime()));
		//} catch (ParseException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		//System.out.println(isWednesday());
		//System.out.println(englishDay(System.currentTimeMillis()));
		//System.out.println(weekdayCh());
		/*System.out.println(year());
		System.out.println(getTime(getDateString(getIBHistoryTime("20120215012".substring(0,8)),1),"yyyyMMdd"));
		System.out.println(Long.parseLong("20120215012")+1);
		System.out.println(DateUtil.chDay(System.currentTimeMillis()).substring(0,8));*/
		
		//chsdf.parse(source)
		
		/*System.out.print(getFixTime(getEnTime("EE MMM dd HH:mm:ss yyyy","Fri Feb 17 15:00:27 2014")));
		System.out.print(getFixTime(getEnTime("EE MMM dd HH:mm:ss yyyy","Fri Feb 17 15:00:27 2014")));
		System.out.print(getTime(getDateString(getTime("yyyy-MM-dd","2014-03-22"),-1),"yyyy-MM-dd"));*/
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINESE);
		System.out.print(new Date(1467695944942L));
		System.out.print(getTime(getTime("yyyyMMddHH:mm:ss.SSS","2014031408:53:37.704")));
	}
}
