package com.eu.ensup.partielspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eu.ensup.partielspring.service.CoursServiceClient;
import com.eu.ensup.partielspring.service.ICoursServiceClient;


@Controller
@RequestMapping("/course")
public class CourseController {
	private static final long serialVersionUID = 1L;

	private ICoursServiceClient courseService;

	

	
	
	public CourseController() {
		
		this.courseService = new CoursServiceClient();
	}





	@GetMapping("")
	public String courseHome(Model model) {
		System.out.println("Course HOme ");
		
		model.addAttribute("courses", courseService.getAllCours());
		
		return "listCourse";
	}

}
