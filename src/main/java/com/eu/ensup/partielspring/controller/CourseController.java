package com.eu.ensup.partielspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.eu.ensup.partielspring.domaine.Course;
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
		
		model.addAttribute("courseList", courseService.getAllCours());
		
		return "listCourse";
	}
	
	public String addCourse() {
		return "createCourse";
	}
	
	@PostMapping("/storeCourse")
	public String storeCourse(@Validated @ModelAttribute("student")Course course, Model model) {
		courseService.createCours(course);
		model.addAttribute("courseList", courseService.getAllCours());
		model.addAttribute("message", "Course ajouté avec succès");
		return "listCourse";
	}
	
	@GetMapping("/updateCourse/{id}")
	public String updateCourse(@PathVariable(name = "id") Long id, Model model) {
		model.addAttribute( "course", courseService.getCoursById(id) );
		return "updateCourse";
	}
	
	@PostMapping("/editCourse/{id}")
	public RedirectView editCourse(@PathVariable(name = "id") Long id, @Validated @ModelAttribute("course")Course course, Model model) {
		courseService.updateCours(id, course);
		model.addAttribute("message", "Course modifié avec succès");
		model.addAttribute("courseList", courseService.getAllCours());
		return new RedirectView("/etudiant");
	}
	
	@GetMapping("/deleteCourse/{id}")
	public RedirectView deleteCourse(@PathVariable(name = "id") Long id, Model model) {
		courseService.deleteCours(id);
		model.addAttribute("courseList", courseService.getAllCours());
		model.addAttribute("message", "Course supprimé avec succès");
		return new RedirectView("/etudiant");
	}
	
	@GetMapping("/viewCourse/{id}")
	public String viewCourse(@PathVariable(name = "id") Long id, Model model) {
		model.addAttribute( "course", courseService.getCoursById(id) );
		return "viewCourse";
	}


}
