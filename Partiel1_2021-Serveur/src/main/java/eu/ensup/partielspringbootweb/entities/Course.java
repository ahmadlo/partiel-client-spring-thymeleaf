package eu.ensup.partielspringbootweb.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Classe métier représentant un cours.
 * 
 * @author 33651
 *
 */
@Entity
public class Course
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String themeCourse;
	private int numberHours;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value = "courses")
	@JoinTable(
            name = "student_course",
            joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")}
    )
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
