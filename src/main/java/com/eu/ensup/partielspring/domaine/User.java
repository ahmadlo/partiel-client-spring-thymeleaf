package com.eu.ensup.partielspring.domaine;

import java.util.Date;

public class User extends Personne
{
	private String login;
	private String password;
	private String profil;

	public User()
	{
		super();
	}

	public User(String firstName, String lastName, String mail, String address, String phone, Date dob)
	{
		super(firstName, lastName, mail, address, phone, dob);
	}

	public User(String firstName, String lastName, String mail, String address, String phone, Date dob, String login,
			String password, String profil)
	{
		super(firstName, lastName, mail, address, phone, dob);
		this.login = login;
		this.password = password;
		this.profil = profil;
	}

	public User(String login, String password)
	{
		this.login = login;
		this.password = password;
	}

	public User(Long id, String login, String password)
	{
		this.login = login;
		this.password = password;
		this.setId(id);
	}

	public User(String login, String password, String profil)
	{
		super();
		this.login = login;
		this.password = password;
		this.profil = profil;
	}

	public String getLogin()
	{
		return login;
	}

	public void setLogin(String login)
	{
		this.login = login;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getProfil()
	{
		return profil;
	}

	public void setProfil(String profil)
	{
		this.profil = profil;
	}

	@Override
	public String toString()
	{
		return "User [ login=" + login + ", password=" + password + ", profil=" + profil + "]";
	}
}
