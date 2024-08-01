package onlineExam.angular.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import onlineExam.angular.entity.Answer;
import onlineExam.angular.entity.Question;
import onlineExam.angular.service.QuestionService;



@RestController
@CrossOrigin("http://localhost:4200")
public class QuestionController {
	@Autowired
	QuestionService questionservice;
	@Autowired
	HttpSession httpsession;
	
	@RequestMapping("getAllQuestion/{subject}")
	public List<Question> getAllQuestion(@PathVariable String  subject) {

	List<Question> list = questionservice.getAllQuestion(subject);
	HttpSession httpsession = LoginController.httpsession;
httpsession.setAttribute("allquestion", list);
	return list; 
	}
	@GetMapping("getAllAnswer")
	public Collection<Answer>getAllAnswer()

	{
		HttpSession httpsession = LoginController.httpsession;
		@SuppressWarnings("unchecked")
		HashMap<Integer,Answer> hashMap = (HashMap<Integer, Answer>)httpsession.getAttribute("submittedDetails");
		Collection<Answer>collection=hashMap.values();
		return collection;
	}

	@RequestMapping("getFirstQuestion/{subject}")
	public Question getFirstQuestion( @PathVariable String  subject) {
   List<Question> list=questionservice.getAllQuestion(subject);
		
		HttpSession httpsession=LoginController.httpsession;
		
		httpsession.setAttribute("allquestion",list);
				
		return list.get(0);

	
	}

	
	@RequestMapping("nextQuestion")
	public Question nextQuestion()
	{
		HttpSession httpsession = LoginController.httpsession;
		
		@SuppressWarnings("unchecked")
		List<Question> list = (List<Question>) httpsession.getAttribute("allquestion");
		Question question;
		if((int)httpsession.getAttribute("questionIndex")<list.size()-1)
		{
			httpsession.setAttribute("questionIndex",(int)httpsession.getAttribute("questionIndex")+1);
			question= list.get((int)httpsession.getAttribute("questionIndex"));
			
		}else {
			question= list.get(list.size()-1);
		}
		return question;
	}
	@RequestMapping("previousQuestion")
	public  Question previousQuestion()
	{
		HttpSession httpsession=LoginController.httpsession;
		
		@SuppressWarnings("unchecked")
		List<Question> list=(List<Question>) httpsession.getAttribute("allquestion");
			
		Question question;
		
		if((int)httpsession.getAttribute("questionIndex")>0)
		{

			httpsession.setAttribute("questionIndex",(int)httpsession.getAttribute("questionIndex") - 1);//2
			
			question=list.get((int)httpsession.getAttribute("questionIndex"));
					
		}
		else
			question=list.get(0);// show first question
				
		return question;
		
	}
	@PostMapping("saveAnswer")
	public void saveAnswer(@RequestBody Answer answer)  
	{
		System.out.println(answer);
		HttpSession httpsession = LoginController.httpsession;
		
		if(answer.getSubmittedAnswer()!=null)
		{
			@SuppressWarnings("unchecked")
			HashMap<Integer,Answer> hashMap = (HashMap<Integer, Answer>)httpsession.getAttribute("submittedDetails");
			hashMap.put(answer.getQno(),answer);
			System.out.println(hashMap);
		}
	}
	@RequestMapping("endexam")
	public ResponseEntity<Integer> endexam()
	{
		HttpSession httpsession = LoginController.httpsession;
		
		
		@SuppressWarnings("unchecked")
		HashMap<Integer,Answer>hashMap = (HashMap<Integer,Answer>)httpsession.getAttribute("submittedDetails");
		
		Collection<Answer>collection=hashMap.values();
	System.out.println("values() gives object of class whose name is "+ collection.getClass().getName());
	
	for(Answer ans:collection)
	{
		if(ans.getSubmittedAnswer().equals(ans.getoriginalAnswer()))
		{
			httpsession.setAttribute("score", (int)httpsession.getAttribute("score")+1);
			
			//httpsession.setAttribute("score", 10);
		}
	}
	Integer score = (Integer)httpsession.getAttribute("score");
	System.out.println("Total Score at Server " + score);
	
	ResponseEntity<Integer> responseEntity = new ResponseEntity<Integer>(score,HttpStatus.OK);
	
	return responseEntity;
	
	}
}
