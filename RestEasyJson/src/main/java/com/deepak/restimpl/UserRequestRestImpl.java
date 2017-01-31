package com.deepak.restimpl;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deepak.modal.DonationActivity;
import com.deepak.modal.User.BLOODGROUP;
import com.deepak.service.IUserDetailsService;
import com.deepak.service.IUserRequestService;

@Path("/UserRequest/")
@Service("UserRequestRestImpl")
public class UserRequestRestImpl implements Serializable{

	@Autowired
	private IUserRequestService userRequestService;
	
	
	@POST
	@Path("/getUserRequest/")
	@Consumes("application/json")
	@Produces("application/json")
	public String getUserRequest(DonationActivity activity) {
		System.out.println("inside  getUserRequest");
		System.out.println("activity   "+activity);		
		//UserDetailsServiceImpl detailsService = new UserDetailsServiceImpl();
		//List<Object> listCategories = userDetailsService.getUserDetails(userid, pwd);
		//System.out.println("listCategories  "+listCategories);
		return userRequestService.getUserRequest(activity); 

	}
	@GET
	@Path("/getUserActiveRequest/{bloodgroup}")
	@Produces("application/json")
	public List<DonationActivity> getUserActiveRequest(@PathParam("bloodgroup") BLOODGROUP bloodgroup) {
		System.out.println("inside  getUserActiveRequest bloodgroup : "+bloodgroup);
		List<DonationActivity> listCategories = userRequestService.getUserActiveRequest(bloodgroup);
		//System.out.println("listCategories  "+listCategories);
		return listCategories; 

	}
	@GET
	@Path("/getUserSubmittedRequest/{userid}")
	@Produces("application/json")
	public List<DonationActivity> getUserSubmittedRequest(@PathParam("userid") Integer userid) {
		System.out.println("inside  getUserSubmittedRequest userid : "+userid);
		List<DonationActivity> listCategories = userRequestService.getUserSubmittedRequest(userid);
		//System.out.println("listCategories  "+listCategories);
		return listCategories; 

	}
}
