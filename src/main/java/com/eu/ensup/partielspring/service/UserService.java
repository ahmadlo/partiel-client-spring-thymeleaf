package com.eu.ensup.partielspring.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.eu.ensup.partielspring.domaine.User;
import com.eu.ensup.partielspring.exceptions.UserNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserService implements IUserService
{
	private static final String url = "http://localhost:8004/SpringMVC/servlet/user/";

	public UserService()
	{
		super();
	}

	/**
	 * Tente de connecter un utilisateur.
	 * 
	 * @param user L'utilisateur à récupérer.
	 * @return L'utilisateur connecté.
	 * @throws UserNotFoundException
	 */
	@Override
	public User login(User user) throws UserNotFoundException
	{
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(url).path("login");

		Response response = webTarget.request("application/json").post(Entity.entity(user, MediaType.APPLICATION_JSON));

		String output = response.readEntity(String.class);

		if (response.getStatus() == 401)
		{
			throw new UserNotFoundException();
		}

		System.out.println(response);
		ObjectMapper mapper = new ObjectMapper();
		User userResponse = null;

		try
		{
			JsonNode jsonNode = mapper.readTree(output);

			userResponse = mapper.readValue(jsonNode.get("user").toString(), User.class);

			System.out.println("the user found : " + userResponse.toString());
			return userResponse;
		}
		catch (JsonProcessingException e)
		{
			e.printStackTrace();
		}
		catch (NullPointerException e)
		{
			System.out.println("Erreur login ou mdp");
			e.printStackTrace();
		}

		return userResponse;
	}

	@Override
	public User getUser()
	{
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(url).path("get");

		Response response = webTarget.request("application/json").get();

		return response.readEntity(User.class);
	}
}
