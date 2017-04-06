package com.huhui.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
public class FileUtil {

	public static void main(String[] arg) {
		//String name = "com/autodo/strategy/ctp/Arbitrage.java";
		// URL
		// url=ClassLoader.getSystemResource("com/autodo/strategy/ctp/Arbitrage.java");
		//System.out.print(getContentByPackagePath(name, true));
		//appendByLine("D:\\workspace\\multi_account\\service\\quickfixj\\src\\main\\java\\com\\huhui\\trade\\service\\stp\\tradeplatform\\lmax_template.cfg","D:\\启动\\cnm.txt",new String[]{"SenderCompID=prouat102_ORDER","TargetCompID=prouatxxxx"});
		//copyFolder(new File("D:\\tmp"),new File("D:\\tmp1"));
		String[] res=getFileLines("D:\\document\\leanwork\\BW续费通知邮件模板.html..html","UTF-8");
		for(int i=0;i<res.length;i++){
			String res_=res[i].replace("\"", "\\\"");
			res_=res_.replace("\'", "\\\'");
			System.out.print(res_.trim()+"\\r\\n");
		}
	}
	
	public static void inputStreamToFile(InputStream ins,File file) {
		  try {
		   OutputStream os = new FileOutputStream(file);
		   int bytesRead = 0;
		   byte[] buffer = new byte[8192];
		   while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
		    os.write(buffer, 0, bytesRead);
		   }
		   os.close();
		   ins.close();
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
	}

