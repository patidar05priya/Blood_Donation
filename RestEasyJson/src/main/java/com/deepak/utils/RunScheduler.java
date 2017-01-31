package com.deepak.utils;

import org.springframework.stereotype.Component;

@Component
public class RunScheduler {

	public void run1() {

	    try {
	    	System.out.println("Scheduler Run");

	    } catch (Exception e) {
		e.printStackTrace();
	    }

	  }
	
	public void run() {

	    try {
	    	System.out.println("Scheduler Run");

	    } catch (Exception e) {
		e.printStackTrace();
	    }

	  }
	
	public void runOnTime(){
		System.out.println("Scheduler runOnTime");
	}
}
