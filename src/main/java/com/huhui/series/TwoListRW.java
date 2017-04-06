package com.huhui.series;

import java.util.ArrayList;
import java.util.List;

public class TwoListRW {
	
	
	public List list1=null;
	public List list2=null;
	public boolean list1Wrting=false;
	
	public List getForRead(){
		if(list1Wrting){
			if(list2!=null){
				return list2;
			}else{
				return list1;
			}
		}
		return list1;
	}
	
	public List getForWrite(){
		list1Wrting=!list1Wrting;
		if(list1Wrting){
			if(list1==null){
				list1=new ArrayList();
			}
			return list1;
		}else{
			if(list2==null){
				list2=new ArrayList();
			}
			return list2;
		}
	}
}
