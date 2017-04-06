

import java.util.Locale;

import com.huhui.util.Arith;
import com.huhui.util.HttpClientUtil;

public class Http {
	public static void main(String[] arg)
    {
		System.out.println(Locale.JAPAN.getLanguage());
		/*int i=0;
		while(true){
			i++;
			try {
				Thread.sleep(1000);
				double price=1.3;
				if(i%2==0){
					price=1.4;
				}
				HttpClientUtil.get("http://127.0.0.1:8081/server_command/command?action=setquote&symbol=EUR/USD&price="+price);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		//System.out.print(Arith.div(Math.floor(2.262*10), 10,1));
		//System.out.print("double:1".split(":")[1]);
		String symbol="EURUSD";
		symbol=symbol.substring(0,3)+"/"+symbol.substring(3, symbol.length());
		//System.out.print(symbol);
		
		//symbol="EUR/USD";
		//symbol=symbol.replace("/", "");
		//System.out.println("-");
		//System.out.println("�,\"".replace("�,\"","\",\""));
		//System.out.print(Arith.div(Double.parseDouble("1.11586035062E9"), 1,2));
		System.out.println("x".compareToIgnoreCase("问"));
		
		String format="a.aa";
		if(format!=null&&format.indexOf(".")!=-1){
			String[] formats=format.split("\\.");
			format=formats[formats.length-1];
		}
		if(format==null||format.length()==0){
			format="";
		}else{
			format="."+format;
		}
		System.out.println(format);
		System.out.println((System.currentTimeMillis()-(System.currentTimeMillis()-20000))/1000);
		
		System.out.println("/configResource/temp/1/2".split("/")[3]);
    } 
}
