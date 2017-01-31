package com.deepak.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.deepak.modal.User;
import com.deepak.utils.UserDetails;

@Service("IUserDetailsService")
public interface IUserDetailsService {
	
	public UserDetails getUserDetails(String userName , String pwd);

	String getForgotPassword(String emailId,String msg);
}
