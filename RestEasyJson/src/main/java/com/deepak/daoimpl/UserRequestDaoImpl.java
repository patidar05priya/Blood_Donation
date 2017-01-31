package com.deepak.daoimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import com.deepak.dao.IUserRequestDao;
import com.deepak.modal.DonationActivity;
import com.deepak.modal.User;
import com.deepak.modal.User.BLOODGROUP;
import com.deepak.utils.HibernateUtil;
@Dao
public class UserRequestDaoImpl extends JPABaseDao<Integer, DonationActivity> implements IUserRequestDao  {

	
	
	@Override
	public String getUserRequest(DonationActivity activity){
		System.out.println("Inside UserRequestDaoImpl ");
		Boolean isRequestExist = findExistingRequest(activity);
		DonationActivity requestactivity = null;
		System.out.println(isRequestExist);
		if(isRequestExist){
			activity.setCreationtime(new Date());
			requestactivity = create(activity);
		}
		System.out.println(requestactivity);
		if(requestactivity!=null){
		return "Request Submitted Successfully";
		}
		return "Some Error Occured";
	}
	
	@Override
	public Boolean findExistingRequest(DonationActivity activity){
		Integer userid = activity.getUserid();
		BLOODGROUP bloodgroup = activity.getBloodgroup();
		String userName = activity.getUsername();
		System.out.println("userid "+userid+" bloodgroup "+bloodgroup);
		EntityManager entityManager =  HibernateUtil.getEntityManager();
		Query query = entityManager.createNamedQuery("findExistingRequest", DonationActivity.class);
		List<DonationActivity> donationActivity = null;
		//query.setParameter("userid", userid)
		query.setParameter("userName", userName).setParameter("bloodgroup", bloodgroup).setParameter("status", DonationActivity.Status.PENDING);
		try{
			donationActivity = query.getResultList();
		}catch(NoResultException e){
			System.out.println("No Result fount in findExistingRequest in UserRequestDaoImpl");
			System.out.println(e);
			e.printStackTrace();
		}
		catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
		}
		System.out.println(donationActivity);
		System.out.println(donationActivity.size());
		if(donationActivity.size()==0){
			return true;
		}
		else{
			return false;
		}
	}
	
	@Override
	public List<DonationActivity> getUserActiveRequest(BLOODGROUP bloodgroup){
		EntityManager entityManager =  HibernateUtil.getEntityManager();
		Query query = entityManager.createNamedQuery("findActiveRequest", DonationActivity.class);
		List<DonationActivity> donationActivity = null;
		query.setParameter("bloodgroup", bloodgroup).setParameter("status", DonationActivity.Status.PENDING);
		try{
			donationActivity = query.getResultList();
		}catch(NoResultException e){
			System.out.println("No Result fount in getUserActiveRequest in UserRequestDaoImpl");
			System.out.println(e);
			e.printStackTrace();
		}
		catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
		}
		return donationActivity;
	}

	@Override
	public List<DonationActivity> getUserSubmittedRequest(Integer userid) {
		EntityManager entityManager =  HibernateUtil.getEntityManager();
		Query query = entityManager.createNamedQuery("findSubmittedRequest", DonationActivity.class);
		List<DonationActivity> donationActivity = null;
		List<DonationActivity.Status> status = new ArrayList<DonationActivity.Status>();
		status.add(DonationActivity.Status.PENDING);
		status.add(DonationActivity.Status.DONE);
		query.setParameter("userid", userid).setParameter("status", status);
		try{
			donationActivity = query.getResultList();
		}catch(NoResultException e){
			System.out.println("No Result fount in getUserSubmittedRequest in UserRequestDaoImpl");
			System.out.println(e);
			e.printStackTrace();
		}
		catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
		}
		return donationActivity;
	}
	
}
