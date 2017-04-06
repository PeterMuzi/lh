package com.huhui.util;

import com.huhui.util.Arith;

public class BuyMoneyUtil {
	
	public static double pernumbuy=2;
	private static double model19=19;
	private static double model192=19.2;
	private static double model195=19.5;
	private static double model75=17.5;
	public static void main11(String[] arg){
		System.out.println(Arith.div(18, 1.6));
	}
	public static void main3(String[] arg){
		String c="from #171";
		System.out.print(c.split("from #")[1]);
		double a=10;double b=12;
		double lja=0;double ljb=0;
		double times=1.786;//3.75;
		for(int i=0;i<8;i++){
			System.out.println("a"+(i+1)+":"+a+" b"+(i+1)+":"+b);
			lja=lja+a;
			ljb=ljb+b;
			System.out.println(" ---a"+(i+1)+":"+lja+" b"+(i+1)+":"+ljb);
			a=lja*times;
			b=ljb*times;
		}
	}
	public static void main22(String[] arg){
		
		double model=19;
		int b_num_count=5;
		boolean a_addInit=true;
		//b 套a
		double b_profit_rate=Arith.div(model-b_num_count*2, b_num_count*2);//0.089;//Arith.div(1.6, 18);
		double a_profit_rate=Arith.div(model-(10-b_num_count)*2, (10-b_num_count)*2);//8.8;//Arith.div(17.6,2);
		double a_next_rate=Arith.div(b_num_count*2, model-b_num_count*2);//11.25;//Arith.div(18, 1.6);
		//double b_next_rate=0.083;
		
		double a_init_buy=1*b_num_count;
		double b_init_buy=a_next_rate*b_num_count;
		
		double sum_kui=0;
		double sum_yin=0;
		double a_nextbuy=a_init_buy;
		double b_nextbuy=b_init_buy;//196
		
		for(int i=1;i<=8;i++){
			System.out.println("i="+i+"     a_next:"+a_nextbuy+";b_next:"+b_nextbuy);
			double b_pro=Arith.mul(b_nextbuy,b_profit_rate);
			double a_pro=Arith.mul(a_nextbuy,a_profit_rate);
			
			sum_kui=sum_kui+Arith.sub(b_nextbuy, a_pro);
			sum_yin=Arith.add(sum_yin,a_pro);
			
			System.out.println("i="+i+"     but_total_kui:"+sum_kui+";a_yin:"+sum_yin);
			if(a_addInit){
				a_nextbuy=Arith.add(sum_yin, a_init_buy);
			}else{
				a_nextbuy=a_init_buy;
			}
			b_nextbuy=Arith.mul(Arith.add(sum_kui,a_nextbuy),a_next_rate);
		}
	}
	/*
	 * 对冲
	 */
	public static void main12(String[] arg){
		int duichongqishu=8;
		double init_buy=10;
		double next_rate=1.02;
		double profit_rate=0.98;
		boolean a_addInit=true;
		
		double sum_kui=0;
		double sum_yin=0;
		double a_nextbuy=init_buy;
		double b_nextbuy=init_buy*next_rate;//196
		
		for(int i=1;i<=14;i++){
			System.out.println("i="+i+"     a_next:"+a_nextbuy+";b_next:"+b_nextbuy);
			if(i<=duichongqishu){
				sum_kui=sum_kui+Arith.sub(b_nextbuy, Arith.mul(a_nextbuy,profit_rate));
				sum_yin=Arith.add(sum_yin, Arith.mul(a_nextbuy, profit_rate));
			}else{
				sum_kui=sum_kui+b_nextbuy;
				sum_yin=0;
			}
			
			
			System.out.println("i="+i+"     but_total_kui:"+sum_kui+";a_yin:"+sum_yin);
			if(i<=duichongqishu-1){
				if(a_addInit){
					a_nextbuy=Arith.add(sum_yin, init_buy);
				}else{
					a_nextbuy=sum_yin;
				}
				b_nextbuy=Arith.mul(Arith.add(sum_kui, a_nextbuy),next_rate);
			}else{
				a_nextbuy=0;
				b_nextbuy=Arith.mul(sum_kui,next_rate);
			}
		}
	}
	
