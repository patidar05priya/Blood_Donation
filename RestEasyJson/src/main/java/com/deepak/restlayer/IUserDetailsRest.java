package com.deepak.restlayer;

import org.springframework.stereotype.Service;

import com.deepak.modal.User;
import com.deepak.modal.User.BLOODGROUP;
import com.deepak.modal.User.USERTYPE;

@Service
public interface IUserDetailsRest {
	public String getForgotPassword(String emailId);

	String userRegistration(User user);

	String checkUserIdAvailabilty(String userId);

	User getUserDetails(String userId);

	String updateUserProfile(User user);

	String changeUserPassword(String emailId, String oldPassword,
			String newPassword);


}
