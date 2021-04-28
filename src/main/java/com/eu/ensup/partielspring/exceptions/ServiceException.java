package com.eu.ensup.partielspring.exceptions;

public class ServiceException extends Exception
{
	public ServiceException()
	{
		super("Exception survenue dans la couche Service.");
	}
	
	public ServiceException(Throwable throwable)
	{
		super("Exception survenue dans la couche Service.", throwable);
	}
	
	public ServiceException(String message)
	{
		super(message);
	}
	
	public ServiceException(String message, Throwable throwable)
	{
		super(message, throwable);
	}
}
