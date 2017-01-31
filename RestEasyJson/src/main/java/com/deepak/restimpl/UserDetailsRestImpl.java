package com.deepak.restimpl;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.deepak.modal.Receiver;
import com.deepak.modal.User;
import com.deepak.modal.User.BLOODGROUP;
import com.deepak.modal.User.USERTYPE;
import com.deepak.restlayer.IClientDataRest;
import com.deepak.restlayer.IUserDetailsRest;
import com.deepak.service.IUserDetailsService;
import com.deepak.service.IUserRegistrationService;
import com.deepak.serviceimpl.UserDetailsServiceImpl;
import com.deepak.serviceimpl.UserRegistrationServiceImpl;
import com.deepak.utils.HibernateUtil;
import com.deepak.utils.UserDetails;

import org.hibernate.Transaction;

@Path("/UserDetails/")
@Service("UserDetailsRestImpl")
public class UserDetailsRestImpl implements IUserDetailsRest {
	

	@Autowired
	private IUserDetailsService userDetailsService;
	
	@Autowired
	private IUserRegistrationService userRegistrationService;
		//http://localhost:8089/RESTfulExample/rest/UserDetails/getUserLogin/pal.deepak3107@gmail.com/admin
		@GET
		@Path("/getUserLogin/{userid}/{pwd}")
		@Produces("application/json")
		public UserDetails getUserLogin(@PathParam(value = "userid") String userid, @PathParam(value = "pwd") String pwd) {
			System.out.println("inside  getUserLogin");
			System.out.println("userId   "+userid+"  pwd  "+pwd);			
			//UserDetailsServiceImpl detailsService = new UserDetailsServiceImpl();
			System.out.println(" Rest 1");
			UserDetails listCategories = userDetailsService.getUserDetails(userid, pwd);
			System.out.println("listCategories  "+listCategories);

			return listCategories; 

		}
		//http://localhost:8089/RESTfulExample/rest/UserDetails/getForgotPassword/pal.deepak3107@gmail.com
		@GET
		@Path("getForgotPassword/{emailId}")
		@Override
		public String getForgotPassword(@PathParam("emailId") String emailId ){
			System.out.println("inside getForgotPassword   "+emailId);
			
			//UserDetailsServiceImpl detailsServiceImpl = new UserDetailsServiceImpl();
			return userDetailsService.getForgotPassword(emailId,null);
		}
		
		
		//http://localhost:8089/RESTfulExample/rest/UserDetails/userRegistration,<Json>
		@POST
		@Path("userRegistration")
		@Override
		public String userRegistration(User user){
			System.out.println("inside userRegistration  Rest ");
			
			//UserRegistrationServiceImpl detailsServiceImpl = new UserRegistrationServiceImpl();
			System.out.println("User Name  : "+user.getUsername());
			String msg = userRegistrationService.userRegistration(user.getUsername(), user.getUsername(), user.getBloodgroup(), user.getUsertype(), user.getAddress(), user.getAreaCode(),user.getPhonenumber(), user.getEmailId(), user.getAge(), user.getWeight());
			return msg;
		}

		@GET
		@Produces("application/json")
		@Path("checkUserIdAvailabilty/{userId}")
		@Override
		public String checkUserIdAvailabilty(@PathParam("userId") String userId){
			System.out.println("inside checkUserIdAvailabilty  Rest "+userId);
			//UserRegistrationServiceImpl detailsServiceImpl = new UserRegistrationServiceImpl();
			String msg = userRegistrationService.isUserIdAvailable(userId);
			return msg;
		}
		@GET
		@Produces("application/json")
		@Path("getUserDetails/{userId}")
		@Override
		public User getUserDetails(@PathParam("userId") String userId){
			System.out.println("inside getUserDetails  Rest "+userId);
			//UserRegistrationServiceImpl detailsServiceImpl = new UserRegistrationServiceImpl();
			User user = userRegistrationService.getUserDetails(userId);
			return user;
		}
		
		@POST
		@Consumes("application/json")
		@Path("updateUserProfile")
		@Override
		public String updateUserProfile(User user){
			System.out.println("inside updateUserProfile  Rest "+user);
			//UserRegistrationServiceImpl detailsServiceImpl = new UserRegistrationServiceImpl();
			String msg = userRegistrationService.updateUserProfile(user);
			return msg;
		}
		@GET
		@Path("changeUserPassword/{emailId}/{oldpwd}/{newpwd}")
		@Override
		public String changeUserPassword(@PathParam("emailId") String emailId, @PathParam("oldpwd") String oldPassword, @PathParam("newpwd") String newPassword){
			System.out.println("inside changeUserPassword  Rest "+emailId+"  "+oldPassword+"  "+newPassword);
			//UserRegistrationServiceImpl detailsServiceImpl = new UserRegistrationServiceImpl();
			String msg = userRegistrationService.changeUserPassword(emailId,oldPassword,newPassword);
			return msg;
		}
		
}
