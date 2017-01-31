package com.deepak.service;

import org.springframework.stereotype.Service;

import com.deepak.modal.User;
import com.deepak.modal.User.BLOODGROUP;
import com.deepak.modal.User.USERTYPE;

@Service("IUserRegistrationService")
public interface IUserRegistrationService {

	String isUserIdAvailable(String userId);

	String userRegistration(String fname, String lname, BLOODGROUP bloodGroup,
			USERTYPE userType, String address, String areaCode,
			String phoneNumber, String emailId, Float age, Float weight);

	User getUserDetails(String userId);

	String updateUserProfile(User user);

	String changeUserPassword(String emailId, String oldPassword,
			String newPassword);
}
