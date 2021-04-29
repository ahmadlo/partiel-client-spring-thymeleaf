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

import com.eu.ensup.partielspring.domaine.Student;
import com.eu.ensup.partielspring.service.IStudentServiceClient;
import com.eu.ensup.partielspring.service.IUserServiceClient;
import com.eu.ensup.partielspring.service.StudentServiceClient;

@Controller
@RequestMapping("/etudiant")
public class EtudiantController {
	private static final long serialVersionUID = 1L;
	private IUserServiceClient userService;
	private IStudentServiceClient studentService;

	
	
	public EtudiantController() {
		
		studentService = new StudentServiceClient();
		
	}

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

}
