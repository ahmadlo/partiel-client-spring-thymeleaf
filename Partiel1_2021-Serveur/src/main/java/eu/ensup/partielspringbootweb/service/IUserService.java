package eu.ensup.partielspringbootweb.service;

import eu.ensup.partielspringbootweb.entities.User;

public interface IUserService
{
	/**
	 * Retourne un utilisateur correspondant au login et au mot de passe.
	 * 
	 * @param user L'utilisateur à chercher.
	 * @return L'utilisateur correspondant au login et mot de passe.
	 */
	User getUser(User user);

	/**
	 * Retourne un utilisateur en fonction de son id.
	 * 
	 * @param id L'id de l'utilisateur à chercher.
	 * @return L'utilisateur correspondant à l'id.
	 */
	User getUser(Long id);

	User create(String login, String password);
	
	User create(User user);
}