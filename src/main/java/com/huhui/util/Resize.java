package com.huhui.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Resize {
	
	
	public static List<Map> reSize(List<Map> src,String groupProperty,String doubleProperty){
		if(src==null||src.size()==0){
			return src;
		}
		String value=(String)src.get(0).get(groupProperty);
		int fromIndex=0;
		int toIndex=0;
		List<Map> res=new ArrayList();
		for(int i=fromIndex;i<src.size();i++){
			if(value.equals((String)src.get(i).get(groupProperty))){
				toIndex=i;
			}else{
				value=(String)src.get(i).get(groupProperty);
				addToResult(src,res,fromIndex,toIndex,doubleProperty);
				fromIndex=i;
				toIndex=i;
				if(i==src.size()-1){
					addToResult(src,res,fromIndex,toIndex,doubleProperty);
				}
			}
		}

		return res;
	}
	
	public static List<Map> reSize(List<Map> src,int wantedBars,String doubleProperty){
		if(src==null||src.size()<=wantedBars){
			return src;
		}
		int perwillget2=(int)Arith.mul(2, Arith.div(src.size(), wantedBars));
		if(perwillget2==2){
			return src;
		}
		
		List<Map> res=new ArrayList();
		int count=src.size()/perwillget2;
		for(int i=0;i<count;i++){
			addToResult(src,res,i*perwillget2,i*perwillget2+perwillget2-1,doubleProperty);
		}
		if(src.size()>perwillget2*count){
			addToResult(src,res,perwillget2*count-1,src.size()-1,doubleProperty);
		}
		return res;
	}
	
	public static void addToResult(List<Map> src,List<Map> res,int fromIndex,int toIndex,String doubleProperty){
		if(fromIndex==toIndex){
			res.add(src.get(fromIndex));
			return;
		}
		double from=(Double)src.get(fromIndex).get(doubleProperty);
		int max=fromIndex;
		int min=fromIndex;
		double maxValue=from;
		double minValue=from;
		for(int i=fromIndex+1;i<=toIndex;i++){
			double value=(Double)src.get(i).get(doubleProperty);
			if(value>maxValue){
				maxValue=value;
				max=i;
			}
			if(value<minValue){
				minValue=value;
				min=i;
			}
		}
		if(max>min){
			res.add(src.get(min));
			res.add(src.get(max));
		}else if(max<min){
			res.add(src.get(max));
			res.add(src.get(min));
		}else{
			res.add(src.get(max));
		}
	}
}
