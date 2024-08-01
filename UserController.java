package onlineExam.angular.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import onlineExam.angular.entity.User;
import onlineExam.angular.service.UserService;


@RestController
@CrossOrigin("http://localhost:4200")
public class UserController {
	
	@Autowired
	SessionFactory factory;

	@PostMapping("saveUser")
	public String saveUser(@RequestBody User user)

	{
		System.out.println(user);
		Session session=factory.openSession();
		
		Transaction tx=session.beginTransaction();
		
				session.persist(user);
		
		tx.commit();
		
		return "record save";
	}
	@Autowired
	UserService service;
	
	@GetMapping("getAllUsers")
	public List<User>getAllUsers(){
		
		return service.getAllUsers();
		
	}
	@RequestMapping("getUser/{username}")
	public User getUser(@PathVariable String username){
		
		return service.getUser(username);
		
	}
	@DeleteMapping("deleteUser/{username}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable String username)
	{
		service.deleteUser(username);
		
		ResponseEntity<Boolean> responseEntity=new ResponseEntity<Boolean>(true,HttpStatus.OK);
		
		return responseEntity;
		
	}
}
