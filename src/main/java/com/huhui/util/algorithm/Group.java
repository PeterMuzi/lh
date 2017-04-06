package com.huhui.util.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Group {
	public static void main(String[] arg){
		//the src must be order by
		String[] src=new String[]{"0","1","2","3","4","5","6","7","8","9"};//
		//System.out.print(zhuhe(src,6));
		System.out.print(zhuhe(src,7).size());
	}
	
	public static List<String> zhuhe(String[] total,int resnumcount){
		List<String> res=new ArrayList();
		for(String s:total){
			res.add(s);
		}
		for(int m=1;m<resnumcount;m++){
			res=mul(res,total);
		}
		
		List<String> temp=new ArrayList();
		for(String s:res){
			if(s.split(";").length!=resnumcount)
			temp.add(s);
		}
		
		res.removeAll(temp);
		return res;
	}
	
	private static List<String> mul(List<String> src,String[] total){
		List<String> res=new ArrayList();
		for(int i=0;i<src.size();i++){
			String[] temp=src.get(i).split(";");
			int t=Integer.parseInt(temp[temp.length-1]);
			for(int j=t+1;j<=total.length-1;j++){
				res.add(src.get(i)+";"+total[j]);
			}
		}
		
		return res;
	}
}
