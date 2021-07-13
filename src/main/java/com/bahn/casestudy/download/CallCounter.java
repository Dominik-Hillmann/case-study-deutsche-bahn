package com.bahn.casestudy.download;

/**
 * A singleton whose object gets incremented on an API call.
 */
public class CallCounter {
	/** The singleton object. */
	private static final CallCounter SINGLETON = new CallCounter();
	/** The counter. */
	private int count;
	/** After how many calls an action should happen. */
	private final int CHECK_RATE = 20;
	
	
	/** 
	 * The non accessable constructor for the singleton.
	 */
	private CallCounter() {
		count = 0;
	}
	
	
	/** 
	 * Method that gets called on an API call.
	 */
	public void callOccured() {
		count++;
	}
	
	
	/**
	 * Fetches the current count.
	 * @return The current count.
	 */
	public int getCount() {
		return count;
	}
	
	
	/**
	 * Get the single instance.
	 * @return The instance.
	 */
	public static CallCounter getInstance() {
		return SINGLETON;
	}
	
	
	/**
	 * Returns {@code true} every {@code CHECK_RATE} times.
	 */
	public boolean counterDivisable() {
		return count % CHECK_RATE == 0;
	}
}
