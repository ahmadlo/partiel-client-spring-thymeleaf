package com.eu.ensup.partielspring.service;

import javax.ws.rs.core.Response;

public interface IStudentCourseService
{
	/**
	 * Associe un cours à un étudiant.
	 * @param studentId L'id de l'étudiant à associer.
	 * @param courseId L'id du cours à associer.
	 * @return La réponse du service REST.
	 */
	Response associateCourse(Long studentId, Long courseId);
	
	/**
	 * Dissocie un cours d'un étudiant.
	 * @param studentId L'id de l'étudiant à dissocier.
	 * @param courseId L'id du cours à dissocier.
	 * @return La réponse du service REST.
	 */
	Response disassociateCourse(Long studentId, Long courseId);
}
