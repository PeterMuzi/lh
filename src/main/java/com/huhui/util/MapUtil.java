package com.huhui.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapUtil {
	
	public static boolean getBoolean(Map  para,String key){
		if(para==null||!para.containsKey(key)){
			return false;
		}
		Object o=para.get(key);
		if(o==null){
			return false;
		}
		if("true".equals(o.toString())||"1".equals(o.toString())){
			return true;
		}
		return false;
	}

	public static int getInt(Map  para,String key){
		return (int)getDouble(  para, key);
	}
	public static String getString(Map  para,String key){
		if(para==null||!para.containsKey(key)){
			return "";
		}
		Object o=para.get(key);
		if(o==null){
			return "";
		}
		return o.toString();
	}
	public static String[] getStrings(Map  para,String[] keys){
		String[] res=new String[keys.length];
		for(int i=0;i<keys.length;i++){
			res[i]=getString(  para, keys[i]);
		}
		return res;
	}
	public static double getDouble(Map  para,String key){
		if(para==null||!para.containsKey(key)){
			return 0;
		}
		Object o=para.get(key);
		if(o==null){
			return 0;
		}
		return Double.parseDouble(o.toString());
	}
	public static Map copy(Map  para){
		if(para==null){
			return null;
		}
		Map res=new HashMap();
		Set<Map.Entry<String,Object>> set = para.entrySet();
	    for (Iterator<Map.Entry<String, Object>> it = set.iterator(); it.hasNext();) {
	         Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
	         Object value=entry.getValue();
	         res.put(entry.getKey(), value);
	    }
	    return res;
	}
	
	public static String mapToUrl(Map  para){
		if(para==null){
			return "";
		}
		
		Set<Map.Entry<String,Object>> set = para.entrySet();
		StringBuffer sb=new StringBuffer();
	    for (Iterator<Map.Entry<String, Object>> it = set.iterator(); it.hasNext();) {
	         Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
	         Object value=entry.getValue();
	         if(value!=null&&value.toString().length()!=0){
	        	 sb.append("&"+entry.getKey()).append("=").append(value);
	         }
	    }
	    return sb.toString();
	}
	
	public static Map urlToMap(String  para){
		Map map=new HashMap();
		if(para==null){
			return map;
		}
		
		String[] datapairs=para.split("&");
		for(String data:datapairs){
			String[] keyvalue=data.split("=");
			if(keyvalue!=null&&keyvalue.length==2){
				map.put(keyvalue[0], keyvalue[1]);
			}
		}
		return map;
	}
	
	public static Map getMap(String[] paraNames,String[] values,String defaultValue){
		if(paraNames==null){
			return null;
		}
		Map map=new HashMap();
		int i=0;
		for(String para:paraNames){
			String value=values[i];
			if((value==null||value.length()==0)){
				map.put(para,defaultValue );
			}else{
				map.put(para,value );
			}
			i++;
		}
		return map;
	}
	
	public static boolean containAll(Map map,List<String> list){
		if(map==null||map.size()==0||list==null||list.size()==0){
			return false;
		}
		for(String p:list){
			if(!map.containsKey(p)){
				return false;
			}
		}
		return true;
	}
	
	public static List<String> getKeys(Map map){
		List<String> res=new ArrayList();
		if(map==null||map.size()==0){
			return res;
		}
		Set<Map.Entry<String, List>> set = map.entrySet();
		
	    for (Iterator<Map.Entry<String, List>> it = set.iterator(); it.hasNext();) {
	         Map.Entry<String, List> entry = (Map.Entry<String, List>) it.next();
	         res.add(entry.getKey());
	    }
	    return res;
	}
	
	public static List<List<Map>> groupBy(List<Map> maps,String[] keys,int having){
		
		if(maps==null||maps.size()==0){
			return null;
		}
		
		List<List<Map>> res=new ArrayList();
		Map res_temp=new HashMap();
		for(Map map:maps){
			String[] values=getStrings(map,keys);
			String key=StringUtil.arrayToString(values, ",");
			if(!res_temp.containsKey(key)){
				res_temp.put(key, new ArrayList());
			}
			((List)(res_temp.get(key))).add(map);
		}
		Set<Map.Entry<String, List>> set = res_temp.entrySet();
	    for (Iterator<Map.Entry<String, List>> it = set.iterator(); it.hasNext();) {
	         Map.Entry<String, List> entry = (Map.Entry<String, List>) it.next();
	         List counts=entry.getValue();
	         if(having==0||counts.size()>=having){
	        	 res.add(counts);
	         }
	    }
	    return res;
	}
	
	public static void main(String[] arg) {
		Map map=urlToMap("access_token=1f67bf71fd32540096c922935c8439f1&expires_in=604800&openid=EE2303B5905D7F2FCC3CECB5F725CB45&openkey=F2A4E9FB56627EEA67B6F60472164BF7");
		System.out.print(map);
	}
}
