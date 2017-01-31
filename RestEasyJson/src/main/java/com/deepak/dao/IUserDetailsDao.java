package com.deepak.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.deepak.daoimpl.Dao;
import com.deepak.modal.User;
import com.deepak.utils.UserDetails;

@Dao
public interface IUserDetailsDao {
	
	public UserDetails getUserDetails(String userName, String pwd);

	String getForgotPassword(String emailId);

}
