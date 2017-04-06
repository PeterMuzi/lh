package com.huhui.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;

public class FtpClient {
	private String ip = null;
	private int port = 0;
	private String username = null;
	private String password = null;
	private String charSet = null;
	private String workingDirectory=null;
	private String targetPath=null;
	
	public static void main(String[] args) {
		
	}
	
    public void upload(File srcFile) throws Exception { 
        FTPClient ftpClient = new FTPClient(); 
        FileInputStream fis = null; 
        try { 
        	ftpClient.setDefaultTimeout(180000);
            ftpClient.setConnectTimeout(180000);
            //ftpClient.setSoTimeout(30000);
            ftpClient.setDataTimeout(180000);
            ftpClient.connect(ip, port); 

            ftpClient.login(username,password); 
            fis = new FileInputStream(srcFile); 
            //设置上传目录 
            ftpClient.changeWorkingDirectory(workingDirectory); 
            ftpClient.setBufferSize(1024); 
            ftpClient.setControlEncoding(charSet); 
            //设置文件类型（二进制） 
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE); 
            ftpClient.storeFile(targetPath+"/"+srcFile.getName(), fis); 
            System.out.println(new Date()+":"+srcFile.getName());
        } catch (Exception e) { 
            throw e;
        } finally { 
            IOUtils.closeQuietly(fis); 
            try { 
                ftpClient.disconnect(); 
            } catch (Exception e) { 
                e.printStackTrace(); 
            } 
        } 
    } 

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getWorkingDirectory() {
		return workingDirectory;
	}

	public void setWorkingDirectory(String workingDirectory) {
		this.workingDirectory = workingDirectory;
	}

	public String getCharSet() {
		return charSet;
	}

	public void setCharSet(String charSet) {
		this.charSet = charSet;
	}

	public String getTargetPath() {
		return targetPath;
	}

	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}

}
