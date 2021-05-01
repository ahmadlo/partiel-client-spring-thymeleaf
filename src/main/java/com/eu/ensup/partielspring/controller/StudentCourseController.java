package com.eu.ensup.partielspring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.eu.ensup.partielspring.domaine.Course;
import com.eu.ensup.partielspring.domaine.Student;
import com.eu.ensup.partielspring.service.ICoursService;
import com.eu.ensup.partielspring.service.IStudentCourseService;
import com.eu.ensup.partielspring.service.IStudentService;

@Controller
@RequestMapping("/coursEtudiant")
public class StudentCourseController
{
	@Autowired
	private IStudentService studentService;

	@Autowired
	private IStudentCourseService studentCourseService;

	@Autowired
	private ICoursService courseService;

	@GetMapping("/associateCourse/{studentId}")
	public String associateCourse(@PathVariable(name = "studentId") Long studentId, Model model)
	{
		Student student = studentService.getStudentById(studentId);
		
		List<Course> courses = new ArrayList<Course>();
		
		Set<Course> studentCourses = student.getCourses();
		
		courses =
				courseService.getAllCourses()
				.stream()
				.filter(c -> !studentCourses.stream().anyMatch(c2 -> c2.getId() == c.getId()))
				.filter(Objects::nonNull)
				.collect(Collectors.toList());
		
		if (courses.isEmpty())
		{
			model.addAttribute("error", "Aucun cours à associer n'a été trouvé.");
		}

		model.addAttribute("student", student);
		model.addAttribute("studentCourses", studentCourses);
		model.addAttribute("courses", courses);

		return "associateCourse";
	}

	@PostMapping("/associateCourse")
	public String associateCourse(@ModelAttribute("studentId") Long studentId, @ModelAttribute("courseId") Long courseId,
			Model model)
	{
		System.out.println("Association étudiant à l'id \"" + studentId + "\" au cours à l'id \"" + courseId + "\"");

		studentCourseService.associateCourse(studentId, courseId);

		model.addAttribute("message", "Cours ajouté avec succès");
		model.addAttribute("studentList", studentService.getListStudent());

		return "listEtudiant";
	}

	@GetMapping("/disassociateCourse/{studentId}/{courseId}")
	public RedirectView disassociateCourse(@PathVariable(name = "studentId") Long studentId, @PathVariable(name = "courseId") Long courseId, Model model)
	{
		System.out.println("Désassociation étudiant à l'id \"" + studentId + "\" au cours à l'id \"" + courseId + "\"");

		studentCourseService.disassociateCourse(studentId, courseId);

		model.addAttribute("message", "Cours supprimé avec succès");
		
		RedirectView redirectView = new RedirectView("/coursEtudiant/associateCourse/" + studentId);

		return redirectView;
	}
}