	/**
	 * 
	 * @param packagePath
	 *            :com/autodo/strategy/ctp/Arbitrage.java
	 * @param needEndl
	 * @return
	 */
	public static String getContentByPackagePath(String packagePath,
			boolean needEndl) {

		BufferedReader reader;
		String className = getClassName(packagePath);
		try {
			URL url = Class.forName(className).getClassLoader().getResource(
					packagePath);
			reader = new BufferedReader(new FileReader(url.getPath()));
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				sb.append(line);
				if (needEndl) {
					sb.append("<br>");
				}
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param packagePath
	 *            :com/autodo/strategy/ctp/Arbitrage_Gold.java
	 * @return :com.autodo.strategy.ctp.Arbitrage
	 */
	public static String getClassName(String packagePath) {
		/*
		 * int index=packagePath.indexOf("_"); if(index==-1){
		 * index=packagePath.indexOf("."); } String
		 * className=packagePath.substring(0,index); return
		 * className.replace("/",".");
		 */
		return FileUtil.class.getName();
	}

	public static String getFileContent(String path)  {
		File f = new File(path);
		FileReader fileReader;
		try {
			fileReader = new FileReader(f);
			BufferedReader br = new BufferedReader(fileReader);
			StringBuffer res = new StringBuffer();
			String str;
			while ((str = br.readLine()) != null) {
				res.append(str);
			}
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String[] getFileLines(String f,String charset)  {
		List<String> list=new ArrayList();
	    try   
	    {    
	    	File file=new File( f);
	        if(file.isFile()&&file.exists())  
	        {       
	            InputStreamReader read = new InputStreamReader(new FileInputStream(file),charset);       
	            BufferedReader reader=new BufferedReader(read);       
	            String line;   
	            
	            while ((line = reader.readLine()) != null)   
	            {        
	            	list.add(line);       
	            }         
	            read.close();      
	        }     
	    } catch (Exception e)   
	    {         
	        e.printStackTrace();     
	    }
	    String[] res=new String[list.size()];
	    list.toArray(res);
	    return res;
	}
	public static String getFileContent(String f,String charset)  {
		return getFileContent(new File( f), charset) ;
	}
	public static String getFileContent(File f,String charset)  {
		StringBuffer list=new StringBuffer();
	    try   
	    {          
	        if(f.isFile()&&f.exists())  
	        {       
	            InputStreamReader read = new InputStreamReader(new FileInputStream(f),charset);       
	            BufferedReader reader=new BufferedReader(read);       
	            String line;   
	            
	            while ((line = reader.readLine()) != null)   
	            {        
	            	list.append(line);       
	            }         
	            read.close();      
	        }     
	    } catch (Exception e)   
	    {         
	        e.printStackTrace();     
	    }
	    return list.toString();
	}
	
	public static List<String> getFileContents(String path,String charset)  {
	    List<String> list=new ArrayList();
	    try   
	    {       
	        File f = new File(path);      
	        if(f.isFile()&&f.exists())  
	        {       
	            InputStreamReader read = new InputStreamReader(new FileInputStream(f),charset);       
	            BufferedReader reader=new BufferedReader(read);       
	            String line;       
	            while ((line = reader.readLine()) != null)   
	            {        
	            	list.add(line);       
	            }         
	            read.close();      
	        }     
	    } catch (Exception e)   
	    {         
	        e.printStackTrace();     
	    }     
	    return list;  
	    
		
	}
	
	public static List<String> getFileContents(String path)  {
		return getFileContents( path,"gbk") ;
	}
	
	public static void copy(String src,String desc)  {
		  FileInputStream fi;
		try {
			fi = new FileInputStream(src);
			BufferedInputStream in=new BufferedInputStream(fi);
			FileOutputStream fo=new FileOutputStream(desc);
			  BufferedOutputStream out=new BufferedOutputStream(fo);
			  byte[] buf=new byte[1024];
			  int len=in.read(buf);
			  while(len!=-1){
			   out.write(buf, 0, len);
			   len=in.read(buf);
			  }
			  out.close();
			  fo.close();
			  in.close();
			  fi.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		  
	}
	
	
	public static void appendByLine(String src,String tofile,String[] appendcontent){  
        try {
            // read file content from file
            StringBuffer sb= new StringBuffer("");
            FileReader reader = new FileReader(src);
            BufferedReader br = new BufferedReader(reader);
            String str = null;
            while((str = br.readLine()) != null) {
                  sb.append(str+"\r\n");
                  //System.out.println(str);
            }
            br.close();
            reader.close();
            // write string to file
            FileWriter writer = new FileWriter(tofile,false);
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(sb.toString());
            if(appendcontent!=null){
            	int len=appendcontent.length;
            	for(int i=0;i<len-1;i++){
            		bw.write(appendcontent[i]+"\r\n");
            	}
            	bw.write(appendcontent[len-1]);
            }
            
            bw.close();
            writer.close();
      }catch(Exception e) {
            e.printStackTrace();
      }
	} 
	public static boolean saveToFile(String fileName,String content,String charset){
	      try{  
	    	  FileOutputStream fos = new FileOutputStream(fileName);
	          Writer out = new BufferedWriter(new OutputStreamWriter(fos,charset)); 
	          out.write(content);    
	          out.close();   
	          fos.close(); 
	    	  return true;
	    	 }   
	    catch(IOException   e)   
	    {
	    	return false;
	    }  
	}
	public static void write(String src,String content){
		if(content==null){
			return;
		}
		 
		try {   
			FileWriter writer = new FileWriter(src,false);
	        BufferedWriter bw = new BufferedWriter(writer);
	        bw.write(content+"\r\n");
	        bw.close();
	        writer.close();
		} catch(Exception e){
			e.printStackTrace();
		}
	 }
	
	/*
	 * 复制文件或者目录
	 */
	public static  void copyFolder(File src, File dest) {  
		try{
			if (src.isDirectory()) {  
		        if (!dest.exists()) {  
		            dest.mkdir();  
		        }  
		        String files[] = src.list();  
		        for (String file : files) {  
		            File srcFile = new File(src, file);  
		            File destFile = new File(dest, file);  
		            // 递归复制  
		            copyFolder(srcFile, destFile);  
		        }  
		    } else {  
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
		}catch(Exception e){
			e.printStackTrace();
		}  
	}  
}
