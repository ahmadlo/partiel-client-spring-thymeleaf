package com.eu.ensup.partielspring.domaine;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Course {
	
	private Long id;
	private Long idCourse;
	private String themeCourse;
	private int numberHours;
	private Set<Student> students =  new HashSet<Student>();
		
	public Course() {
		super();
	}

	public Course(String themeCourse, int numberHours, Long id) {
		super();
		this.themeCourse = themeCourse;
		this.numberHours = numberHours;
		this.id=id;
	}


	public Long getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(Long id) {
		idCourse = id;
	}

	public String getThemeCourse() {
		return themeCourse;
	}

	public void setThemeCourse(String themeCourse) {
		this.themeCourse = themeCourse;
	}

	public int getNumberHours() {
		return numberHours;
	}

	public void setNumberHours(int numberHours) {
		this.numberHours = numberHours;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", idCourse=" + idCourse + ", themeCourse=" + themeCourse + ", numberHours="
				+ numberHours + ", students=" + students + "]";
	}
	
	
	
		
}
