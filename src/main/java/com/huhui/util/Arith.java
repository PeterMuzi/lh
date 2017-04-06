
package com.huhui.util;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
/**
 * 数学加减
 * @author Zark
 */
public class Arith {

	private static final int DEF_DIV_SCALE = 10;

	private Arith() {
	}

	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");

		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	public	static void main(String[] arg){

		System.out.println(div(1,100,2));
		
		
	}
	
	public static double mul(Map a, String pro1,String pro2) {
		if (a==null||!a.containsKey(pro1)||!a.containsKey(pro2)) {
			return 0;
		}
		return  mul(Double.parseDouble(a.get(pro1).toString()),Double.parseDouble(a.get(pro2).toString()));
	}
	public static double mul(Map a, String pro1,double pro2) {
		if (a==null||!a.containsKey(pro1)||pro2==0) {
			return 0;
		}
		return Math.abs(Arith.div(mul(Double.parseDouble(a.get(pro1).toString()),pro2), 1,2));
	}
	public static double sub(Map a, String pro1,String pro2) {
		if (a==null||!a.containsKey(pro1)||!a.containsKey(pro2)) {
			return 0;
		}
		double p1=Double.parseDouble(a.get(pro1).toString());
		double p2=Double.parseDouble(a.get(pro2).toString());
		return sub(p1,p2);
	}
	public static double add(Map a, List<String> pros) {
		if (a==null||pros==null||pros.isEmpty()) {
			return 0;
		}
		double res=0;
		for(String src:pros){
			if(a.containsKey(src)){
				res=Arith.add(res, Double.parseDouble(a.get(src).toString()));
			}
		}
		return Arith.div(res, 1, 2);
	}
	public static double add(Map a,String[] pros) {
		if (a==null||pros==null||pros.length==0) {
			return 0;
		}
		double res=0;
		for(String src:pros){
			if(a.containsKey(src)){
				res=Arith.add(res, Double.parseDouble(a.get(src).toString()));
			}
		}
		return Arith.div(res, 1, 2);
	}
}
