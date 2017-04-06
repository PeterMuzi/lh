package com.huhui.util.excell;

import java.util.List;
import java.util.Map;
/*
 * 直接取property的值设置到columnName
 */
public class BaseColumnHander {
	public String columnNameEn=null;
	public String columnName=null;
	public String property=null;
	public String value=null;
	public  String hand(List<Map> datas,Map map){
		value=null;
		if(map.get(property)!=null){
			value=map.get(property).toString();
		}
		
		if(value==null){
			value="";
		}
		return value;
	}
	public BaseColumnHander(String columnName, String property) {
		super();
		this.columnName = columnName;
		this.property = property;
	}
	public String getColumnNameEn() {
		return columnNameEn;
	}
	public void setColumnNameEn(String columnNameEn) {
		this.columnNameEn = columnNameEn;
	}
	
}
