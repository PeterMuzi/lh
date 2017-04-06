package com.huhui.util;

import java.util.ArrayList;
import java.util.List;

public class Random {
	
	/*
	 * 多少位的随机数
	 */
	public	static synchronized String getRandom(int size){
		java.util.List<Integer> list=new java.util.ArrayList<Integer>();
		java.util.Random ran=new java.util.Random();
		StringBuffer sb=new StringBuffer();
		while(list.size()<size)
		{
			int n=ran.nextInt(10);
			if(!list.contains(n)){
				list.add(n);
				sb.append(n);
			}
			
		}
		//System.out.println(sb.toString());
		return sb.toString();
	}
	
	/*
	 * 0~不包括size,刚好是数组的索引
	 */
	public	static synchronized int zeroTo(int size){
		java.util.Random ran=new java.util.Random();
		return ran.nextInt(size);
	}
	
	/*
	 * size中得到count个不重复的随机数
	 */
	public	static synchronized List<Integer> zeroTo(int size,int count){
		List<Integer> res=new ArrayList();
		for(int i=0;i<count;i++){
			int temp=zeroTo(size);
			while(res.contains(temp)){
				temp=zeroTo(size);
			}
			res.add(temp);
		}
		return res;
	}
	
	public static String radomAndContain(String containbuy,int count){
		if(containbuy==null){
			return getRandom(count);
		}
		StringBuffer sb=new StringBuffer();
		java.util.List<Integer> list=new java.util.ArrayList<Integer>();
		for(int i=0;i<containbuy.length();i++){
			String sub=containbuy.substring(i,i+1);
			list.add(Integer.parseInt(sub));
			sb.append(sub);
		}
		java.util.Random ran=new java.util.Random();
		
		while(list.size()<count)
		{
			int n=ran.nextInt(10);
			if(!list.contains(n)){
				list.add(n);
				sb.append(n);
			}
			
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	
	public static void main(String[] arg) {
		/*java.util.Random ran=new java.util.Random();
		for(int i=0;i<4;i++){
			System.out.println(zeroTo(100,20));
		}*/
			
		int res=Integer.parseInt(Random.getRandom(1));
		res++;
		if(res>=6){
			res=res-5;
		}
		System.out.println(res);
		
	}
}
