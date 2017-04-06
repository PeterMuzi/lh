package com.huhui.util;

import java.text.DecimalFormat;

public class ObjectId {
	
	public static ObjectId instance=null;
	public long id=0;
	public String date=String.valueOf(System.currentTimeMillis());
	private DecimalFormat format =  new DecimalFormat("00000");;
	public synchronized String nextId(){
		id++;
		if(id>=99998){
			date=String.valueOf(System.currentTimeMillis());
			id=0;
		}
		return date+format.format(id);
	}
	
	public static ObjectId getInstance(){
		if(instance==null){
			instance=new ObjectId();
		}
		return instance;
	}
	
	public static void main(String[] noRecordDao)
	{
		//ObjectId id=new ObjectId();
		//System.out.println(id.nextId());
		//id.id=999907;
		//System.out.println(id.nextId());
		for(int i=0;i<100;i++){
			System.out.println(ObjectId.getInstance().nextId());
		}
		new Long(1396274840454000670L);
	}
}
