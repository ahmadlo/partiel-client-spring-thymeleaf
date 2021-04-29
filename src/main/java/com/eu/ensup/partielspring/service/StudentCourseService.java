package com.eu.ensup.partielspring.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eu.ensup.partielspring.domaine.Course;
import com.eu.ensup.partielspring.domaine.Student;

@Service
public class StudentCourseService implements IStudentCourseService
{
	private static final String url = "http://localhost:8004/SpringMVC/servlet/studentCourse/";
	private static final String studentUrl = "http://localhost:8004/SpringMVC/servlet/student/";
	
	@Autowired
	private IStudentServiceClient studentService;

	@Autowired
	private ICoursServiceClient courseService;
	
	/**
	 * Methode pour associer un cours à un étudiant
	 * @param studentId
	 * @param courseId
	 * @return
	 */
	@Override
	public Response associateCourse(Long studentId, Long courseId) {
		
		Client client = ClientBuilder.newClient();
		
		Student student = studentService.getStudentById(studentId);
		Course course = courseService.getCoursById(courseId);
		
		if (!student.getCourses().contains(course))
			student.getCourses().add(course);
		
		WebTarget webTarget = client.target(studentUrl).path("update/" + studentId);
		
		
		Response response = webTarget.request("application/json").put(Entity.entity(student, MediaType.APPLICATION_JSON));
		return response;		
		
	}
}
