package edu.uwec.cs355.group13.service;

/*
 * Timer - class to time execution of Java code in milliseconds
 *
 * Created on Dec 2, 2003 - Paul J. Wagner
 */


import java.util.Date;

/**
 * @author WAGNERPJ
 */
public class Timer {
	private long startTime;			// start time in absolute milliseconds
	private long stopTime;			// stop time in absolute milliseconds
	private long totalTime;			// difference of stop and start time
	
	// --- default constructor
	public Timer() {
		startTime = 0;
		stopTime = 0;
		setTotalTime(0);
	}
	
	// --- startTimer - get a starting time
	public void startTimer() {
		Date now = new Date();
		startTime = now.getTime();
	}

	// --- stopTimer - get an ending time
	public void stopTimer() {
		Date now = new Date();
		stopTime = now.getTime();
	}

	// --- getTotal - return the elapsed time
	public long getTotal() {
		long result = 0;
		if (stopTime > startTime) {
			result = stopTime - startTime;
		}
		return result;
	}

	public long getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(long totalTime) {
		this.totalTime = totalTime;
	}
}	// end - class Timer