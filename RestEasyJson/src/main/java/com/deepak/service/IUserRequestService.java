package com.deepak.service;

import java.util.List;

import com.deepak.modal.DonationActivity;
import com.deepak.modal.User.BLOODGROUP;

public interface IUserRequestService {

	String getUserRequest(DonationActivity activity);

	List<DonationActivity> getUserActiveRequest(BLOODGROUP bloodgroup);

	List<DonationActivity> getUserSubmittedRequest(Integer userid);

}
