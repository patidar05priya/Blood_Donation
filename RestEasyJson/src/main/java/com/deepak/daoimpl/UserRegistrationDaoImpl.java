package com.deepak.daoimpl;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deepak.dao.IUserRegistrationDao;
import com.deepak.modal.User;
import com.deepak.modal.User.BLOODGROUP;
import com.deepak.modal.User.USERTYPE;
import com.deepak.service.IUserDetailsService;
import com.deepak.serviceimpl.UserDetailsServiceImpl;
import com.deepak.serviceimpl.UserRegistrationServiceImpl;
import com.deepak.utils.HibernateUtil;
import com.deepak.utils.HibernateUtil;

@Dao
public class UserRegistrationDaoImpl implements IUserRegistrationDao {

	@Autowired
	IUserDetailsService detailsServiceImpl;
	@Override
	public String userRegistration(String fname, String lname, BLOODGROUP bloodGroup,
			USERTYPE userType, String address,
			String areaCode, String phoneNumber, String emailId, Float age, Float weight){
			System.out.println("inside userRegistration  DAO ");		
			SessionFactory sessionFactory =  HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			User user = new User();
			user.setUsername(fname);
			user.setBloodgroup(bloodGroup);
			user.setUsertype(userType);
			user.setAge(age);
			user.setWeight(weight);
			user.setDonationcount(0);
			user.setIsavailable(true);
			user.setAddress(address);
			user.setAreaCode(areaCode);
			user.setPhonenumber(phoneNumber);
			user.setEmailId(emailId);
			user.setCreatedon(new Date());
			Random r = new SecureRandom();
			String pwd = new BigInteger(40, r).toString(32);
			user.setPassword(pwd);
			Transaction transaction = session.beginTransaction();
			Integer userId = (Integer)session.save(user);
			transaction.commit();
			if(userId!=null)
			{
				//UserDetailsServiceImpl detailsServiceImpl = new UserDetailsServiceImpl();
				String msg = detailsServiceImpl.getForgotPassword(emailId, pwd);
				if(msg.equalsIgnoreCase("Password Send Successfully To Your Email Id")){
					return "Registration Successfull";
				}
				else{
					return "Registration Successfull But Unable to Send Password";
				}
			
			}
			else{
				return "Registration Failed";
			}
	}
	@Override
	public String isUserIdAvailable(String userId) {
		System.out.println("inside isUserIdAvailable  DAO New ");
		EntityManager entityManager =  HibernateUtil.getEntityManager();
		Query query = entityManager.createNamedQuery("isUserIdAvailable");
		System.out.println("userId  : "+userId);
		query.setParameter("email", userId);
		User userlist = null;
		try{
			userlist =  (User) query.getSingleResult();
		}catch(NoResultException e){
			System.out.println("No Result Exception : in isUserIdAvailable in UserRegistrationDaoImpl");
		}
		System.out.println("userlist  :  "+userlist);
		if(userlist!=null){
			return "Email Id already exist";
		}
		else{
			return "Email Id not exist";
		}
	}
	@Override
	public User getUserDetails(String userId) {
		System.out.println("inside getUserDetails  DAO");
		EntityManager entityManager =  HibernateUtil.getEntityManager();
		Query query = entityManager.createNamedQuery("isUserIdAvailable");
		query.setParameter("email", userId);
		User user = null;
		try{
			user =  (User) query.getSingleResult();
		}catch(NoResultException e){
			System.out.println("No Result Exception : in isUserIdAvailable in UserRegistrationDaoImpl");
		}
		System.out.println("user  :  "+user);
		if(user!=null){
			return user;
		}
		else{
			return null;
		}
		
	}
	@Override
	public String updateUserProfile(User user) {
		System.out.println("inside updateUserProfile  DAO");
		SessionFactory sessionFactory =  HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		System.out.println("in dao user : "+user);
		session.update(user);
		transaction.commit();
		session.close();
		return "Profile Update Successfull";
	}
	public String changeUserPassword(String emailId, String oldPassword,
			String newPassword) {
		
		System.out.println("inside changeUserPassword  DAO");
		Integer i = 0;
		EntityManager entityManager =  HibernateUtil.getEntityManager();
		Query query = entityManager.createNamedQuery("changeUserPassword");
		query.setParameter("email", emailId).setParameter("oldpassword", oldPassword).setParameter("newpassword", newPassword);
		try{
			i = query.executeUpdate();
			entityManager.getTransaction().commit();
		}catch(NoResultException e){
			System.out.println("No Result fount in changeUserPassword in UserRegistrationDaoImpl");
		}
		if(i>0){
			return "Password Changed Successfully";
		}
		else{
			return "Password Change Failed";
		}
	}
	
	/*public static void main(String[] args) {
		Random r = new SecureRandom();
		String pwd = new BigInteger(40, r).toString(32);
		System.out.println("Password is : "+pwd);
		}*/
}
