package com.deepak.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.deepak.dao.IUserDetailsDao;
import com.deepak.dao.IUserRequestDao;
import com.deepak.modal.DonationActivity;
import com.deepak.modal.User;
import com.deepak.modal.User.BLOODGROUP;
import com.deepak.utils.HibernateUtil;
import com.deepak.utils.UserDetails;

@Dao
public class UserDetailsDaoImpl implements IUserDetailsDao {

	@Autowired
	IUserRequestDao userrequestdao;
	
	@Override
	public UserDetails getUserDetails(String userid, String pwd) {
		System.out.println("inside UserDetailsDaoImpl and getUserLogin");
		System.out.println("userId   "+userid+"  pwd  "+pwd);
		System.out.println("getting connection ");
		//SessionFactory sessionFactory =  HibernateUtil.getSessionFactory();
		User user = null;
		UserDetails userdetails = new UserDetails();
		try{
		EntityManager entityManager =  HibernateUtil.getEntityManager();
		System.out.println("got session");
		String hql = "select * from USER where emailId = '"+userid+"' and password = '"+pwd+"'";
		System.out.println("created query is  "+hql);
		//Session session = sessionFactory.openSession();
		//Query query = session.createSQLQuery(hql);
		javax.persistence.Query query = entityManager.createNativeQuery(hql, User.class);
		System.out.println("created query");
		//List<Object> listCategories = query.list();
		System.out.println("Deepak");
		user = (User) query.getSingleResult();
		System.out.println("user : "+user);
		if(user!=null){
			System.out.println(" name "+user.getUsername());
			userdetails.setUsername(user.getUsername());
			userdetails.setUserid(user.getId());
			userdetails.setUsertype(user.getUsertype());
			userdetails.setBloodgroup(user.getBloodgroup());
			BLOODGROUP bloodgroup = user.getBloodgroup();
			List<DonationActivity> listCategories = userrequestdao.getUserActiveRequest(bloodgroup);
			userdetails.setActiverequest((long) listCategories.size());	
			}
		}
		catch(Exception e){
			System.out.println("Exception : "+e);
			e.printStackTrace();
		}
		return userdetails;
	}

	@Override
	public String getForgotPassword(String emailId) {
		System.out.println("inside UserDetailsDaoImpl and getForgotPassword");
		EntityManager entityManager =  HibernateUtil.getEntityManager();
		String hql = "select * from user where userid = '"+emailId+"'";
		System.out.println("created query is  "+hql);	
		javax.persistence.Query query = entityManager.createNativeQuery(hql, User.class);
		System.out.println("created query "+query);
		List<User> listCategories  = null;
		try{
		listCategories = (List<User>) query.getSingleResult();
		}
		catch(NoResultException e){
			System.out.println("Error in get password  "+e);
		}
		System.out.println("fetching list");
		if(listCategories!=null){
			return listCategories.get(0).getPassword();
		}
		return "User Id Not Found";
	}
	
}
