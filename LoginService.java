package onlineExam.angular.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import onlineExam.angular.dao.LoginDAO;
import onlineExam.angular.entity.User;

@Service
public class LoginService {
	@Autowired
	public LoginDAO dao;

	public boolean validate(User user) {
		String dbpassword = dao.getPasswordFromDabase(user.getUsername());

		if (dbpassword.equals(user.getPassword())) {
			return true;
		} else {
			return false;
		}

	}
}
