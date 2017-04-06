package com.huhui.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * @author Zark
 *
 */
public class StringUtil {
	
	public static final String UTF_8="UTF-8";
	
	public static final String GBK="GBK";
	
	public static final String ISO="ISO-8859-1";
	
	public static String convertStreamToString(InputStream is) {   
		   BufferedReader reader = new BufferedReader(new InputStreamReader(is));   
		        StringBuilder sb = new StringBuilder();   
		        String line = null;   
		        try {   
		            while ((line = reader.readLine()) != null) {   
		                sb.append(line + "/n");   
		            }   

		        } catch (IOException e) {   

		            e.printStackTrace();   

		        } finally {   

		            try {   

		                is.close();   

		            } catch (IOException e) {   

		                e.printStackTrace();   

		            }   

		        }   
		        return sb.toString();   
	}    
	
	public static String nextId(List<String> ids,String prefix,String beginId){
		
		if(ids==null||ids.size()==0){
			return prefix+beginId;
		}
		
		String[] src=new String[ids.size()];
		ids.toArray(src);
		
		Arrays.sort(src);
		
		String last=src[src.length-1];
		String id=last.replace(prefix, "");
		int nextId=0;
		try{
			nextId=Integer.parseInt(id)+1;
			return prefix+String.valueOf(nextId);
		}catch(Exception e){
			return last+"1";
		}
	}

	public static String getDomain(String url){
		
		if(url==null){
			return null;
		}
		int index=url.indexOf("//");
		if(index!=-1){
			url=url.substring(index+2,url.length());
		}
		
		index=url.indexOf(":");
		if(index!=-1){
			url=url.substring(0,index);
		}
		
		index=url.indexOf("/");
		if(index!=-1){
			url=url.substring(0,index);
		}
        return url;
	}
	
	//a,a,a
	public static String times(String src,String split,int times){
		if(src==null){
			return null;
		}
		if(split==null){
			split="";
		}
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<times;i++){
		  sb.append(src).append(split);
		}
		String res=null;
		try{
			res=sb.substring(0, sb.length()-split.length());
		} catch(Exception e){
			System.err.print(src);
			e.printStackTrace();
		}
		return res;
	}
	
	public static String trim(String src){
		if(src==null){
			return null;
		}
		return src.trim();
	}
	
	public static String arrayTo2Quotation (String[] src){
		if(src==null){
			return null;
		}
		String[] res=new String[src.length];
		for(int i=0;i<src.length;i++){
			res[i]="\""+src[i]+"\"";
	    }
		return arrayToString(res,",");
	}
	
	public static String arrayToString(String[] src,String split){
		if(src==null){
			return null;
		}
		StringBuffer sb=new StringBuffer();
		for(String s:src){
		  sb.append(trim(s)).append(split);
		}
		String res=null;
		try{
			res=sb.substring(0, sb.length()-split.length());
		} catch(Exception e){
			System.err.print(src);
			e.printStackTrace();
		}
		return res;
	}
	
	public static String listToString(List<String> src,String split){
		if(src==null){
			return null;
		}
		return arrayToString(listToStrings(src), split);
	}
	public static String[] listToStrings(List<String> list){
		if(list==null){
			return null;
		}
		String[] res=new String[list.size()];
		list.toArray(res);
		return res;
	}
	
	public static Map urlParamToMap(String urlPara){
		if(urlPara==null){
			return null;
		}
		Map res=new HashMap();
		String[] paramlist=urlPara.split("&");
		for(String temp:paramlist){
			String[] everyPara=temp.split("=");
			if(everyPara!=null && everyPara.length==2){
				res.put(everyPara[0], everyPara[1]);
			}
		}
		return res;
	}
	
	public static String encode(String src,String format){
		if(src==null){
			return null;
		}
		try {
			return java.net.URLEncoder.encode(src, format);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return src;
	}
	
	public static String codeConversion(String src,String from,String to){
		if(src==null){
			return null;
		}
		try {
			return new   String(src.getBytes(from),to);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return src;
		}
		
	}
	public static String decode(String src,String format){
		if(src==null){
			return null;
		}
		try {
			return java.net.URLDecoder.decode(src, format);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return src;
	}
	/*
	 * src : 33;pend "0";len 5=>00033
	 */
	public static String preApend(String src,String pend,int len){
		if(src==null){
			return times(pend, null, len);
		}
		if(src.length()==len){
			return src;
		}
		return times(pend, null, len-src.length())+src;
		
	}
	
	public static boolean existUpperCase(String word)
	 {
	  for(int i = 0; i < word.length(); i++)
	  {
	   char c = word.charAt(i);
	   if (Character.isUpperCase(c))
	   {
	    return true;
	   }
	  }
	  return false;
	 }
	
	public static void main(String[] args){
		//System.out.println(preApend("987","0",5));
		List<String> ids=new ArrayList();
		ids.add("B23");
		ids.add("B1");
		//System.out.print(StringUtil.nextId(ids, "B", "1"));
		//System.out.print(times("<<","",1));
		//System.out.print(encode("淡淡的","GBK"));
		/*try {
			System.out.print(new   String("淡淡的".getBytes("UTF-8"), "GBK"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		StringBuffer sb=new StringBuffer("1234");
		sb.substring(0, 2);
		//System.out.println(StringUtil.arrayToString(new String[]{"a","b"},"=?,"));
		System.out.println(sb.substring(0, 2));
	}
}
