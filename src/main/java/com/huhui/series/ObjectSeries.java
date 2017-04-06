package com.huhui.series;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
 * 生产者消费者队列，最多只存x个数据，多于的将丢弃；是一个数组，读写都有一个索引，操作后索引加1；
 * 读线程用读索引，写线程用写索引，不用同步，只支持各一个读和写线程，效率高
 */
public class ObjectSeries {
	
	private Object[] dataSeries=null;
	
	private int capacity=1;
	
	private int hasWritedIndex=-1;
	
	private int hasReadIndex=-1;
	
	//private Integer writelock=0;
	
	private List list=null;
	
	private boolean changed=false;
		
	private int listCatheMillis=0;
	
	private long lastReceived=0;
	
	private boolean print =true;

	public ObjectSeries(int capacity,int listCatheMillis) {
		super();
		this.capacity = capacity;
		dataSeries=new Object[capacity] ;
		this.listCatheMillis=listCatheMillis;
	}
	
	public ObjectSeries(int capacity) {
		super();
		this.capacity = capacity;
		dataSeries=new Object[capacity] ;
	}
	
	public void write(Object data) {
		if(data==null){
			return ;
		}
		hasWritedIndex+=1;
		if(hasWritedIndex>=capacity){
			hasWritedIndex=0;
		}
		dataSeries[hasWritedIndex]=data;
		changed=true;
		lastReceived=System.currentTimeMillis();	
		if(print)System.out.println("w"+hasWritedIndex);
	}
	
	//读已经写了的
	public Object read() {
		return read(false);
	}
	
	//读已经写了的
	public Object read(boolean remove) {
		if(hasReadIndex+1==capacity){
			if(dataSeries[0]==null){
				if(hasReadIndex!=this.hasWritedIndex){
					hasReadIndex=hasWritedIndex;
				}
				return null;
			}
		}else if(dataSeries[hasReadIndex+1]==null){
			if(hasReadIndex!=this.hasWritedIndex){
				hasReadIndex=hasWritedIndex;
			}
			return null;
		}
		//已经读完
		hasReadIndex+=1;
		if(hasReadIndex>=capacity){
			hasReadIndex=0;
		}
		Object res=dataSeries[hasReadIndex];
		if(remove){
			dataSeries[hasReadIndex]=null;
		}
		if(print)System.out.println("r"+hasReadIndex);
		return res;
	}
	
	//非生产者消费者模式，index=0表示当前写的位置，index=-1表示上一次写的位置
	public Object get(int index) {
		int theindex=hasWritedIndex+index;
		if(theindex<0){
			theindex=this.capacity+theindex;
		}
		return dataSeries[theindex];
	}
	
	public void remove(Object data) {
		if(data==null){
			return ;
		}
		for(int i=0;i<capacity;i++){
			if(dataSeries[i]!=null&&dataSeries[i]==data){
				dataSeries[i]=null;
				changed=true;
			}
		}
	}
	
	public List getList(){
		return getList(this.capacity);
	}
	
	public List getList(int count){
		if(hasWritedIndex==-1){
			return null;
		}
		
		List list=new ArrayList();
		for(int i=hasWritedIndex;i>=0;i--){
			if(dataSeries[i]!=null){
				list.add(dataSeries[i]);
			}
		}
		for(int i=capacity-1;i>=hasWritedIndex+1;i--){
			if(dataSeries[i]!=null){
				list.add(dataSeries[i]);
			}
		}
		
		if(list.size()<count){
			return list;
		}
		
		//缓存20取10
		if(list.size()>count){
			List res=new ArrayList();
			for(int i=0;i<count;i++){
				res.add(list.get(i));
			}	
			return res;
		}
		return list;
	}
	
	public List getList(boolean desc,boolean useCache){
		if(hasWritedIndex==-1){
			return null;
		}
		
		if(list!=null&&useCache&&(System.currentTimeMillis()-this.lastReceived)<=this.listCatheMillis){
			return list;
		}
		
		if(list==null||changed){
			list=new ArrayList();
			if(desc){
				for(int i=hasWritedIndex;i>=0;i--){
					if(dataSeries[i]!=null){
						list.add(dataSeries[i]);
					}
				}
				for(int i=capacity-1;i>=hasWritedIndex+1;i--){
					if(dataSeries[i]!=null){
						list.add(dataSeries[i]);
					}
				}
				
			}else{
				for(int i=hasWritedIndex+1;i<capacity;i++){
					if(dataSeries[i]!=null){
						list.add(dataSeries[i]);
					}
				}
				for(int i=0;i<=hasWritedIndex;i++){
					if(dataSeries[i]!=null){
						list.add(dataSeries[i]);
					}
				}
			}
			changed=false;
		}

		return list;
	}
	
