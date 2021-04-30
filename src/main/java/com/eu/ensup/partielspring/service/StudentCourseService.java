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
	 * Methode pour associer un cours à un étudiant
	 * @param studentId
	 * @param courseId
	 * @return
	 */
	@Override
	public Response associateCourse(Long studentId, Long courseId) {
		
		Client client = ClientBuilder.newClient();
		
		WebTarget webTarget = client.target(url).path("addStudentCourse/" + studentId + "/" + courseId);
		
		Response response = webTarget.request("application/json").get();
		
		return response;		
	}
}
