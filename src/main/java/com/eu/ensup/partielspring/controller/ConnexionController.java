package com.eu.ensup.partielspring.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.eu.ensup.partielspring.domaine.User;
import com.eu.ensup.partielspring.exceptions.UserNotFoundException;
import com.eu.ensup.partielspring.service.CoursServiceClient;
import com.eu.ensup.partielspring.service.ICoursServiceClient;
import com.eu.ensup.partielspring.service.IStudentServiceClient;
import com.eu.ensup.partielspring.service.IUserServiceClient;
import com.eu.ensup.partielspring.service.StudentServiceClient;
import com.eu.ensup.partielspring.service.UserServiceClient;


@Controller
public class ConnexionController {

	private IUserServiceClient userService;
	private IStudentServiceClient studentService;
	private ICoursServiceClient courseService;

	/**
	 * Default constructor.
	 */
	public ConnexionController() {
		userService = new UserServiceClient();
		courseService = new CoursServiceClient();
		studentService = new StudentServiceClient();
	}
	
	@GetMapping("/home")
	public String home(HttpSession session) {
		if (session.getAttribute("user") == null)
			return "login";
		else
			return "home";
	}
	
	@GetMapping( path ={"/","/login"})
	public String login() {
		return "login";
	}

	
	
	@PostMapping("/login")
	public String authentification(@ModelAttribute(name="login") String login, @ModelAttribute(name="password") String password,Model model,HttpSession session) throws ServletException, IOException, NullPointerException {
		
		System.out.println("################# Login Authentification");
		String reponse = "/login";
		
		try
		{
			User user = new User();
			user.setLogin(login);
			user.setPassword(password);
			
			System.out.println(user.getLogin() +" - " + user.getPassword());
			
			User userRetour = userService.login(user);
			
			System.out.println("auth result : " + userRetour.toString());
			
			if (userRetour != null && userRetour.getLogin().equalsIgnoreCase(login)
					&& userRetour.getPassword().equalsIgnoreCase(password))
			{
					
				reponse ="/home";
				String profil;
				if(userRetour.getProfil().equalsIgnoreCase("D")) {
					profil = "Directeur";
				}
				else {
					profil = "Responsable";
				}
				session.setAttribute("user", userRetour);
				
				model.addAttribute("profil", profil);
				model.addAttribute("students", studentService.getListStudent());
				model.addAttribute("courses", courseService.getAllCours());
					
			}
			
		}
		catch (UserNotFoundException e)
		{	
			model.addAttribute("error", e.getMessage());
			System.out.println("error : " + e.getMessage());
		}
		System.out.println(" Page to display : " +reponse);
		
		return reponse;
	}
}
