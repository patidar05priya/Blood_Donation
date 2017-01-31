package com.deepak.serviceimpl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.deepak.dao.IUserDetailsDao;
import com.deepak.modal.User;
import com.deepak.service.IUserDetailsService;
import com.deepak.utils.UserDetails;

@Configurable
@Component
@Service("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements IUserDetailsService {
	@Autowired
	private IUserDetailsDao userDetailsDao;
	@Override
	public UserDetails getUserDetails(String userName, String pwd) {
		System.out.println(" Service 1");
		//UserDetailsDaoImpl userDetailsDao = new UserDetailsDaoImpl();
		System.out.println(" Service 2");
		System.out.println("userDetailsDao : "+userDetailsDao);
		UserDetails userList = userDetailsDao.getUserDetails(userName, pwd);
		if(userList!=null){
			System.out.println("Login Successfull");
		}
		else{
			System.out.println("Login Failed");
		}
		return userList;
	}
	@Override
	public String getForgotPassword(String emailId, String msg){
		System.out.println("inside  getForgotPassword at service  "+emailId);
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("md.afsar475@gmail.com","afsarafsi");
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("md.afsar475@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(emailId));
			message.setSubject("Testing Subject Resend");
			if(msg!=null){
			message.setText("Dear User," +
					"\n\n Thank You For Register. \n Your Password is : "+msg);
			}
			else{
				//UserDetailsDaoImpl daoImpl = new UserDetailsDaoImpl();
				String password = userDetailsDao.getForgotPassword(emailId);
				if(password!=null && !password.equalsIgnoreCase("User Id Not Found")){
				message.setText("Dear User," +
						"\n\n Your Password is : "+password);
				}
				else{
					return "User Id Not Found";
				}
			}
			Transport.send(message);

			System.out.println("Email Sending Done");
			return "Password Send Successfully To Your Email Id";

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}

}
