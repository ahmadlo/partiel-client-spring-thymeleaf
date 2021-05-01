package com.eu.ensup.partielspring.domaine;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Student extends Personne
{

	@JsonIgnoreProperties(value = "students")
	private Set<Course> courses;

	public Student(String firstName, String lastName, String mail, String address, String phone, Date dob)
	{
		super(firstName, lastName, mail, address, phone, dob);
	}

	public Student(String firstName, String lastName, String mail, String address, String phone, Date dob,
			Set<Course> courses)
	{
		super(firstName, lastName, mail, address, phone, dob);
		this.courses = courses;
	}

	public Student()
	{
		super();
	}

	public Set<Course> getCourses()
	{
		if (courses == null)
			courses = new HashSet<Course>();
		return courses;
	}

	public void setCourses(Set<Course> courses)
	{
		this.courses = courses;
	}

	@Override
	public String toString()
	{
		return "Etudiant [cours=" + courses + ", getId()=" + getId() + ", getFirstName()=" + getFirstName()
				+ ", getLastName()=" + getLastName() + ", getMail()=" + getMail() + ", getAddress()=" + getAddress()
				+ ", getPhone()=" + getPhone() + ", getDob()=" + getDob() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
}
