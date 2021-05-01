package eu.ensup.partielspringbootweb.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eu.ensup.partielspringbootweb.config.ResourceNotFoundException;
import eu.ensup.partielspringbootweb.entities.Course;
import eu.ensup.partielspringbootweb.entities.Student;
import eu.ensup.partielspringbootweb.service.ICourseService;
import eu.ensup.partielspringbootweb.service.IStudentService;

@RestController
@CrossOrigin
@RequestMapping("/studentCourse")
public class StudentCourseController
{
	@Autowired
	private IStudentService studentService;

	@Autowired
	private ICourseService courseService;

	/**
	 * @param studentService
	 */
	public StudentCourseController(IStudentService studentService, ICourseService courseService)
	{
		System.out.println("+++++++++++++++++++ Init studentCourse controller+++++++++++++++++");
		this.studentService = studentService;
		this.courseService = courseService;
	}

	@GetMapping("/addStudentCourse/{studentId}/{courseId}")
	public Student updateStudent(@PathVariable(value = "studentId") Long studentId,
			@PathVariable(value = "courseId") Long courseId) throws ResourceNotFoundException
	{
		Student student = studentService.getStudent(studentId);
		Course course = courseService.getCourse(courseId);

		Set<Course> studentCourses = student.getCourses();
		studentCourses.add(course);
		student.setCourses(studentCourses);

		System.out.println(">> student " + student);
		System.out.println(">> course " + course);

		return studentService.updateStudent(student);
	}

	@GetMapping("/removeStudentCourse/{studentId}/{courseId}")
	public Student removeStudentCourse(@PathVariable(value = "studentId") Long studentId,
			@PathVariable(value = "courseId") Long courseId) throws ResourceNotFoundException
	{
		Student student = studentService.getStudent(studentId);
		Course course = courseService.getCourse(courseId);

		Set<Course> studentCourses = student.getCourses();
		System.out.println(">> AV studentCourses " + studentCourses);

		Course courseToRemove = null;

		for (Course studentCourse : studentCourses)
		{
			if (studentCourse.getId() == course.getId())
				courseToRemove = studentCourse;
		}

		if (courseToRemove == null)
			return studentService.updateStudent(student);

		studentCourses.remove(courseToRemove);
		student.setCourses(studentCourses);

		System.out.println(">> AP studentCourses " + studentCourses);

		return studentService.updateStudent(student);
	}
}
