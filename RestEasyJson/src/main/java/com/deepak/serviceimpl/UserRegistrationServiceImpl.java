package com.deepak.serviceimpl;

import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.deepak.dao.IUserRegistrationDao;
import com.deepak.daoimpl.UserRegistrationDaoImpl;
import com.deepak.modal.User;
import com.deepak.modal.User.BLOODGROUP;
import com.deepak.modal.User.USERTYPE;
import com.deepak.service.IUserDetailsService;
import com.deepak.service.IUserRegistrationService;

@Configurable
@Component
@Service("UserRegistrationServiceImpl")
public class UserRegistrationServiceImpl implements IUserRegistrationService{

	@Autowired
	IUserRegistrationDao userRegistrationDao;
	@Override
	public String userRegistration( String fname,String lname,BLOODGROUP bloodGroup,USERTYPE userType,String address,String areaCode,String phoneNumber,String emailId, Float age,Float weight){
		System.out.println("inside userRegistration  Service ");
		//UserRegistrationDaoImpl daoImpl = new UserRegistrationDaoImpl();
			return userRegistrationDao.userRegistration(fname, lname, bloodGroup, userType, address, areaCode, phoneNumber, emailId, age, weight);
	}
	@Override
	public String isUserIdAvailable(String userId) {
		if (userId != null) {
			//UserRegistrationDaoImpl daoImpl = new UserRegistrationDaoImpl();
			userId = userId.trim();
			String msg = userRegistrationDao.isUserIdAvailable(userId);
			return msg;
		} else {
			return "Incorrect User Id";
		}
	}
	@Override
	public User getUserDetails(String userId) {
	//	UserRegistrationDaoImpl daoImpl = new UserRegistrationDaoImpl();
		User user = userRegistrationDao.getUserDetails(userId);
		return user;
	}
	@Override
	public String updateUserProfile(User user) {
		//UserRegistrationDaoImpl daoImpl = new UserRegistrationDaoImpl();
		String msg = "";
		if(user!=null){
			msg = userRegistrationDao.updateUserProfile(user);
		}
		else{
			msg = "Some Error Occurred Due To Invalid Request";
		}
		return msg;
	}
	@Override
	public String changeUserPassword(String emailId, String oldPassword,
			String newPassword) {
		//UserRegistrationDaoImpl daoImpl = new UserRegistrationDaoImpl();
		String msg = "";
		msg = userRegistrationDao.changeUserPassword(emailId,oldPassword,newPassword);
		return msg;
	}
	
}

