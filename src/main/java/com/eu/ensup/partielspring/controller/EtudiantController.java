package com.eu.ensup.partielspring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.eu.ensup.partielspring.domaine.Student;
import com.eu.ensup.partielspring.service.IStudentServiceClient;

@Controller
@RequestMapping("/etudiant")
public class EtudiantController {

	@Autowired
	private IStudentServiceClient studentService;

	@GetMapping("")
	public String etudiantHome(Model model) {
		
		System.out.println("Etudiant HOme ");
		
		model.addAttribute("studentList", studentService.getListStudent());

		return "listEtudiant";
	}
	
	@GetMapping("/ajout")
	public String addEtudiant() {
		return "createEtudiant";
	}
	
	@PostMapping("/storeStudent")
	public String storeStudent(@Validated @ModelAttribute("student")Student student, Model model) {
		
		System.out.println(student);
		
		studentService.createStudent(student);
		
		model.addAttribute("studentList", studentService.getListStudent());
		model.addAttribute("message", "Etudiant ajouté avec succès");
		
		return "listEtudiant";
	}
	
	@GetMapping("/updateStudent/{id}")
	public String updateStudent(@PathVariable(name = "id") Long id, Model model) {
		
		model.addAttribute( "student", studentService.getStudentById(id) );
		
		return "updateEtudiant";
	}
	
	@PostMapping("/editStudent/{id}")
	public RedirectView editStudent(@PathVariable(name = "id") Long id, @Validated @ModelAttribute("student")Student student, Model model) {
		
		System.out.println("Etudiant à modifier : " + student);
		
		studentService.updateStudent(id, student);
		
		model.addAttribute("message", "Etudiant modifié avec succès");
		model.addAttribute("studentList", studentService.getListStudent());
		
		return new RedirectView("/etudiant");
	}
	
	@GetMapping("/deleteStudent/{id}")
	public RedirectView deleteStudent(@PathVariable(name = "id") Long id, Model model) {
		
		studentService.deleteStudent(id);
		
		model.addAttribute("studentList", studentService.getListStudent());
		model.addAttribute("message", "Etudiant supprimé avec succès");
		
		return new RedirectView("/etudiant");
	}
	
	@GetMapping("/viewStudent/{id}")
	public String viewStudent(@PathVariable(name = "id") Long id, Model model) {
		
		model.addAttribute( "student", studentService.getStudentById(id) );
		
		return "viewEtudiant";
	}

	@PostMapping("/search")
	public String authentification(@ModelAttribute(name="searchText") String searchText, Model model) {
		
		if (searchText == null || searchText == "")
			return "listEtudiant";
		
		List<Student> students = studentService.getListStudent()
				.stream()
				.filter(s -> s.getFirstName().toLowerCase().contains(searchText.toLowerCase())
						|| s.getLastName().toLowerCase().contains(searchText.toLowerCase())
						|| s.getMail().toLowerCase().contains(searchText.toLowerCase()))
				.collect(Collectors.toList());
		
		if (students == null)
			students = new ArrayList<Student>();
		
		model.addAttribute("studentList", students);

		return "listEtudiant";
	}
}
