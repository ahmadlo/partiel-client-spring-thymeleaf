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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.eu.ensup.partielspring.domaine.Student;
import com.eu.ensup.partielspring.service.IStudentService;

@Controller
@RequestMapping("/etudiant")
public class StudentController
{
	@Autowired
	private IStudentService studentService;

	@GetMapping("")
	public String etudiantHome(Model model)
	{
		model.addAttribute("studentList", studentService.getListStudent());

		return "listEtudiant";
	}

	@GetMapping("/ajout")
	public String addEtudiant()
	{
		return "createEtudiant";
	}

	@PostMapping("/storeStudent")
	public String storeStudent(@Validated @ModelAttribute("student") Student student, Model model)
	{
		System.out.println(student);

		studentService.createStudent(student);

		model.addAttribute("studentList", studentService.getListStudent());
		model.addAttribute("message", "Etudiant ajouté avec succès");

		return "listEtudiant";
	}

	@GetMapping("/updateStudent/{id}")
	public String updateStudent(@PathVariable(name = "id") Long id, Model model)
	{
		model.addAttribute("student", studentService.getStudentById(id));

		return "updateEtudiant";
	}

	@PostMapping("/editStudent/{id}")
	public RedirectView editStudent(@PathVariable(name = "id") Long id,
			@Validated @ModelAttribute("student") Student student, RedirectAttributes redirectAttributes)
	{
		System.out.println("Etudiant à modifier : " + student);

		studentService.updateStudent(id, student);

		redirectAttributes.addFlashAttribute("message", "Etudiant modifié avec succès");

		return new RedirectView("/etudiant");
	}

	@GetMapping("/deleteStudent/{id}")
	public RedirectView deleteStudent(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes)
	{
		studentService.deleteStudent(id);

		redirectAttributes.addFlashAttribute("message", "Etudiant supprimé avec succès");

		return new RedirectView("/etudiant");
	}

	@GetMapping("/viewStudent/{id}")
	public String viewStudent(@PathVariable(name = "id") Long id, Model model)
	{
		model.addAttribute("student", studentService.getStudentById(id));

		return "viewEtudiant";
	}

	@PostMapping("/search")
	public String searchStudent(@ModelAttribute(name = "searchText") String searchText, Model model)
	{
		if (searchText == null || searchText == "")
			return "listEtudiant";

		List<Student> students = studentService.getListStudent().stream()
				.filter(s -> s.getFirstName().toLowerCase().contains(searchText.toLowerCase())
						|| s.getLastName().toLowerCase().contains(searchText.toLowerCase())
						|| s.getMail().toLowerCase().contains(searchText.toLowerCase()))
				.collect(Collectors.toList());

		if (students == null)
			students = new ArrayList<Student>();

		model.addAttribute("studentList", students);
		model.addAttribute("searchString", "Résultat de la recherche \"" + searchText + "\" :");

		return "listEtudiant";
	}
}
