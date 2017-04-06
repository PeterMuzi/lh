package com.huhui.util;

import java.io.IOException;
import java.nio.charset.Charset;

import com.csvreader.CsvReader;

public class CsvUtil {
	public static void main(String[] arg){
		
		try {
			CsvReader r = new CsvReader("D:\\document\\leanwork\\BW//银行列表.csv", ',',Charset.forName("UTF-8"));
			//读取表头
			r.readHeaders();
			//逐条读取记录，直至读完
			int i=1;
			while (r.readRecord()) {
			 //读取一条记录
			 String data=r.getRawRecord().split("\"bank\",")[1];
			 String[] datas=data.split("\",\"");
			 
			 System.out.println("{\"value\":\""+i+"\",\"message\":{\"zh-CN\":\""+datas[2].replace("\"", "")+"\",\"en-US\":\""+datas[1].replace("\"", "")+"\"},\"orderNo\":"+i+",\"isDefault\":false},");
			 //System.out.println(datas[1].replace("\"", "")+" "+datas[2].replace("\"", ""));
			 i++;
			 //按列名读取这条记录的值
			 //System.out.println(r.get("Name"));
			 //System.out.println(r.get("class"));
			 //System.out.println(r.get("number"));
			 //System.out.println(r.get("sex"));
			}
		    r.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
