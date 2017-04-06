package com.huhui.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class HttpClientUtil {
	
	public static String post(String url) {
		return post( url,null,null);
	}
	public static String post(String url,String[] paraNames,String[] paraValues) {
		return post(null, url, paraNames, paraValues);
	}
	public static String post(HttpClient	client,String url,String[] paraNames,String[] paraValues) {
        return post(	client, url,paraNames,paraValues,null,null);
    }
	
	public static String post(HttpClient	client,String url,String[] paraNames,String[] paraValues,String[] headNames,String[] headValues) {
		if(client==null)client=new HttpClient();
		client.setConnectionTimeout(5000);
        PostMethod postMethod = new Utf8PostMethod(url);
        if(paraNames!=null){
        	//postMethod.setRequestBody(para(paraNames,paraValues));
        	for(int i=0;i<paraNames.length;i++){
        		postMethod.addParameter(paraNames[i], paraValues[i]);
        	}
        }
        if(headNames!=null){
        	for(int i=0;i<headNames.length;i++){
        		postMethod.setRequestHeader(headNames[i], headValues[i]);
        		//postMethod.addRequestHeader(headNames[i], headValues[i]);
        	}
        }
        try {
        	int statusCode = client.executeMethod(postMethod);
        	
            String res= postMethod.getResponseBodyAsString();
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: "+ postMethod.getStatusLine());
            }else{
            	return res;
            }
            return null;
        } catch (Exception e) {
                e.printStackTrace();
        }finally{
                postMethod.releaseConnection();
        }
        return null;
    }
	
	public static String get(String url) {
		return get( url,null,null);
	}
	
	public static String get(GetMethod getmethod,String url) {
		return get(null,getmethod, url,null,null);
	}
	
	public static String get(GetMethod getmethod,String url,String[] paraNames,String[] paraValues) {
		return get(null,getmethod, url,paraNames,paraValues) ;
	}
	
	public static String get(String url,String[] paraNames,String[] paraValues) {
		return get(null,null, url,paraNames,paraValues) ;
	}
	
	public static String get(HttpClient	client,GetMethod getmethod,String url,String[] paraNames,String[] paraValues) {
		if(client==null)client=new HttpClient();
		client.setConnectionTimeout(5000);
		if(getmethod==null) getmethod = new GetMethod(url);
        if(paraNames!=null){
        	getmethod.setQueryString(para(paraNames,paraValues));
        }
        try {
        	int statusCode = client.executeMethod(getmethod);
        	String res=getmethod.getResponseBodyAsString();
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: "+ getmethod.getStatusLine());
            }else{
            	return res;
            }
            return null;
        } catch (Exception e) {
                e.printStackTrace();
        }finally{
        	getmethod.releaseConnection();
        }
        return null;
    }
	
	public static String httpsget(String url,String[] paraNames,String[] paraValues) {
		HttpClient	client=new HttpClient();
		client.setConnectionTimeout(5000);
        GetMethod postMethod = new GetMethod(url);
        if(paraNames!=null){
        	postMethod.setQueryString(para(paraNames,paraValues));
        }
        try {
        	int statusCode = client.executeMethod(postMethod);
        	
            String res= postMethod.getResponseBodyAsString();
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: "+ postMethod.getStatusLine());
            }else{
            	return res;
            }
            return null;
        } catch (Exception e) {
                e.printStackTrace();
        }finally{
                postMethod.releaseConnection();
        }
        return null;
    }
	
	public static NameValuePair[] para(String[] paraNames,String[] paraValues){
		NameValuePair[] res=new NameValuePair[paraNames.length];
		for(int i=0;i<res.length;i++){
			res[i]=new NameValuePair(paraNames[i],paraValues[i]);
		}
		return res;
	}
	
	public	static void main(String[] arg){
		/*System.out.println(formatDouble(1.02505,2));
		System.out.println(point(4));*/
		//System.out.println(point(4));
		//System.out.println(pointValue(4));
		//System.out.println(formatDouble(1.02406,0));
		//System.out.println(profitPoint(7899,7897,0));
		//get("http://sdkhttp.eucp.b2m.cn/sdkproxy/sendsms.action?cdkey=0SDK-EMY-0130-LDXLN&password=317215&phone=13402868556&message=内容&addserial=");
		String res=HttpClientUtil.get("http://42.96.129.242:8080/forward?bars=3&symbol=EURUSD&period=m15&ran="+System.currentTimeMillis());
		if(res!=null){
			res=res.replace("<data><s d='", "");
			res=res.replace("' /></data>", "");
			String[] datas=res.split("' /><s d='");
			System.out.println(datas[0]);
			System.out.println(datas[1]);
			System.out.println(datas[2]);
		}
		
	}
    public static class Utf8PostMethod extends PostMethod{
        public Utf8PostMethod(String url){
            super(url);
        }
        @Override
        public String getRequestCharSet() {
            return "UTF-8";
        }
    }
}
