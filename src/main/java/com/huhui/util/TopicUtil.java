package com.huhui.util;

import java.util.ArrayList;
import java.util.List;

public class TopicUtil {
	public static String[] topic(String src) {
		if(src==null||src.length()<=2){
			return null;
		}
		int fromindex=src.indexOf("#");
		int toindex=src.indexOf("#",fromindex+1);
		if(toindex==-1){
			return null;
		}
		List<String> res=new  ArrayList();
		while(fromindex>=0&&toindex>=0){
			res.add(src.substring(fromindex+1, toindex));
			fromindex=src.indexOf("#",toindex+1);
			toindex=src.indexOf("#",fromindex+1);
		}
		if(res.size()==0){
			return null;
		}
		String[] datas=new String[res.size()];
		return res.toArray(datas);
	}
	
	public static String topicUrled(String src) {
		String[] topics=topic(src);
		if(topics==null){
			return src;
		}
		for(String t:topics){
			src=src.replaceAll("#"+t+"#", "<a href=\"/search?type=t&amp;keyword="+t+"\">#"+t+"#</a>");
		}
		return src;
	}
	//<a href="/search?type=t&amp;keyword=K0000352">K0000352(2)</a>
	public static void main(String[] args) {
		String src="#是大方#的ssd士大夫";
		String[] res=topic( src);
		
		System.out.println(((String)res[0]).length());
		System.out.println(topicUrled(src));
		res=topic(topicUrled( src));
		System.out.println(((String)res[0]).length());
	}
}
