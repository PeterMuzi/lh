package com.huhui.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DirCopy {

	public static void main(String[] arg) {
		List<String> excludes=new ArrayList();
		if(arg.length==5){
			String src=arg[0];
			String temp=arg[1];
			String desc=arg[2];
			String time=arg[3];//like:20140807152300
			String exlude=arg[4];//.svn
			String[] datas=exlude.split(",");
			for(String s:datas){
				excludes.add(s);
			}
			
			copyFolder(new File(src),new File(temp),time, excludes);
			copyFolder(new File(temp),new File(desc),time, excludes);
			return;
		}
		
		File temp=new File("D:\\tmp"+"\\"+System.currentTimeMillis());
		temp.mkdir();
		excludes.add(".svn");
		copyFolder(new File("D:\\tmp"),new File("D:\\tmp1"),"20140811103000",excludes);
	}
	
	
	/*
	 * 复制文件或者目录
	 */
	public static  void copyFolder(File src, File dest,String more_than_time,List<String> excludes) {  
		try{
			if (src.isDirectory()&&!excludes.contains(src.getName())) {  
		        if (!dest.exists()) {  
		        	if(src.list()==null||src.list().length==0){
		        		
		        	}else{
		        		dest.mkdir();
		        	}
		        }  
		        String files[] = src.list();  
		        for (String file : files) {  
		            File srcFile = new File(src, file);  
		            File destFile = new File(dest, file);  
		            // 递归复制  
		            copyFolder(srcFile, destFile,more_than_time,excludes);  
		        }  
		    } else if(src.isFile()){
		    	if(more_than_time.compareTo(DateUtil.getTime(src.lastModified(), "yyyyMMddHHmm"))<=0){
		    		InputStream in = new FileInputStream(src);  
			        OutputStream out = new FileOutputStream(dest);  
			  
			        byte[] buffer = new byte[1024];  
			  
			        int length;  
			          
			        while ((length = in.read(buffer)) > 0) {  
			            out.write(buffer, 0, length);  
			        }  
			        in.close();  
			        out.close();
		    	}
		    }
		}catch(Exception e){
			e.printStackTrace();
		}  
	}  
}
