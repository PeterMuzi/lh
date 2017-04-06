package com.huhui.util.excell;

import java.util.List;
import java.util.Map;

import com.huhui.util.Arith;
import com.huhui.util.BeanUtil;
import com.huhui.util.MapUtil;
/*
 * 值取两列相加
 */
public class AddColumnHander extends BaseColumnHander{
	
	public String[] keys1=null;
	public String[] keys2=null;
	public AddColumnHander(String columnName,String property, String[] keys1, String[] keys2) {
		super(columnName, null);
		this.property = property;
		this.keys1 = keys1;
		this.keys2 = keys2;
	}

	
	@Override
	public String hand(List<Map> datas,Map map) {
		value="0";
		//key1的值去找key2
		Map map2=(Map)BeanUtil.findObject(datas, keys2, MapUtil.getStrings(map, keys1));
		if(map2==null){
			value="0";
		}else{
			double d1=MapUtil.getDouble(map, property);
			double d2=MapUtil.getDouble(map2, property);
			value=String.valueOf(Arith.add(d1, d2));
		}
		return value;
	}
	
}
