package com.huhui.util.timeseries;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.huhui.util.DateUtil;
import com.huhui.util.FileUtil;

public class DataQuoteSer {
	
	private TimestampsOnOccurred timestampsOnOccurred=new TimestampsOnOccurred(100);
	
	private Map<String,Quote> datas=new Hashtable();
	
	private String name=null;
	
	public long lastChartTime=0;
	
	public DataQuoteSer(String name) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public Quote getOrCreate(String time){
		if(datas.containsKey(time)){
			return datas.get(time);
		}
		Quote temp=timestampsOnOccurred.createQuote(time);
		datas.put(time, temp);
		return temp;
	}
	public Quote get(String time){
		if(datas.containsKey(time)){
			return datas.get(time);
		}
		return null;
	}
	public List<Quote> getItems(){
		return timestampsOnOccurred;
	}
	
	public boolean contain(String time){
		return datas.containsKey(time);
	}
	
	public int size(){
		return datas.size();
	}
	
	public boolean isEmpty(){
		return datas.isEmpty();
	}
	
	/*
	 * ramain,remove the old
	 */
	public int remain(int count){
		int len=this.size();
		if(len<=count){
			return 0;
		}
		Quote q=null;
		for(int i=0;i<len-count;i++){
			q=timestampsOnOccurred.get(len-1-i);
			timestampsOnOccurred.remove(len-1-i);
			datas.remove(q.getSer_time());
			q=null;
		}
		return len-count;
	}
	
	public List<Quote> searchBefore(String time,int count){
		if(this.isEmpty()){
			return null;
		}
		
		int index=timestampsOnOccurred.nearestIndexOfOccurredTime(time);
		int len=this.size();
		if(len==index){
			return null;
		}
		boolean exist=this.datas.containsKey(time);
		if(exist){
			index++;
		}
		List<Quote> res=new ArrayList();
		for(int i=index;i<index+count&&i<=len-1;i++){
			res.add(timestampsOnOccurred.get(i));
		}
		return res;
	}
	
    @Override
	public String toString() {
		return "DataQuoteSer [timestampsOnOccurred=" + timestampsOnOccurred + "]";
	}

	public static void main(String[] args){
		System.out.println("&".indexOf("&"));
    	DataQuoteSer t=new DataQuoteSer("");
    	System.out.println(t.size());
    	System.out.println(t.size());
    	t.getOrCreate("201602160735");
    	t.getOrCreate("201602160730");
    	t.getOrCreate("201602160736");
    	t.getOrCreate("201602160738");
    	t.getOrCreate("201602160729");
    	t.getOrCreate("201602160737");
    	t.getOrCreate("201602160732");
    	t.getOrCreate("201602160733");
    	System.out.println(t);
    	System.out.println(t.size());
    	System.out.println(t.searchBefore("201602160740", 2));
    	System.out.println(t.remain(4));
    	System.out.println(t);
    	System.out.println(t.size());
    	
    	System.out.println(DateUtil.getTime(DateUtil.getTime("yyyyMMddHHmm","201602160735")-2*3600*1000, "yyyyMMddHHmm"));
    	
    	String[] contet=FileUtil.getFileContent("D:\\IFX--达人119037678\\行情k线\\tiny\\Files\\AUDCHF\\AUDCHF_1.csv","UTF-8").split("\r\n");
    	int i=0;
    }
}