	public static void main2(String[] arg){
		double sum_kui=0;
		double nextbuy=1;
		for(int i=1;i<=14;i++){
			sum_kui=Arith.add(sum_kui, nextbuy);
			System.out.println("--"+nextbuy);
			System.out.println("------"+sum_kui);

			if(i==1){
				nextbuy=next(sum_kui,5,model19);
			}else{
				nextbuy=next(sum_kui,5,model19)-1;
			}
			nextbuy=nextformat( nextbuy, 5);
		}
	}
	
	public static void main(String[] arg){
		main111(null);
		//等6有40期；等7 20期
		double sum_kui=0;
		double sum_profit=0;
		double nextbuy=4;
		double qi1=16384;
		double rate=1.7;
		for(int i=1;i<=16;i++){
			if(i==13){
				//rate=2.4;
			}
			if(i==14){
				//rate=2.4;
			}
			if(i>1){
				double mongy=Arith.div(qi1, Math.pow(2, i-1), 2);
				sum_profit=Arith.add(sum_profit,Arith.mul(sum_kui, mongy));
			}
			
			sum_kui=Arith.add(sum_kui, nextbuy);
			System.out.println("i"+i+"--"+nextbuy+"     "+nextbuy);
			System.out.println("------"+sum_kui);
			System.out.println("------sum_profit-:"+sum_profit);
			
			nextbuy=Arith.div(Arith.mul(nextbuy, rate), 1,2);
		}
		
		System.out.println("------");
		System.out.println("------");
		 sum_kui=0;
		 sum_profit=0;
		 nextbuy=4;
		 qi1=16384;
		rate=1.7;
		for(int i=1;i<=16;i++){
			if(i==13){
				//rate=2.4;
			}
			if(i==14){
				//rate=2.4;
			}
			sum_kui=Arith.add(sum_kui, nextbuy);
			System.out.println("i"+i+"--"+nextbuy+"     "+nextbuy);
			double mongy=Arith.div(qi1, Math.pow(2, i-1), 2);
			sum_profit=Arith.add(sum_profit,Arith.mul(nextbuy,mongy)*0.95 );
			System.out.println("------"+sum_kui);
			System.out.println("------sum_profit:"+sum_profit);
			
			nextbuy=Arith.div(Arith.mul(nextbuy,rate), 1,2);
		}
	}
	public static void maina(String[] arg){
		double sum_kui=0;
		double sum_profit=0;
		double nextbuy=10;
		double qi1=262144;
		for(int i=1;i<=20;i++){
			sum_kui=Arith.add(sum_kui, nextbuy);
			System.out.println("i"+i+"--"+nextbuy+"     "+nextbuy);
			double mongy=Arith.div(qi1, Math.pow(2, i-1), 2);
			sum_profit=Arith.add(sum_profit,Arith.mul(nextbuy,mongy)*0.9 );
			System.out.println("------"+sum_kui);
			System.out.println("------sum_profit:"+sum_profit);
			nextbuy=Arith.div(Arith.mul(nextbuy, 1.24), 1,2);
		}
	}
	public static void main111(String[] arg){
		//main2(null);
		//System.out.println(nextbuy(7,8,8,14,true)[0]);
		//System.out.println(profit(12,6));
		//System.out.println(1.08/9*10);
		/*System.out.println(nextbuy(5,10,10,10,true)[1]);
		System.out.println("---------------------------");
		System.out.println(nextbuy(5,10,10,4,true)[1]);
		System.out.println("---------------------------");*/ 
		//System.out.println(nextbuy(9,3,3,3.6,false)[0]);
		//System.out.println(nextbuy(5,22,22,1,true)[1]);
		//System.out.println(nextbuy(9,3,3,18,true)[1]);
		//System.out.println(nextbuy(1900,100,100,100,2,false)[1]);
		//System.out.println(nextbuy(1920,1,2000,2000,0.2,true)[1]);
		/*System.out.println(nextbuyh5hao(12,22,10.0,true)[0]);
		System.out.println(nextbuyh5hao(12,22,10,true)[1]);
		System.out.println(nextbuyh5hao(1,10,10,true)[0]);
		System.out.println(nextbuyh5hao(1,10,10,true)[1]);
		System.out.println(nextbuyh5hao(2,10,10,true)[0]);
		System.out.println(nextbuyh5hao(2,10,10,true)[1]);
		System.out.println(nextbuyh5hao(3,10,10,true)[0]);
		System.out.println(nextbuyh5hao(3,10,10,true)[1]);
		System.out.println(nextbuyh5hao(4,10,10,true)[0]);
		System.out.println(nextbuyh5hao(4,10,10,true)[1]);
		System.out.println(nextbuyh5hao(5,10,10,true)[0]);
		System.out.println(nextbuyh5hao(5,10,10,true)[1]);
		System.out.println(nextbuyh5hao(6,10,10,true)[0]);
		System.out.println(nextbuyh5hao(6,10,10,true)[1]);
		System.out.println(nextbuyh5hao(7,10,10,true)[0]);
		System.out.println(nextbuyh5hao(7,10,10,true)[1]);
		System.out.println(nextbuyh5hao(8,10,10,true)[0]);
		System.out.println(nextbuyh5hao(8,10,10,true)[1]);
		System.out.println(nextbuyh5hao(9,10,10,true)[0]);
		System.out.println(nextbuyh5hao(9,10,10,true)[1]);
		System.out.println(nextbuyh5hao(10,10,10,true)[0]);
		System.out.println(nextbuyh5hao(10,10,10,true)[1]);
		System.out.println("---------------------------");
		System.out.println(nextbuyh5hao(0,8,10,false)[0]);
		System.out.println(nextbuyh5hao(0,8,10,false)[1]);
		System.out.println(nextbuyh5hao(1,8,10,false)[0]);
		System.out.println(nextbuyh5hao(1,8,10,false)[1]);
		System.out.println(nextbuyh5hao(2,8,10,false)[0]);
		System.out.println(nextbuyh5hao(2,8,10,false)[1]);
		System.out.println(nextbuyh5hao(3,8,10,false)[0]);
		System.out.println(nextbuyh5hao(3,8,10,false)[1]);
		System.out.println(nextbuyh5hao(4,8,10,false)[0]);
		System.out.println(nextbuyh5hao(4,8,10,false)[1]);
		System.out.println(nextbuyh5hao(5,8,10,false)[0]);
		System.out.println(nextbuyh5hao(5,8,10,false)[1]);
		System.out.println(nextbuyh5hao(6,8,10,false)[0]);
		System.out.println(nextbuyh5hao(6,8,10,false)[1]);
		System.out.println(nextbuyh5hao(7,8,10,false)[0]);
		System.out.println(nextbuyh5hao(7,8,10,false)[1]);
		System.out.println(nextbuyh5hao(9,8,10,false)[0]);
		System.out.println(nextbuyh5hao(9,8,10,false)[1]);*/
		//System.out.println(nextbuy(4,16,22,8,true)[0]);
		//System.out.println(nextbuy(4,16,22,8,true)[1]);
		//System.out.println(nextbuy(9,2,3,180,true)[0]);
		//System.out.println(nextbuy(9,1,3,180,true)[1]);
		//System.out.println(getInitBuyMoney("123456789",70000));
		//System.out.println(getBack("12345678")*16);
		/*System.out.println(lv(8));*/
		//System.out.println(profit(16,8));
		//System.out.println(2*getBack("2"));
		//System.out.println(nextbuy(1,20.12,1));
		/*StringBuffer sb=new StringBuffer();
		for(int i=1;i<=5;i++){
			for(int j=1;j<=9;j++){
				sb.append("max(f"+i+""+j+"_buy_time),");
			}
		}
		for(int i=1;i<=5;i++){
			for(int j=1;j<=9;j++){
				sb.append("max(f"+i+""+j+"_buy_money),");
			}
		}
		for(int i=1;i<=5;i++){
			for(int j=1;j<=9;j++){
				sb.append("max(f"+i+""+j+"_buyed),");
			}
		}
		for(int i=1;i<=5;i++){
			for(int j=1;j<=9;j++){
				sb.append("max(f"+i+""+j+"_buy_time),");
			}
		}
		System.out.println(sb.toString());*/
		
		//System.out.println(nextbuy(7,12,12,0.14,false)[0]);
		//System.out.println(nextbuy(7,12,12,0.14,false)[1]);
		//System.out.println(getBack("123456")*12);
		/*System.out.println(nextbuy(5,1,3,10,true)[0]);
		System.out.println(nextbuy(5,1,3,10,true)[1]);
		System.out.println(nextbuy(5,2,3,10,true)[0]);
		System.out.println(nextbuy(5,2,3,10,true)[1]);
		System.out.println(nextbuy(5,3,3,10,true)[0]);
		System.out.println(nextbuy(5,3,3,10,true)[1]);*/
		/*System.out.println(nextbuyjieti(1,1,6,0));
		System.out.println(nextbuyjieti(1,2,6,0));
		System.out.println(nextbuyjieti(1,3,6,0));
		System.out.println(nextbuyjieti(2,1,6,0));
		System.out.println(nextbuyjieti(2,2,6,0));
		System.out.println(nextbuyjieti(2,3,6,0));
		System.out.println(nextbuyjieti(3,1,6,0));
		System.out.println(nextbuyjieti(3,2,6,0));
		System.out.println(nextbuyjieti(3,3,6,0));
		
		System.out.println(shouldbuy(14,6));*/
		//System.out.println(nextbuy(5,1,4,5*2,true)[0]);
		//System.out.println( nextbuy(7,4,4,14,true));
		
		//nextbuy1persent(5,12,12,5*2);
		//System.out.println(next(10,5));
		System.out.println( nextbuy(5,16,16,1,true,20)[1]);
	}
	
