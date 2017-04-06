package com.huhui.util.trade;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.huhui.util.DateUtil;

public class TradeTimeConfig {
	
	private static TradeTimeConfig instance=null;
	
	private long forex_from=0;
	private long forex_to=0;
	public static TradeTimeConfig getInstance() {
		if(instance==null){
			instance=new TradeTimeConfig();
		}
		return instance;
	}
	/*
	 * 
	 */
	public boolean forexTrading(){
		
		long time=System.currentTimeMillis();
		if(forex_from==0||forex_to==0||time>forex_to){
			forex_from=getTime("yyyyMMddHHmm",getThisWeekMon("yyyyMMdd")+"0259");
			forex_to=getTime("yyyyMMddHHmm",getThisWeekSat("yyyyMMdd")+"0501");
		}
		if(time>forex_to){
			forex_from=forex_from+7*24*3600*1000;
			forex_to=forex_to+7*24*3600*1000;
		}
		if(time>=forex_from&&time<=forex_to){
			return true;
		}else{
			return false;
		}
	}
	
	public static String getThisWeekMon(String format){
		try{
			int mondayPlus=0;
			Calendar cd = Calendar.getInstance();
			// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
			int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK); // 因为按中国礼拜一作为第一天所以这里减1
			//System.out.println(cd.get(Calendar.DAY_OF_WEEK));
			if (dayOfWeek == 1) {
				mondayPlus = -6;
			} else if(dayOfWeek==2){
				mondayPlus = 0;
			} else if(dayOfWeek==3){
				mondayPlus = -1;
			}else if(dayOfWeek==4){
				mondayPlus = -2;
			}else if(dayOfWeek==5){
				mondayPlus = -3;
			}else if(dayOfWeek==6){
				mondayPlus = -4;
			}else if(dayOfWeek==7){
				mondayPlus = -5;
			}
			GregorianCalendar currentDate = new GregorianCalendar();
			currentDate.add(GregorianCalendar.DATE, mondayPlus);
			SimpleDateFormat sd=new SimpleDateFormat(format,Locale.getDefault());
			return sd.format(currentDate.getTime());
		}catch(Exception e){
			return "";
		}
	}
	public static String getThisWeekSat(String format){
		try{
			int mondayPlus=0;
			Calendar cd = Calendar.getInstance();
			// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
			int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK); // 因为按中国礼拜一作为第一天所以这里减1
			//System.out.println(cd.get(Calendar.DAY_OF_WEEK));
			if (dayOfWeek == 1) {
				mondayPlus = -1;
			} else if(dayOfWeek==2){
				mondayPlus = 5;
			} else if(dayOfWeek==3){
				mondayPlus = 4;
			}else if(dayOfWeek==4){
				mondayPlus = 3;
			}else if(dayOfWeek==5){
				mondayPlus = 2;
			}else if(dayOfWeek==6){
				mondayPlus = 1;
			}else if(dayOfWeek==7){
				mondayPlus = 0;
			}
			GregorianCalendar currentDate = new GregorianCalendar();
			currentDate.add(GregorianCalendar.DATE, mondayPlus);
			SimpleDateFormat sd=new SimpleDateFormat(format,Locale.getDefault());
			return sd.format(currentDate.getTime());
		}catch(Exception e){
			return "";
		}
	}
	public static long getTime(String dateFormat,String date){
		try{
		 SimpleDateFormat sd=new SimpleDateFormat(dateFormat,Locale.getDefault());
		 return sd.parse(date).getTime();
		}catch(Exception e){
			return System.currentTimeMillis();
		}
	}
	public static void main(String[] arg){
		System.out.println(getThisWeekMon("yyyyMMdd")+"0259");
		System.out.println(getThisWeekSat("yyyyMMdd")+"0501");
		
		System.out.println(TradeTimeConfig.getInstance().forexTrading());
		System.out.println(DateUtil.chDay(TradeTimeConfig.getInstance().forex_from) );
		System.out.println(DateUtil.chDay(System.currentTimeMillis()) );
		System.out.println(DateUtil.chDay(TradeTimeConfig.getInstance().forex_to) );
	}
}
