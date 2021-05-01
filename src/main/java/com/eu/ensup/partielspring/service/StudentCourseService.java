package com.eu.ensup.partielspring.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;


@Service
public class StudentCourseService implements IStudentCourseService
{
	private static final String url = "http://localhost:8004/SpringMVC/servlet/studentCourse/";
	
	/**
	 * Associe un cours à un étudiant.
	 * @param studentId L'id de l'étudiant à associer.
	 * @param courseId L'id du cours à associer.
	 * @return La réponse du service REST.
	 */
	@Override
	public Response associateCourse(Long studentId, Long courseId) {
		
		Client client = ClientBuilder.newClient();
		
		WebTarget webTarget = client.target(url).path("addStudentCourse/" + studentId + "/" + courseId);
		
		Response response = webTarget.request("application/json").get();
		
		return response;		
	}
	
	/**
	 * Dissocie un cours d'un étudiant.
	 * @param studentId L'id de l'étudiant à dissocier.
	 * @param courseId L'id du cours à dissocier.
	 * @return La réponse du service REST.
	 */
	@Override
	public Response disassociateCourse(Long studentId, Long courseId) {
		
		Client client = ClientBuilder.newClient();
		
		WebTarget webTarget = client.target(url).path("removeStudentCourse/" + studentId + "/" + courseId);
		
		Response response = webTarget.request("application/json").get();
		
		return response;		
	}
}