	/*
	 * 输入资金得到某期能买多少
	 */
	public static double canbuy(double money,int howmanynums,int times,int maxtime,boolean mustprofit,double model){
		double lost=nextbuy(howmanynums,maxtime,maxtime,1,mustprofit,model)[1];
		double init=Arith.div(money, lost,2);
		if(times==1){
			init=Arith.mul(Math.ceil(Arith.div(init, howmanynums*0.02)), howmanynums*0.02);
		}
		return nextformat(nextbuy(howmanynums,times,maxtime,init,mustprofit,model)[0],howmanynums);
	}
	
	public static double[] nextbuy1persent(int howmanynums,int times,int maxtime,double initbuy,double model){
		//System.out.println("times=="+times);
		if(times>maxtime){
			return new double[]{0,0};
		}
		double sum_kui=0;
		double nextbuy=initbuy;
		for(int i=1;i<=maxtime;i++){
			sum_kui=Arith.add(sum_kui, nextbuy);
			System.out.println("--"+nextbuy+"     "+Arith.div(nextbuy, initbuy));
			System.out.println("------"+sum_kui);
			if(i==times){
				return new double[]{nextbuy,sum_kui};
			}
			nextbuy=Arith.div( next(sum_kui,howmanynums,model),1,2)-(i-1)*9;
			//nextbuy=nextformat( nextbuy, howmanynums);
			if(nextbuy<initbuy){
				nextbuy=initbuy;
			}
			
		}
		return new double[]{0,0};
	}

