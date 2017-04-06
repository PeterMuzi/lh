package com.huhui.util.excell;

import java.util.List;
import java.util.Map;

import com.huhui.util.Arith;
import com.huhui.util.BeanUtil;
import com.huhui.util.MapUtil;

public class AddDoubleHander extends BaseColumnHander{
	
	public double added=0;
	public AddDoubleHander(String columnName,String property,double added) {
		super(columnName, property);
		this.added = added;
	}

	
	@Override
	public String hand(List<Map> datas,Map map) {
		double value=Double.parseDouble(super.hand(datas, map));
		value=Arith.add(value, added);
		return String.valueOf(value);
	}
	
}
