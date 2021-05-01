package com.eu.ensup.partielspring.service;

import java.util.List;

import javax.ws.rs.core.Response;

import com.eu.ensup.partielspring.domaine.Course;

public interface ICoursService
{
	/**
	 * Récupere la liste de tous les cours.
	 * 
	 * @return La liste de tous les cours.
	 */
	List<Course> getAllCourses();

	/**
	 * Récupère un cours par son id.
	 * 
	 * @param id L'id du cours à récupérer.
	 * @return Le cours correspondant à l'id.
	 */
	Course getCourseById(Long id);

	/**
	 * Crée un cours.
	 * 
	 * @param cours Le cours à créer.
	 * @return La réponse du service REST.
	 */
	Response createCourse(Course cours);

	/**
	 * Modifie un cours.
	 * 
	 * @param id    L'id du cours à modifier.
	 * @param cours L'objet Course contenant les nouvelles informations.
	 * @return La réponse du service REST.
	 */
	Response updateCourse(Long id, Course cours);

	/**
	 * Supprime un cours.
	 * 
	 * @param id L'id du cours à supprimer.
	 * @return La réponse du service REST.
	 */
	Response deleteCourse(Long id);
}