	public static double nextbuy(int numcount,double lossed,int way,double model){
		double init=pernumbuy*numcount*way;
		double lv=lv(numcount,model);
		if(lossed<=Arith.mul(init,lv)){
			return init;
		}
		return Arith.div(lossed,lv);
	}
	/*
	 * numcount=8;
	 * res:0.2
	 */
	public static double lv(int numcount,double model){
		return Arith.div((model-numcount*pernumbuy), (numcount*pernumbuy),3);
	}

	public static double profit(double buyed,int numcount,double model){
		return Arith.div(Arith.mul(buyed, lv(numcount,model)), 1, 2) ;
	}
	
	public static double shouldbuy(double profit,int numcount,double model){
		return Arith.div(Arith.div(profit, lv(numcount,model)), 1, 2) ;
	}
	
	public static double nextbuyjieti(int howmanynums,int times,int status,double loss,double currbuymoney,int fails){
		
		if(times<=2||fails>=16){
			return nextbuy(howmanynums,loss,1,model19);
		}
		
		if(status==1){
			return currbuymoney;
		}else{
			return Arith.div(nextbuy(howmanynums,loss,1,model19), 2);
		}
	}
	
	/*
	 * howmanynums:5个号码
	 * times:2当前第2期
	 * maxtime：12最大12期
	 * initbuy：第一期买
	 * mustProfit必须盈利
	 */
	public static double[] nextbuy(int howmanynums,int times,int maxtime,double initbuy,boolean mustProfit,double model){
		//System.out.println("times=="+times);
		if(times>maxtime){
			return new double[]{0,0};
		}
		double sum_kui=0;
		double nextbuy=initbuy;
		for(int i=1;i<=maxtime;i++){
			sum_kui=Arith.add(sum_kui, nextbuy);
			System.out.println("--"+nextbuy+"     "+Arith.div(nextbuy, initbuy)*10);
			System.out.println("------"+sum_kui);
			if(i==times){
				return new double[]{nextbuy,sum_kui};
			}
			nextbuy=Arith.div( Arith.add(next(sum_kui,howmanynums,model), mustProfit?initbuy:0),1,2);
			nextbuy=nextformat( nextbuy, howmanynums);
			if(nextbuy<initbuy){
				nextbuy=initbuy;
			}
			
		}
		return new double[]{0,0};
	}
	
