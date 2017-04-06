package com.huhui.util.excell;

import java.util.List;
import java.util.Map;
/*
 * 默认值列
 */
public class DirectSetColumnHander extends BaseColumnHander{

	public DirectSetColumnHander(String columnName, String value) {
		super(columnName, null);
		super.value=value;
	}

	@Override
	public String hand(List<Map> datas,Map map) {
		return value;
	}
	
}
