package com.huhui.util;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class CutImage {
	 private BufferedImage img;

	 private BufferedImage child;

	 public CutImage(){

	 }

	 public CutImage(BufferedImage im){

	  img=im;

	 }

	 public CutImage(Image im){

	  img=(BufferedImage)im;

	 }

	 public void setImg(BufferedImage img) {

	  this.img = img;

	 }

	 public BufferedImage getChildImage(int x,int y,int width,int height) {

	  int cw=width;

	  int ch=height;

	  int pw=img.getWidth();

	  int ph=img.getHeight();

	  if(pw<x+width){

	   System.out.println("给出的参数超出原图片的范围！程序会自动减小宽度或高度");

	   cw=pw-x;

	  }

	  if(ph<y+height){

	   System.out.println("给出的参数超出原图片的范围！程序会自动减小宽度或高度");

	   ch=ph-y;

	  }

	  child=new BufferedImage(cw,ch,BufferedImage.SCALE_SMOOTH );//TYPE_INT_ARGB

	  for(int i=0;i<ch;i++){

	   for(int j=0;j<cw;j++){

	    child.setRGB(j, i, img.getRGB(x+j, y+i));

	   }

	  }

	  return child;

	 }
}
