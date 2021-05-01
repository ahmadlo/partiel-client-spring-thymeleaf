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

import com.eu.ensup.partielspring.domaine.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

@Service
public class StudentService implements IStudentService
{
	private static final String url = "http://localhost:8004/SpringMVC/servlet/student/";

	public StudentService()
	{
		super();
	}

	/**
	 * Récupère la liste de tous les étudiants.
	 * 
	 * @return La liste de tous les étudiants.
	 */
	@Override
	public List<Student> getListStudent()
	{
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonJsonProvider.class);

		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target(url).path("getAll");

		Response response = webTarget.request("application/json").get();

		List<Student> listEtudiant = response.readEntity(new GenericType<List<Student>>()
		{
		});
		System.out.println("etudiants : " + listEtudiant);

		return listEtudiant;
	}

	/**
	 * Crée un étudiant.
	 * 
	 * @param student L'étudiant à créer.
	 * @return La réponse du service REST.
	 */
	@Override
	public Response createStudent(Student student)
	{
		Response response = null;

		Client client = ClientBuilder.newClient();

		WebTarget webTarget = client.target(url).path("create");

		ObjectMapper objectMapper = new ObjectMapper();
		String input;

		try
		{
			input = objectMapper.writeValueAsString(student);
			response = webTarget.request("application/json").post(Entity.json(input));

		}
		catch (JsonProcessingException e)
		{
			e.printStackTrace();
		}

		return response;
	}

	/**
	 * Récupère un étudiant par son id.
	 * 
	 * @param id L'id de l'étudiant à récupérer.
	 * @return L'étudiant correspondant à l'id.
	 */
	@Override
	public Student getStudentById(Long id)
	{
		Client client = ClientBuilder.newClient();
		
		WebTarget webTarget = client.target(url).path("detail/" + id);

		Response response = webTarget.request("application/json").get();

		return response.readEntity(Student.class);
	}

	/**
	 * Recherche un étudiant par son prénom et son nom.
	 * 
	 * @param firstName Le prénom de l'étudiant.
	 * @param lastName Le nom de l'étudiant.
	 * @return La liste des étudiants correspondant.
	 */
	@Override
	public List<Student> getStudentByFirstAndLastName(String first_name, String last_name)
	{
		Client client = ClientBuilder.newClient();
		
		WebTarget webTarget = client.target(url).path("research/" + first_name + "/" + last_name);

		Response response = webTarget.request("application/json").get();
		
		List<Student> listEtudiant = response.readEntity(new GenericType<List<Student>>()
		{
		});

		return listEtudiant;
	}

	/**
	 * Supprime un étudiant.
	 * 
	 * @param id L'id de l'étudiant à supprimer.
	 * @return La réponse du service REST.
	 */
	@Override
	public Response deleteStudent(Long id)
	{
		Client client = ClientBuilder.newClient();

		WebTarget webTarget = client.target(url).path("delete/" + id);

		Response response = webTarget.request("application/json").delete();

		return response;
	}

	/**
	 * Met à jour les informations d'un étudiant.
	 * 
	 * @param id L'id de l'étudiant à mettre à jour.
	 * @param student L'objet Student avec les nouvelles informations.
	 * @return La réponse du service REST.
	 */
	@Override
	public Response updateStudent(Long id, Student etudiant)
	{
		Client client = ClientBuilder.newClient();

		WebTarget webTarget = client.target(url).path("update/" + id);

		Response response = webTarget.request("application/json")
				.put(Entity.entity(etudiant, MediaType.APPLICATION_JSON));
		
		return response;
	}
}
