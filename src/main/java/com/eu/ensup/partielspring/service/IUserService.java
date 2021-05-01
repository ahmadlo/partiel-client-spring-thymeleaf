package com.eu.ensup.partielspring.service;

import com.eu.ensup.partielspring.domaine.User;
import com.eu.ensup.partielspring.exceptions.UserNotFoundException;

public interface IUserService
{
	/**
	 * Tente de connecter un utilisateur.
	 * 
	 * @param user L'utilisateur à récupérer.
	 * @return L'utilisateur connecté.
	 * @throws UserNotFoundException
	 */
	User login(User user) throws UserNotFoundException;

	User getUser();
}