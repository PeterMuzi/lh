package com.huhui.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
/**
 * @author Zark
 */
public class NumberFormat {
	
	private static DecimalFormat doubleFormat =new DecimalFormat("0.##");
	
	/*取合
	 * @param first=0.7899, second=0.7897, pointNum=4
	 * @return 2
	 */
	public	static double getSum(List list,String property){
		if(list==null||list.size()==0){
			return 0d;
		}
		double res=0d;
		for(int i=0;i<list.size();i++){
			Object o=list.get(i);
			Object value;
			try {
				value = PropertyUtils.getProperty(o, property);
				if(value!=null){
					res=Arith.add(res, Double.parseDouble(value.toString()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	
	/*盈利点数
	 * @param first=0.7899, second=0.7897, pointNum=4
	 * @return 2
	 */
	public	static double profitPoint(double first,double second,int pointNum){
		return Arith.mul(Arith.sub(first,second), point(pointNum)) ;
	}
	
	public	static String doubleFormat(double src){
		if(src==0){
			return "0";
		}
		return doubleFormat.format(src);
	}
	
	/*取多少位小数
	 * @param src=0.78993, pointNum=4
	 * @return 0.7899
	 */
	public	static double formatDouble(double src,int pointNum){
		if(src==0){
			return 0;
		}
		double point=point(pointNum);
		src=Arith.mul(src,point);
		src=Math.round(src);
		src=Arith.div(src,point) ;
		return src;
	}
	/*
	 * @param src="0", pointNum=4
	 * @return "0000"
	 */
	public	static String times(String src,int pointNum){
		if(src==null){
			src="0";
		}
		String res="";
		for(int i=0;i<pointNum;i++){
			res+=src;
		}
		return res;
	}
	/*
	 * @param pointNum=4
	 * @return 10000
	 */
	public	static float point(int pointNum){
		String formateS=times("0",pointNum);
		return Float.parseFloat("1"+formateS);
	}
	/*
	 * @param pointNum=4
	 * @return 0.0001
	 */
	public	static double pointValue(int pointNum){
		if(pointNum==0){
			return 1;
		}else if(pointNum==-1){
			return 10;
		}
		String formateS=times("0",pointNum-1);
		return new Double("0."+formateS+"1").doubleValue();
	}
	/*
	 * @param pointNum=0.0001,price=0.009
	 * @return 0.0090
	 */
	public	static String smallNumber(double pointNum,double price){
		int count=0;
		if(pointNum>=1){
			count= 0;
		}else if(pointNum==0.1){
			count= 1;
		}else if(pointNum==0.01){
			count= 2;
		}else if(pointNum==0.001){
			count= 3;
		}else if(pointNum==0.0001){
			count= 4;
		}else if(pointNum==0.00001){
			count= 5;
		}else if(pointNum==0.000001){
			count= 6;
		}else{
			count= 1;
		}
		if(count==0){
			return String.valueOf(((int)price));
		}
		
		//DecimalFormat df = new DecimalFormat("0.000"); 
		//System.out.println(df.format(50.02));
		
		price=Arith.div(price, 1,count);
		String[] res=String.valueOf(price).split("\\.");
		if(res.length==1){
			return String.valueOf(price);
		}
		
		String small=res[1];
		if(small.length()<count){
			int size=count-small.length();
			for(int i=0;i<size;i++){
				small=small+"0";
			}
		}
		return res[0]+"."+small;
	}
	/*
	 * @param price=1.3324, stoploss=1.3326
	 * @return 0.0002 or 0
	 */
	public	static double getOffSet(double price,double stoploss){
		if(price==0||stoploss==0){
			return 0;
		}
		return Math.abs(Arith.sub(price, stoploss));
	}
	
	
	
	public	static void main(String[] arg){
		/*System.out.println(formatDouble(1.02505,2));
		System.out.println(point(4));*/
		//System.out.println(point(4));
		//System.out.println(pointValue(4));
		//System.out.println(formatDouble(1.02406,0));
		//System.out.println(profitPoint(7899,7897,0));
		//System.out.println((int)Arith.div(1, 0.0001));
		
		 /* double d = 0.00002328000;
		  java.text.NumberFormat formatter = java.text.NumberFormat.getInstance();
		  formatter.setMaximumFractionDigits(12);
		  String sdouble = formatter.format(d);
		  System.out.println(sdouble);
		  System.out.println(doubleFormat(1.0));
		  doubleFormat.format(d);*/
		//System.out.println(smallNumber(0.0001,0.78));
		
		//DecimalFormat df = new DecimalFormat("0.000"); 
		//System.out.println(Number(0.09,3));
		System.out.print((int)1.6d);
	}
}
