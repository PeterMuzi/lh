package com.huhui.util;

import java.util.HashMap;
import java.util.Map;

public class FIXparse {
	
	private static String split ="\1";
	public static Map parse(String data){
		String[] items = data.split(split);
		Map map=new HashMap();
		for(int i=0;i<items.length;i++){
			String[] temp=items[i].split("=");
			if(temp!=null&&temp.length==2){
				map.put(temp[0], temp[1]);
			}
		}
		return map;
	}
}
