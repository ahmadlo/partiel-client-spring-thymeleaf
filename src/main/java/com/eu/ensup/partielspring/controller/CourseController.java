package com.eu.ensup.partielspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eu.ensup.partielspring.domaine.Course;
import com.eu.ensup.partielspring.service.ICoursServiceClient;

@Controller
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private ICoursServiceClient courseService;

	@GetMapping("")
	public String courseHome(Model model) {
		System.out.println("Course HOme ");
		
		model.addAttribute("courseList", courseService.getAllCours());
		
		return "listCourse";
	}
	
	@GetMapping("/ajout")
	public String addCourse() {
		return "createCourse";
	}
	
	@PostMapping("/storeCourse")
	public String storeCourse(@Validated @ModelAttribute("course")Course course, Model model) {
		
		course.setId((long) 0);
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
	public String editCourse(@PathVariable(name = "id") Long id, @Validated @ModelAttribute("course")Course course, Model model) {
		
		courseService.updateCours(id, course);
		
		model.addAttribute("message", "Course modifié avec succès");
		model.addAttribute("courseList", courseService.getAllCours());
		
		return "listCourse";
	}
	
	@GetMapping("/deleteCourse/{id}")
	public String deleteCourse(@PathVariable(name = "id") Long id, Model model) {
		
		courseService.deleteCours(id);
		
		model.addAttribute("courseList", courseService.getAllCours());
		model.addAttribute("message", "Course supprimé avec succès");
		
		//return new RedirectView("/etudiant");
		return "listCourse";
	}
	
	@GetMapping("/viewCourse/{id}")
	public String viewCourse(@PathVariable(name = "id") Long id, Model model) {
		
		model.addAttribute( "course", courseService.getCoursById(id) );
		
		return "viewCourse";
	}
}
