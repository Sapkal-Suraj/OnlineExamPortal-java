package onlineExam.angular.dao;

import org.hibernate.Session;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import onlineExam.angular.entity.User;



@Repository
public class LoginDAO 
{
	@Autowired
	SessionFactory factory;

	public String getPasswordFromDabase(String username)
	{
		Session session=factory.openSession();
		
		// User user=new User();
		
		User user = session.get(User.class, username);
		
		// userFromDB==>[username=x password=y mobno=1234 emailidd=rd@dr] User class object
		
		
		return user.getPassword();
		
	}
	
}
