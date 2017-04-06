package com.huhui.util;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public class StringEncoder {

	public static String utf8 = "UTF-8";
	public static final String GBK = "GBK";

	public static String changeCharset(String str, String newCharset) {
		try {
			if (str != null) {
				// 用默认字符编码解码字符串。
				byte[] bs = str.getBytes();
				// 用新的字符编码生成字符串
				return new String(bs, newCharset);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return str;
		}

		return str;
	}

	public static String uRLEncoder(String src, String decoderFormat) {
		if (src == null) {
			return src;
		}
		try {
			return java.net.URLEncoder.encode(src, decoderFormat);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return src;
		}
	}

	public static void uRLEncoder(List<Map> list, String property,
			String descProperty) {
		if (list == null) {
			return;
		}
		for (Map map : list) {
			map.put(descProperty, uRLEncoder((String) map.get(property), utf8));
		}
		return;
	}
	
	public static void main(String[] arg) throws Exception {
		//System.out.print(changeCharset("sd地方","GB2312"));
		
		String a="currentPosition=%E9%A2%84%E7%BA%A6%C2%A0%3E%3E%C2%A0%E8%AE%A2%E7%A9%BA%E8%BD%A6&keyword=&po.qqlx=0&po.xqslh=201312RY156817&po.pzycfh=01R00385716&po.zcrq=2014-01-30&minDate=2014-01-30&maxDate=2014-02-27&po.qqcz=C&po.qqcs=30&po.qqds=1800&po.hqhw=&po.dddxtz=1&po.shdwdh=15009919929&po.uuid=&fzhzzm=%E4%BC%8A%E5%AE%81&fztmism=43116&fjm=%E4%B9%8C%E9%B2%81%E6%9C%A8%E9%BD%90%E5%B1%80&fhdwmc=%E4%BC%8A%E5%AE%81%E5%B8%82%E7%A5%A5%E7%94%9F%E7%89%A9%E6%B5%81%E6%9C%8D%E5%8A%A1%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8&dzhzzm=%E6%96%B0%E9%83%BD&dztmism=45578&djm=%E6%88%90%E9%83%BD%E5%B1%80&shdwmc=%E5%9B%9B%E5%B7%9D%E7%B2%AE%E6%B2%B9%E6%89%B9%E5%8F%91%E4%B8%AD%E5%BF%83%E7%9B%B4%E5%B1%9E%E5%82%A8%E5%A4%87%E5%BA%93&zcdd=%E9%93%81%E8%B7%AF%E8%B4%A7%E5%9C%BA&fzyx=43116+++&qqcsMax=30&xcdd=%E5%9B%9B%E5%B7%9D%E7%B2%AE%E6%B2%B9%E6%89%B9%E5%8F%91%E4%B8%AD%E5%BF%83%E7%9B%B4%E5%B1%9E%E5%82%A8%E5%A4%87%E5%BA%93%E4%B8%93%E7%94%A8%E7%BA%BF&dzyx=45578001&hzpm=%E7%8E%89%E7%B1%B3";
		String res=java.net.URLDecoder.decode(a,utf8);
		System.out.print(res);
	}
}
