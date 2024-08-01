package onlineExam.angular.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import onlineExam.angular.dao.QuestionDAO;
import onlineExam.angular.entity.Question;


@Service
public class QuestionService {

	@Autowired
	QuestionDAO dao;

	public List<Question> getAllQuestion(String subject) {

		return dao.getAllQuestion(subject);
	}
}
