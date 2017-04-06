package com.huhui.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 时间格式化工具类
 * @author Steven
 */
public class TimeFormat 
{
	private static SimpleDateFormat sdfForSpanFormat;
	static
	{
		sdfForSpanFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdfForSpanFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
	}
	
	/**
	* 按与当前时间点的距离格式化时间
	* @param time，需要格式化的时间
	* @return，格式化结果
	*/
	public static String SpanFormat(long time){
		return SpanFormat(time,true);
	}
	public static String SpanFormat(long time,boolean needTime)
	{
		long timeSpan = System.currentTimeMillis() - time;
		if(timeSpan < 1000)
		{
			return "1秒前";
		}
		else if(timeSpan < 60000)
		{
			return String.valueOf(timeSpan / 1000) + "秒前";
		}
		else if(timeSpan < 3600000)
		{
			return String.valueOf(timeSpan / 60000) + "分钟前";
		}
		else if(timeSpan < 86400000)
		{
			return String.valueOf(timeSpan / 3600000) + "小时前";
		}
		else if(timeSpan < 86400000)
		{
			return String.valueOf(timeSpan / 86400000) + "天前";
		}
		else
		{
			if(needTime){
				return sdfForSpanFormat.format(new Date(time));
			}else{
				return String.valueOf(timeSpan / 86400000) + "天前";
			}
		}
	}
}