	/*
	 * 从后到前top多少是否等于o
	 */
	public boolean equalTop(int top,Object o){
		if(top>this.capacity){
			top=capacity;
		}
		
		if(hasWritedIndex==-1){
			return false;
		}
		
		if(hasWritedIndex==this.capacity-1||this.dataSeries[hasWritedIndex+1]==null||hasWritedIndex+1>=top){
			for(int i=hasWritedIndex-top+1;i<=hasWritedIndex;i++){
				if(i>=0&&this.dataSeries[i]!=null&&this.dataSeries[i].equals(o)){
					return true;
				}
			}
		}else{
			for(int i=0;i<=hasWritedIndex;i++){
				if(this.dataSeries[i]!=null&&this.dataSeries[i].equals(o)){
					return true;
				}
			}
			int morecount=top-hasWritedIndex-1;
			for(int i=this.capacity-1;i>this.capacity-morecount;i--){
				if(this.dataSeries[i]!=null&&this.dataSeries[i].equals(o)){
					return true;
				}
			}
		}
		return false;
	}
	
	/*
	 * 从前到后 top多少是否等于o
	 */
	public boolean equalBottom(int top,Object o){
		if(top>this.capacity){
			top=capacity;
		}
		
		if(hasWritedIndex==-1){
			return false;
		}
		
		if(hasWritedIndex==this.capacity-1||this.dataSeries[hasWritedIndex+1]==null){
			for(int i=0;i<top;i++){
				if(this.dataSeries[i]!=null&&this.dataSeries[i].equals(o)){
					return true;
				}
			}
		}else{
			int morecount=(this.capacity-hasWritedIndex-1)-top;
			int to=morecount<=0?this.capacity:(hasWritedIndex+1+top);
			for(int i=hasWritedIndex+1;i<to;i++){
				if(this.dataSeries[i]!=null&&this.dataSeries[i].equals(o)){
					return true;
				}
			}
			
			if(morecount<0){
				for(int i=0;i<Math.abs(morecount);i++){
					if(this.dataSeries[i]!=null&&this.dataSeries[i].equals(o)){
						return true;
					}
				}
			}
			
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "ObjectSeries [dataSeries=" + Arrays.toString(dataSeries)
				+ ", capacity=" + capacity + ", hasWritedIndex="
				+ hasWritedIndex + ", hasReadIndex=" + hasReadIndex
				+ ", writeplusreadindex="  + ", list="
				+ list + ", changed=" + changed + ", listCatheMillis="
				+ listCatheMillis + ", lastReceived=" + lastReceived + "]";
	}

	public static void main(String[] args)
    {
		main1(args);
		
    }
	
	public static void main2(String[] args)
    {
		ObjectSeries ob=new ObjectSeries(10);
		for(int i=0;i<10;i++){
			ob.write(String.valueOf(i));
		}
		System.out.println(ob.equalBottom(10, "1"));
		System.out.println(ob.equalBottom(4, "3"));
		System.out.println(ob.equalTop(10, "7"));
		System.out.println(ob.equalTop(3, "2"));
    }
	
	public static void main1(String[] args)
    {
		boolean test=true;
		ObjectSeries a=new ObjectSeries(10,0);
		if(test)System.out.println(a.getList(10));
		a.write("0");
		if(test)System.out.println(a.getList(10));
		a.write("1");
		
		System.out.println(a.toString());
		System.out.println(a.read());
		
		if(test)System.out.println(a.getList(10));
		a.write("2");
		if(test)System.out.println(a.getList(10));
		a.write("3");
		if(test)System.out.println(a.getList(10));
		a.write("4");
		if(test)System.out.println(a.getList(10));
		a.write("5");
		if(test)System.out.println(a.getList(10));
		a.write("6");
		if(test)System.out.println(a.getList(10));
		a.write("7");
		
		System.out.println(a.toString());
		System.out.println(a.read());
		
		if(test)System.out.println(a.getList(10));
		a.write("8");
		if(test)System.out.println(a.getList(10));
		a.write("9");
		a.write("10");
		a.write("11");
		a.write("12");
		a.write("13");
		a.write("14");
		a.write("15");
		a.write("16");
		a.write("17");
		//System.out.println(a.toString());
		if(test)System.out.println(a.getList(5));
		if(test)System.out.println(a.getList(10));
		/*String res=(String)a.read();
		while(res!=null){
			System.out.println(a.toString());
			System.out.println(res);
			res=(String)a.read();
		}*/
    }
}
