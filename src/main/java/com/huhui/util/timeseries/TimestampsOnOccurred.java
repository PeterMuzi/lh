package com.huhui.util.timeseries;

import java.util.ArrayList;
import java.util.List;

public class TimestampsOnOccurred  extends ArrayList<Quote> implements java.io.Serializable{

	public Quote createQuote(String time){
		int index=this.nearestIndexOfOccurredTime(time);
		Quote temp=new Quote();
		temp.setSer_time(time);
		this.add(index,temp);
		return temp;
	}
	
    public TimestampsOnOccurred(int initialCapacity) {
		super(initialCapacity);
	}
    private Quote add(int index,String time) {
    	Quote temp=new Quote();
		temp.setSer_time(time);
		this.add(index,temp);
		return temp;
    }
    public int nearestIndexOfOccurredTime(String time) {
    	if(this.isEmpty()){
    		return 0;
    	}
    	int size=size();
    	if(time.compareTo(this.get(0).getSer_time()) > 0){
    		return 0;
    	}
    	if(time.compareTo(this.get(size-1).getSer_time()) < 0){
    		return size;
    	}
        int from = 0;
        int to = size- 1;
        int length = to - from;
        while (length > 1) {
            length /= 2;
            final String midTime = get(from + length).getSer_time();
            if (time.compareTo(midTime) > 0) {
            	to=from + length-1;
            } else if (time.compareTo(midTime) < 0) {
            	from += length+1;
            } else {
                /** time == midTime */
                return from + length;
            }
            length = to - from;
        }
        
        /**
         * if we reach here, that means the time should between (start) and (start + 1),
         * and the length should be 1 (end - start). So, just do following checking,
         * if can't get exact index, just return nearest one: 'start'
         */
        if(from==to){
        	return  time.compareTo(this.get(from).getSer_time())>= 0?from:from+1;
        }
        for(int i=from;i<=to-1;i++){
        	if (time.compareTo(get(i).getSer_time())<0&&time.compareTo(get(i+1).getSer_time())>0) {
                return i+1;
            }
        }
        return from;
    }
    
    public static void main(String[] args){
    	TimestampsOnOccurred t=new TimestampsOnOccurred(5);
    	System.out.println(t.size());
    	//t.add("201602160735");
    //	t.add("201602160730");
    	//t.add("201602160731");
    	System.out.println(t.size());
    	t.add(t.nearestIndexOfOccurredTime("201602160735"),"201602160735");
    	t.add(t.nearestIndexOfOccurredTime("201602160730"),"201602160730");
    	t.add(t.nearestIndexOfOccurredTime("201602160736"),"201602160736");
    	t.add(t.nearestIndexOfOccurredTime("201602160738"),"201602160738");
    	t.add(t.nearestIndexOfOccurredTime("201602160729"),"201602160729");
    	t.add(t.nearestIndexOfOccurredTime("201602160737"),"201602160737");
    	t.add(t.nearestIndexOfOccurredTime("201602160732"),"201602160732");
    	t.add(t.nearestIndexOfOccurredTime("201602160733"),"201602160733");
    	System.out.println(t);
    	System.out.println(t.size());
    }
}
