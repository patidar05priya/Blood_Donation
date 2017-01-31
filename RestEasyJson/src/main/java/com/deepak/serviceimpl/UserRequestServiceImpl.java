package com.deepak.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deepak.dao.IUserRequestDao;
import com.deepak.modal.DonationActivity;
import com.deepak.modal.DonationActivity.Status;
import com.deepak.modal.User.BLOODGROUP;
import com.deepak.service.IUserRequestService;

@Service
public class UserRequestServiceImpl implements IUserRequestService {
	
	@Autowired
	IUserRequestDao userRequestDao;
	
	@Override
	public String getUserRequest(DonationActivity activity){
		System.out.println("Inside UserRequestServiceImpl ");
		activity.setStatus(DonationActivity.Status.PENDING);
		return  userRequestDao.getUserRequest(activity);
	}

	@Override
	public List<DonationActivity> getUserActiveRequest(BLOODGROUP bloodgroup) {
		return userRequestDao.getUserActiveRequest(bloodgroup);
	}

	@Override
	public List<DonationActivity> getUserSubmittedRequest(Integer userid) {
		 return userRequestDao.getUserSubmittedRequest(userid);
	}
}
