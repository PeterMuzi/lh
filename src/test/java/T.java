import com.huhui.util.Arith;


public class T {
	public static void main(String[] arg)
    {
		double share=2;
		double lmaxLots=Arith.mul(Arith.div(1.206, 0.01),0.1) ;
		System.out.println(lmaxLots);
		//lmaxLots=Arith.mul((double)(int)Arith.div(lmaxLots,0.1),0.1);
		if(share<=1){
			lmaxLots=Arith.div((double)(int)Math.round(lmaxLots*1/share),1/share);
		}else{
			lmaxLots=Arith.mul((double)(int)Arith.div(lmaxLots, share),share);
		}
		
		//lmaxLots=Arith.div((double)(int)Arith.mul(lmaxLots,1/0.1),1/0.1);
		//lmaxLots=Arith.mul((double)(int)Arith.div(lmaxLots,0.1),0.1);
		System.out.println(lmaxLots);
		/*String datas="1'";
		datas=datas.replace("'", "''");
		//System.out.println(datas);
		List<String> list=new ArrayList();
		list.add("1");
		list.add(1,"2");
		System.out.print(datas);*/
		/*A a=new A();
		a.setA("s");
		String res=GenericRepository.updateSql(a, "bb", "t");
		System.out.print(res);*/
		//System.out.print("ab".substring(1,2));
		//System.out.print("http://img.wdjimg.com/mms/icon/v1/1/37/9e75108049a5069ebb305b2fb670d371_100_100.png".startsWith("http://img.wdjimg.com/mms/icon/v"));
    }
}
