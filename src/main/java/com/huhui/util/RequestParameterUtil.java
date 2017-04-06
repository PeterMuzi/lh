package com.huhui.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 * @author Zark
 */
public class RequestParameterUtil {

    private static boolean test=false;
    
    public static String getParam(HttpServletRequest request){
    	Enumeration en = request.getParameterNames();
    	StringBuffer sb=new StringBuffer();
		while (en.hasMoreElements()) {
		    String paramName = (String) en.nextElement();
		    String paramValue = request.getParameter(paramName);
		    sb.append(paramName).append("=").append(paramValue).append("&");
		}
		return sb.toString();
    }

	/*
	 *max return  fields key map
	 */
	public static Map getRequestParameterMap(HttpServletRequest request,boolean removeEmptyValue){
		
		Map map=new HashMap();
		Enumeration en = request.getParameterNames();
		while (en.hasMoreElements()) {
		    String paramName = (String) en.nextElement();
		    String paramValue = request.getParameter(paramName);
		    if(removeEmptyValue&&(paramValue==null||paramValue.trim().length()==0)){
		    	;
		    }else{
		    	map.put(paramName, paramValue);
		    }
		}
		return map;
	}
	
	public static String[][] getInsertParameter(HttpServletRequest request,List<String> excludes){
		
		Enumeration en = request.getParameterNames();
		int paraCount=0;
		while (en.hasMoreElements()) {
		    String paramName = (String) en.nextElement();
		    if(excludes==null||!excludes.contains(paramName)){
		    	paraCount++;
		    }
		}
		if(paraCount==0){
			return null;
		}
		String[][] res=new String[2][paraCount];
		
		
		int index=0;
		en = request.getParameterNames();
		while (en.hasMoreElements()) {
		    String paramName = (String) en.nextElement();
		    String paramValue = request.getParameter(paramName);
		    if(excludes==null||!excludes.contains(paramName)){
		    	res[0][index]=paramName;
			    res[1][index]=paramValue;
			    index++;
		    }
		}
		return res;
	}
	
	public static Object[] getUpdateParameter(HttpServletRequest request,String ids,List<String> excludes){
		if(ids==null||ids.length()==0){
			return null;
		}
		
		Enumeration en = request.getParameterNames();
		int index=0;
		
		String[] ids__=ids.split(",");
		String[][] ids_=new String[2][ids__.length];
		for(String id:ids__){
			ids_[0][index]=id;
			ids_[1][index]=request.getParameter(id);
			index++;
		}
		
		index=0;

		while (en.hasMoreElements()) {
		    String paramName = (String) en.nextElement();
		    if(excludes==null||!excludes.contains(paramName)){
				index++;
		    }
		}
		String[][] columns=new String[2][index];
		
		index=0;
		en = request.getParameterNames();
		while (en.hasMoreElements()) {
		    String paramName = (String) en.nextElement();
		    String paramValue = request.getParameter(paramName);
		    if(excludes==null||!excludes.contains(paramName)){
		    	columns[0][index]=paramName;
			    columns[1][index]=paramValue;
			    index++;
		    }
		}
		return new Object[]{columns,ids_};
	}
	
	public static List getRequestParameterNames(HttpServletRequest request){
		
		List list=new ArrayList();
		Enumeration en = request.getParameterNames();
		while (en.hasMoreElements()) {
			list.add((String) en.nextElement());
		}

		return list;
	}

	public static Map getRequestParameterMap(HttpServletRequest request,String[] paraNames,String[] columns,String defaultValue){
		if(paraNames==null){
			return null;
		}
		Map map=new HashMap();
		int i=0;
		for(String para:paraNames){
			String value=request.getParameter(para);
			if((value==null||value.length()==0)){
				map.put(columns[i],defaultValue );
			}else{
				map.put(columns[i],value );
			}
			
			i++;
		}
		return map;
	}
	
	
	/*
	 * columns: a,b,c
	 * data: 1~2~3~~4~5~6
	 * linesplit: ~~
	 * fieldsplit:~
	 */
	public static List<Map> getRequestParameterMap(String columns,String data,String linesplit,String fieldsplit,String defaultValue){
		if(data==null||columns==null){
			return null;
		}
		List<Map> maps=new ArrayList();
		String[] paraNames=columns.split(",");
		String[] lines=data.split(linesplit);
		for(String para:lines){
			String[] fieldvalues=para.split(fieldsplit);
			maps.add(MapUtil.getMap(paraNames, fieldvalues, defaultValue));
		}
		return maps;
	}
}
