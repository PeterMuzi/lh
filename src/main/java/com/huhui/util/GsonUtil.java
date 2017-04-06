package com.huhui.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GsonUtil {
	
	public static Gson gson=new Gson();
	
	
	public static Map getMap(String src_,String charset){
		String src=FileUtil.getFileContent(src_, charset);
		if(src!=null){
			return gson.fromJson(src, new TypeToken<Map>(){}.getType()); 
		}
		return null;
	}
	public static List<Map> getMapList(String file,String charset){
		String src=FileUtil.getFileContent(file, charset);
		if(src!=null){
			return gson.fromJson(src, new TypeToken<List<Map>>(){}.getType()); 
		}
		return null;
	}
	
	public static void saveToFile(String src,Object content){
		String res=gson.toJson(content);
		FileUtil.write(src, res);
	}
	public static void saveToFile(String src,Object content,String charset){
		String res=gson.toJson(content);
		FileUtil.saveToFile(src, res, charset);
	}
	public static List<Map> getMapList(String src){
		
		String res=FileUtil.getFileContent(src);
		if(res!=null){
			return gson.fromJson(res, new TypeToken<List<Map>>(){}.getType()); 
		}
		return null;
	}
	
	public static Map getMap(String src){
		
		String res=FileUtil.getFileContent(src);
		if(res!=null){
			return gson.fromJson(res, new TypeToken<Map>(){}.getType()); 
		}
		return null;
	}
	
	public static Map getMap(File src,String charset){
		
		String res=FileUtil.getFileContent(src,charset);
		if(res!=null){
			return gson.fromJson(res, new TypeToken<Map>(){}.getType()); 
		}
		return null;
	}
	
	public static void main1(String[] arg) {
		Map map=new HashMap();
		map.put("id", "1");
		map.put("nae","dd");
		List<Map> maps=new ArrayList();
		maps.add(map);
		map=new HashMap();
		map.put("id","2");
		map.put("nae","eee");
		maps.add(map);
		saveToFile("d:\\aa.txt",maps);
		
		maps=getMapList("d:\\aa.txt");
		int i=0;
		i++;
	}
	
	public static void main2(String[] arg) {

		List<Map> maps=getMapList("d:\\aa.txt");
		int i=0;
		i++;
	}
	
	public static void main(String[] arg) {

		Map maps=getMap("D:\\tools\\常用服务器\\数据备份研究\\LDFX.txt");
		List<Map> accounts=(List)maps.get("mt4_account");
		for(Map map:accounts){
			System.out.println((int)Double.parseDouble(map.get("id").toString()));
		}
		Map strategyinfo=(Map)maps.get("strategyinfo");
		List<Map> groups=(List)strategyinfo.get("groups");
		int i=0;
		i++;
	}
	
	public static void main3(String[] arg) {

		Map map=new HashMap();
		map.put("id", "1");
		map.put("nae","dd");
		
		saveToFile("d:\\aa.txt",map);
		
		map=getMap("d:\\aa.txt");
		int i=0;
		i++;
	}
}
