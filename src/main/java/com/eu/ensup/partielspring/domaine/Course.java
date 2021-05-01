package com.eu.ensup.partielspring.domaine;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Course
{
	private Long id;
	private String themeCourse;
	private int numberHours;

	@JsonIgnoreProperties(value = "courses")
	private Set<Student> students = new HashSet<Student>();

	public Course()
	{
		super();
	}

	public Course(String themeCourse, int numberHours, Long id)
	{
		super();
		this.themeCourse = themeCourse;
		this.numberHours = numberHours;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getThemeCourse()
	{
		return themeCourse;
	}

	public void setThemeCourse(String themeCourse)
	{
		this.themeCourse = themeCourse;
	}

	public int getNumberHours()
	{
		return numberHours;
	}

	public void setNumberHours(int numberHours)
	{
		this.numberHours = numberHours;
	}

	public Set<Student> getStudents()
	{
		return students;
	}

	public void setStudents(Set<Student> students)
	{
		this.students = students;
	}

	@Override
	public String toString()
	{
		return "Course [id=" + id + ", themeCourse=" + themeCourse + ", numberHours="
				+ numberHours + "]";
	}
}
