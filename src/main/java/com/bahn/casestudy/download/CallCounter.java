package com.bahn.casestudy.download;

public class CallCounter {
	
	private static final CallCounter SINGLETON = new CallCounter();
	private int count;
	
	
	private CallCounter() {
		count = 0;
	}
	
	
	public void callOccured() {
		count++;
	}
	
	public int getCount() {
		return count;
	}
	
	public static CallCounter getInstance() {
		return SINGLETON;
	}
}
