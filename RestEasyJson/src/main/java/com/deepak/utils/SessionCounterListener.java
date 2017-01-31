package com.deepak.utils;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionCounterListener implements HttpSessionListener, HttpSessionContext{

	private static int totalActiveSessions;
	
	  public static int getTotalActiveSession(){
		return totalActiveSessions;
	  }
		
	  @Override
	  public void sessionCreated(HttpSessionEvent arg0) {
		totalActiveSessions++;
		System.out.println("sessionCreated - add one session into counter");
	  }

	  @Override
	  public void sessionDestroyed(HttpSessionEvent arg0) {
		totalActiveSessions--;
		System.out.println("sessionDestroyed - deduct one session from counter");
	  }

		@Override
		public Enumeration getIds() {
			// TODO Auto-generated method stub
			return null;
		}
	
		@Override
		public HttpSession getSession(String arg0) {
			// TODO Auto-generated method stub
			return null;
		}
}
