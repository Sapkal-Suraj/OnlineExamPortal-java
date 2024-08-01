package onlineExam.angular.dao;

import java.util.List;



import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import onlineExam.angular.entity.User;

@Repository
public class UserDAO 
{
	@Autowired
	SessionFactory factory;
	
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers()
	{
		
		Session session=factory.openSession();
		
		@SuppressWarnings({ "deprecation", "rawtypes" })
		Query query=session.createQuery("from User");
		
		return query.list();
		
	}
	
	
	public User getUser(String username)
	{

		Session session=factory.openSession();
		
		@SuppressWarnings({ "deprecation", "unchecked" })
		Query<User> query=session.createQuery("from User where username=:username");
		
		query.setParameter("username",username);
		
		return query.list().get(0);
		
	}

	
	@SuppressWarnings("deprecation")
	public  void deleteUser(String username)
	{

		Session session=factory.openSession();
		
		@SuppressWarnings("unchecked")
		Query<User> query=session.createQuery("delete from User where username=:username");
		
		query.setParameter("username",username);
		
		Transaction tx=session.beginTransaction();
		
			query.executeUpdate();
		
		tx.commit();
		
		
	}
	
	
	
}
