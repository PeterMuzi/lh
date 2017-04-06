package com.huhui.util;

import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;

public class BindUtil {
	public static void bind(Object o,HttpServletRequest request){
		if(o==null){
			return;
		}
		Field[] fields=o.getClass().getDeclaredFields();
		if(fields!=null){
			for(Field f:fields){
				String value=request.getParameter(f.getName());
				if(value!=null){
					BeanUtil.setProperty(o, f.getName(), value);
				}
				//System.out.println(f.getName());
			}
		}
	}
}
