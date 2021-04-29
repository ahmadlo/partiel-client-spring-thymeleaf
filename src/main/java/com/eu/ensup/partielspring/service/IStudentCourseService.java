package com.eu.ensup.partielspring.service;

import javax.ws.rs.core.Response;

public interface IStudentCourseService
{
	/**
	 * Methode pour associer un cours à un étudiant
	 * @param studentId
	 * @param courseId
	 * @return
	 */
	Response associateCourse(Long studentId, Long courseId);
}