	public static double[] nextbuy(double model,int howmanynums,int times,int maxtime,double initbuy,boolean mustProfit){
		//System.out.println("times=="+times);
		if(times>maxtime){
			return new double[]{0,0};
		}
		double sum_kui=0;
		double nextbuy=initbuy;
		for(int i=1;i<=maxtime;i++){
			sum_kui=Arith.add(sum_kui, nextbuy);
			System.out.println("--"+nextbuy+"     "+Arith.div(nextbuy, initbuy)*10);
			System.out.println("------"+sum_kui);
			if(i==times){
				return new double[]{nextbuy,sum_kui};
			}
			nextbuy=Arith.div( Arith.add(next(model,sum_kui,howmanynums), mustProfit?initbuy:0),1,2);
			nextbuy=nextformat( nextbuy, howmanynums);
			if(nextbuy<initbuy){
				nextbuy=initbuy;
			}
			
		}
		return new double[]{0,0};
	}
	
	public static double nextbuyjieti(int bigtimes,int smalltimes,int num,double add,double model){
		double init=nextbuy(num,3,4,num*2,true,model)[1];
		double big=Arith.add(nextbuy(num,bigtimes,bigtimes+1,init,true,model)[0], add);
		double rate=nextbuy(num,smalltimes,smalltimes+1,num*2,true,model)[0];;
		return Arith.div( Arith.mul(big, Arith.div(rate, init)),1,2);
	}

	
	private static double next(double sum_kui,int howmanynums,double model){
		return Arith.div(sum_kui*howmanynums*2,(model-howmanynums*2));
	}
	
	public static double next(double model,double sum_kui,int howmanynums){
		return Arith.div(sum_kui*howmanynums*2,(model-howmanynums*2));
	}
	
	public static double nextformat(double nextbuy,int howmanynums){
		return Arith.mul(((int)Arith.div(nextbuy, 0.02*howmanynums)), 0.02*howmanynums);
	}
	
	/*
	 * 5=>1.92
	 */
	public static double getBack(String buyNum,double model){
		if(buyNum==null||buyNum.length()==0){
			return 0;
		}
		return Arith.div(model, buyNum.length()*2,3);
	}
	
	
	//---------------------------------------------------
	
	public static double rateofreturn(int numcount,double model){
		return Arith.div((model-numcount*pernumbuy), (numcount*pernumbuy),2);
	}
	
	public static double nextbuy(int numcount,double lossed,double initbuy,boolean plusbuy,int digit,double model){
		return nextbuy( rateofreturn(numcount,model), lossed, initbuy, plusbuy, digit);
	}
	
	/*
	 * buytims:当前第3期，return 第3期买多少
	 */
	public static double buy(int numcount,int buytims,double initbuy,boolean plusbuy,int digit,double model){
		if(buytims==1){
			return initbuy;
		}
		double lossed=initbuy;
		for(int i=2;i<buytims;i++){
			lossed=Arith.add(     nextbuy(numcount,lossed,initbuy, plusbuy, digit)  , lossed)  ;
		}
		return nextbuy( rateofreturn(numcount,model), lossed, initbuy, plusbuy, digit);
	}

	/*
	 * rateofreturn=0.7 or 0.92;plusbuy like initbuy;
	 */
	public static double nextbuy(double rateofreturn,double lossed,double initbuy,boolean plusbuy,int digit){
		if(lossed==0){
			return initbuy;
		}
		/*if(lossed<=Arith.mul(initbuy,rateofreturn)){
			return initbuy;
		}*/
		double res=Arith.div(lossed,rateofreturn,digit);
		if(plusbuy){
			res=Arith.add(res, initbuy);
		}
		return res;
	}

	/*
	 * 10,0.92=>9.2
	 */
	public static double profit(double buyed,double rateofreturn){
		return Arith.div(Arith.mul(buyed, rateofreturn), 1, 2) ;
	}
	
	/*
	 * 10,0.92=>19.2
	 */
	public static double getBack(double buyed,double rateofreturn){
		if(buyed==0){
			return 0;
		}
		return Arith.mul(buyed,Arith.add(rateofreturn, 1));
	}
}
