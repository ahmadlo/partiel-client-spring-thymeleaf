package com.eu.ensup.partielspring.service;

import com.eu.ensup.partielspring.domaine.User;
import com.eu.ensup.partielspring.exceptions.UserNotFoundException;

public interface IUserServiceClient {

	/**
	 * Methode de connexion d'un utilisateur
	 * @param user
	 * @return
	 */
	User login(User user) throws UserNotFoundException;

	/**
	 * Methode pour recuperer un user
	 * @return
	 */
	User getUser();

}