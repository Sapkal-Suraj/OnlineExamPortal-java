package onlineExam.angular.dao;

import java.util.List;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import onlineExam.angular.entity.Question;


@Repository
public class QuestionDAO {
	@Autowired
	SessionFactory factory;
	
	@SuppressWarnings("unchecked")
	public List<Question>getAllQuestion(String subject)
	{
		Session session = factory.openSession();
	
		//@suppresswarning is optional
		@SuppressWarnings({ "deprecation", "rawtypes" })
		Query query =session.createQuery("from Question where subject =:subject");
		
		query.setParameter("subject", subject);
				
				return query.list();
	}
	
	

}
