package com.huhui.util.excell;

import java.util.List;
import java.util.Map;
/*
 * 直接取property的值设置到columnName
 */
public class SubstringColumnHander  extends BaseColumnHander{
	public int fromIndex=0;
	public int toIndex=0;
	public  String hand(List<Map> datas,Map map){
		String value=super.hand(datas, map);
		if(!"".equals(value)){
			return value.substring(fromIndex, toIndex);
		}
		return value;
	}
	public SubstringColumnHander(String columnName, String property,int fromIndex,int toIndex) {
		super(columnName,property);
		this.fromIndex=fromIndex;
		this.toIndex=toIndex;
	}
}
