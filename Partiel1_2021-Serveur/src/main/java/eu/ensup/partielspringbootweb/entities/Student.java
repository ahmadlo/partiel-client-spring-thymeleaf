package eu.ensup.partielspringbootweb.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

/**
 * Classe métier représentant un étudiant .
 * 
 *
 */
@Entity
public class Student extends Personne
{
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Course> courses = new HashSet<Course>();

	public Student()
	{
		super();
		
	}
	
	public Student(Long id, String firstName, String lastName, String mail, String address, String phone, Date dob) {
		super(id, firstName, lastName, mail, address, phone, dob);
	}



	public Set<Course> getCourses() {
		return courses;
	}



	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "Etudiant [cours=" + courses + ", getId()=" + getId() + ", getFirstName()=" + getFirstName()
				+ ", getLastName()=" + getLastName() + ", getMail()=" + getMail() + ", getAddress()=" + getAddress()
				+ ", getPhone()=" + getPhone() + ", getDob()=" + getDob() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
}
