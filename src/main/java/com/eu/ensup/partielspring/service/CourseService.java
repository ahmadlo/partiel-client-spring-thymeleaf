package com.eu.ensup.partielspring.service;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.springframework.stereotype.Service;

import com.eu.ensup.partielspring.domaine.Course;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

@Service
public class CourseService implements ICoursService
{
	private static final String url = "http://localhost:8004/SpringMVC/servlet/course/";

	public CourseService()
	{
		super();
	}

	/**
	 * Récupere la liste de tous les cours.
	 * 
	 * @return La liste de tous les cours.
	 */
	@Override
	public List<Course> getAllCourses()
	{
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonJsonProvider.class);

		Client client = ClientBuilder.newClient(clientConfig);
		
		WebTarget webTarget = client.target(url).path("getAll");
		
		Response response = webTarget.request("application/json").get();
		
		List<Course> courses = response.readEntity(new GenericType<List<Course>>()
		{
		});
		
		System.out.println("cours : " + courses);

		return courses;
	}

	/**
	 * Récupère un cours par son id.
	 * 
	 * @param id L'id du cours à récupérer.
	 * @return Le cours correspondant à l'id.
	 */
	@Override
	public Course getCourseById(Long id)
	{
		Client client = ClientBuilder.newClient();

		WebTarget webTarget = client.target(url).path("detail/" + id);

		Response response = webTarget.request("application/json").get();

		return response.readEntity(Course.class);
	}

	/**
	 * Crée un cours.
	 * 
	 * @param cours Le cours à créer.
	 * @return La réponse du service REST.
	 */
	@Override
	public Response createCourse(Course cours)
	{
		Client client = ClientBuilder.newClient();

		WebTarget webTarget = client.target(url).path("create");
		
		Response response = webTarget.request("application/json")
				.post(Entity.entity(cours, MediaType.APPLICATION_JSON));
		
		return response;
	}

	/**
	 * Modifie un cours.
	 * 
	 * @param id L'id du cours à modifier.
	 * @param cours L'objet Course contenant les nouvelles informations.
	 * @return La réponse du service REST.
	 */
	@Override
	public Response updateCourse(Long id, Course cours)
	{
		Client client = ClientBuilder.newClient();

		WebTarget webTarget = client.target(url).path("update/" + id);
		
		Response response = webTarget.request("application/json").put(Entity.entity(cours, MediaType.APPLICATION_JSON));
		
		return response;
	}

	/**
	 * Supprime un cours.
	 * 
	 * @param id L'id du cours à supprimer.
	 * @return La réponse du service REST.
	 */
	@Override
	public Response deleteCourse(Long id)
	{
		Client client = ClientBuilder.newClient();

		WebTarget webTarget = client.target(url).path("delete/" + id);
		
		Response response = webTarget.request("application/json").delete();
		
		return response;
	}
}
