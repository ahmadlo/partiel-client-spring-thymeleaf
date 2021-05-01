package com.eu.ensup.partielspring.service;

import java.util.List;

import javax.ws.rs.core.Response;

import com.eu.ensup.partielspring.domaine.Student;

public interface IStudentService
{
	/**
	 * Récupère la liste de tous les étudiants.
	 * 
	 * @return La liste de tous les étudiants.
	 */
	List<Student> getListStudent();

	/**
	 * Crée un étudiant.
	 * 
	 * @param student L'étudiant à créer.
	 * @return La réponse du service REST.
	 */
	Response createStudent(Student student);

	/**
	 * Récupère un étudiant par son id.
	 * 
	 * @param id L'id de l'étudiant à récupérer.
	 * @return L'étudiant correspondant à l'id.
	 */
	Student getStudentById(Long id);

	/**
	 * Recherche un étudiant par son prénom et son nom.
	 * 
	 * @param firstName Le prénom de l'étudiant.
	 * @param lastName Le nom de l'étudiant.
	 * @return La liste des étudiants correspondant.
	 */
	List<Student> getStudentByFirstAndLastName(String firstName, String lastName);

	/**
	 * Supprime un étudiant.
	 * 
	 * @param id L'id de l'étudiant à supprimer.
	 * @return La réponse du service REST.
	 */
	Response deleteStudent(Long id);

	/**
	 * Met à jour les informations d'un étudiant.
	 * 
	 * @param id L'id de l'étudiant à mettre à jour.
	 * @param student L'objet Student avec les nouvelles informations.
	 * @return La réponse du service REST.
	 */
	Response updateStudent(Long id, Student student);
}