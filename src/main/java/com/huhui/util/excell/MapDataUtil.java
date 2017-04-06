package com.huhui.util.excell;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huhui.util.MapUtil;

public class MapDataUtil {
	
	/*
	 * datas.property{"M","B","H"} * muls{"M","B","H"} except=true:不要自己就相当于多1倍数据
	 * resultProperty:将结果{"M","B","H"}放属性中，如"回程航班适用舱位"
	 */
			
	public static void mul(List<Map> datas,String property,String[] muls,boolean except,String resultProperty){
		if(datas==null||datas.size()==0){
			return;
		}
		List<Map> adds=new ArrayList();
		for(Map map:datas){
			boolean isfirst=true;
			for(String mul:muls){
				if(!MapUtil.getString(map, property).equals(mul)&&except||MapUtil.getString(map, property).equals(mul)&&!except){
					if(isfirst){
						map.put(resultProperty, mul);
						isfirst=false;
					}else{
						Map temp=MapUtil.copy(map);
						temp.put(resultProperty, mul);
						adds.add(temp);
					}
				}
			}
		}
		datas.addAll(adds);
	}
}
