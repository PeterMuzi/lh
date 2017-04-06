package com.huhui.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;

public class ListUtil {
	public static Set toSet(List list){
		if(list==null){
			return null;
		}
		Set set=new HashSet();
		for(Object o:list){
			set.add(o);
		}
		return set;
	}
	public static List<String> getListOfObject(List map,String property){
		if(map==null){
			return new ArrayList();
		}

		List<String> list=new ArrayList();
		Object data=null;
		for(Object o:map){
			data=BeanUtil.getProperty(o, property);
			if(data!=null){
				list.add(data.toString());
			}
		}
		return list; 
	}
	public static List getList(List<Map> map,String property){
		if(map==null){
			return new ArrayList();
		}
		List list=new ArrayList();
		for(Map o:map){
			Object value=o.get(property);
			if(value!=null&&value.toString().length()!=0)list.add(value);
		}
		return list;
	}
	
	public static Map group(List list,String property){
		if(list==null||list.size()==0){
			return null;
		}
		Map<String,List> res=new HashMap();
		List list_=null;
		for(Object o:list){
			String value=null;
			try {
				value = PropertyUtils.getProperty(o, property).toString();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
			if(!res.containsKey(value)){
				list_=new ArrayList();
				res.put(value, list_);
			}else{
				list_=res.get(value);
			}
			list_.add(o);
		}
		return res;
	}
}
