package com.deepak.utils;

import org.springframework.stereotype.Service;

import com.deepak.modal.User;

@Service("CustomerInfo")
public class CustomerInfo {

	public static User getUserInContext(){
		User userDao =ApplicationContextProvider.getApplicationContext().getBean(User.class);
     	System.out.println("User : "+userDao);
		return null;//userDao.findByUserName(getCustomerUsername()); 
	}
}
