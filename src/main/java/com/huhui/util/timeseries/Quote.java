package com.huhui.util.timeseries;

public class Quote {
	private double open;
	private double high=0;
	private double low=0;
	private double close;
	private double volume;
	private double amount;
	private int sourceid;
	private String ser_time;
	private boolean created=false;
	
	private double open_ask;
	private double high_ask=0;
	private double low_ask=0;
	private double close_ask;
	
	public void receive(double open,double high,double low,double close,double volume,double amount,int sourceid,String ser_time){
		this.open=open;
		this.high=high;
		this.low=low;
		this.close=close;
		this.volume=volume;
		this.amount=amount;
		this.ser_time=ser_time;
		this.sourceid=sourceid;
	}
	public void copyFrom(Quote o){
		
		if(this.open==0){
			this.open=o.open;
		}
		
		if(this.high<o.high&&o.high!=0){
			this.high=o.high;
		}
		if(this.low>o.low&&o.low!=0){
			this.low=o.low;
		}
		
		this.close=o.close;
		this.open_ask=o.open_ask;
		if(this.high_ask<o.high_ask&&o.high_ask!=0){
			this.high_ask=o.high_ask;
		}
		if(this.low_ask>o.low_ask&&o.low_ask!=0){
			this.low_ask=o.low_ask;
		}

		this.close_ask=o.close_ask;
		
		this.volume=o.volume;
		this.amount=o.amount;
		this.ser_time=o.ser_time;
		this.sourceid=o.sourceid;
	}
	public double getOpen() {
		return open;
	}
	public void setOpen(double open) {
		this.open = open;
	}
	public double getHigh() {
		return high;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
	}
	public double getClose() {
		return close;
	}
	public void setClose(double close) {
		this.close = close;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getSourceid() {
		return sourceid;
	}
	public void setSourceid(int sourceid) {
		this.sourceid = sourceid;
	}
	public String getSer_time() {
		return ser_time;
	}
	public void setSer_time(String ser_time) {
		this.ser_time = ser_time;
	}
	public boolean isCreated() {
		return created;
	}
	public void setCreated(boolean created) {
		this.created = created;
	}
	
	public String toString(boolean isask) {
		return ser_time+","+(isask?this.getOpen_ask():this.getOpen())+","+(isask?this.getHigh_ask():this.getHigh())+","+(isask?this.getLow_ask():this.getLow())+","+(isask?this.getClose_ask():this.getClose())+","+this.getVolume();
	}
	public double getOpen_ask() {
		return open_ask;
	}
	public void setOpen_ask(double open_ask) {
		this.open_ask = open_ask;
	}
	public double getHigh_ask() {
		return high_ask;
	}
	public void setHigh_ask(double high_ask) {
		this.high_ask = high_ask;
	}
	public double getLow_ask() {
		return low_ask;
	}
	public void setLow_ask(double low_ask) {
		this.low_ask = low_ask;
	}
	public double getClose_ask() {
		return close_ask;
	}
	public void setClose_ask(double close_ask) {
		this.close_ask = close_ask;
	}
	
	
}
