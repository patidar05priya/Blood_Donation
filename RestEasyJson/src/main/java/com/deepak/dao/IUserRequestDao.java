package com.deepak.dao;

import java.util.List;

import com.deepak.modal.DonationActivity;
import com.deepak.modal.User.BLOODGROUP;

public interface IUserRequestDao {

	String getUserRequest(DonationActivity activity);

	Boolean findExistingRequest(DonationActivity activity);

	List<DonationActivity> getUserActiveRequest(BLOODGROUP bloodgroup);

	List<DonationActivity> getUserSubmittedRequest(Integer userid);

}
