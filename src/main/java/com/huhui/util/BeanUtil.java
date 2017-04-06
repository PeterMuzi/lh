package com.huhui.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

public class BeanUtil {
	
	public static final int  findObjectEqual=0;
	public static final int  findObjectEndWith=1;
	public static final int  findObjectNotEqual=2;
	public static  void	setProperty(Object o,String property,Object value){
		if(o==null){
			return;
		}
		try {
			BeanUtils.setProperty(o,property,value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Object	getProperty(Object o,String property){
		try {
			return BeanUtils.getProperty(o,property);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public static List findList(List list,String property,String value){
		return  findList( list, property, value,0);
	}
	
	public static List findList(List list,String[] property,String value[]){
		if(list==null||list.size()==0){
			return null;
		}
		List res=new ArrayList();
		for(Object o:list){
			res.add(o);
		}
		
		for(int i=0;i<property.length;i++){
			res=findList(res, property[i], value[i],0);
		}
		return  res;
	}
	
	public static List findList(List list,String property,String value,int valuetype){
		if(list==null||list.size()==0||value==null){
			return list;
		}
		List res=new ArrayList();
		for(int i=0;i<list.size();i++){
			Object o=list.get(i);
			Object v;
			try {
				v = PropertyUtils.getProperty(o, property);
				if(v!=null){
					if(valuetype==0&&value.equals(v.toString())){
						res.add(o);
					}else if(valuetype==1&&v.toString().endsWith(value)){
						res.add(o);
					}else if(valuetype==2&&!value.equals(v.toString())){
						res.add(o);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	
	public static Object findObject(List list,String[] property,String value[]){
		List res=findList( list, property, value);
		if(res!=null&&res.size()!=0){
			return res.get(0);
		}
		return null;
	}
	
	public static Object findObject(List list,String property,String value,int valuetype){
		if(list==null||list.size()==0||property==null||value==null){
			return null;
		}
		for(int i=0;i<list.size();i++){
			Object o=list.get(i);
			Object v;
			try {
				v = PropertyUtils.getProperty(o, property);
				if(v!=null){
					if(valuetype==0&&value.equals(v.toString())){
						return o;
					}else if(valuetype==1&&v.toString().endsWith(value)){
						return o;
					}else if(valuetype==2&&!value.equals(v.toString())){
						return o;
					}
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static Object findObject(List list,String property,String value){
		return findObject( list, property, value,0);
	}
	
	public static int findObjectIndex(List list,String property,String value,int valuetype){
		if(list==null||list.size()==0||property==null||value==null){
			return -1;
		}
		for(int i=0;i<list.size();i++){
			Object o=list.get(i);
			Object v;
			try {
				v = PropertyUtils.getProperty(o, property);
				if(v!=null){
					if(valuetype==0&&value.equals(v.toString())){
						return i;
					}else if(valuetype==1&&v.toString().endsWith(value)){
						return i;
					}else if(valuetype==2&&!value.equals(v.toString())){
						return i;
					}
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return -1;
	}
}
