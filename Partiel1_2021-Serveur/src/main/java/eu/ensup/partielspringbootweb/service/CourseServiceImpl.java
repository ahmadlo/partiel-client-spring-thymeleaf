package eu.ensup.partielspringbootweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import eu.ensup.partielspringbootweb.config.ResourceNotFoundException;
import eu.ensup.partielspringbootweb.entities.Course;
import eu.ensup.partielspringbootweb.repositories.CourseRepository;

@Service
public class CourseServiceImpl implements ICourseService
{
	@Autowired
	private CourseRepository courseRepo;

	/**
	 * @param courseRepo
	 */
	public CourseServiceImpl(CourseRepository courseRepo)
	{
		this.courseRepo = courseRepo;
	}

	/**
	 * Récupère la liste de tous les cours.
	 * @return La liste de tous les cours.
	 */
	@Override
	public List<Course> getAllCourses()
	{
		return (List<Course>) courseRepo.findAll();
	}

	/**
	 * Crée un cours.
	 * @param course Le cours à créer.
	 * @return Le cours créé.
	 */
	@Override
	public Course createCourse(Course course)
	{
		return courseRepo.save(course);
	}

	/**
	 * Récupère un cours par son id.
	 * 
	 * @param id L'id du cours à récupérer.
	 * @return Le cours correspondant à l'id.
	 */

	@Override
	public Course getCourse(Long id) throws ResourceNotFoundException
	{
		Course cours = null;
		Optional<Course> stuFound = courseRepo.findById(id);
		
		if (stuFound.isPresent())
		{
			cours = stuFound.get();
		}

		return cours;
	}

	/**
	 * Met à jour les informations d'un cours.
	 * 
	 * @param id L'id du cours à modifier.
	 * @param course Le cours contenant les informations à mettre à jour.
	 * @return Le cours mis à jour.
	 */
	@Override
	public Course updateCourse(Long id, Course course)
	{
		Course cours = null;
		Optional<Course> courseFound = courseRepo.findById(id);
		
		if (courseFound.isPresent())
		{
			System.out.println("AV cours : " + cours);
			System.out.println("AV course : " + course);
			
			cours = courseFound.get();
			cours.setNumberHours(course.getNumberHours());
			cours.setThemeCourse(course.getThemeCourse());
			cours.setStudents(course.getStudents());
		}
		
		System.out.println("AP cours : " + cours);
		System.out.println("AP course : " + course);

		return courseRepo.save(course);
	}

	/**
	 * Supprimme un cours
	 * 
	 * @param id L'id du cours à supprimer.
	 * 
	 */
	@Override
	public void deleteCourse(Long id)
	{
		Optional<Course> courseDel = courseRepo.findById(id);
		if (courseDel.isPresent())
		{
			Course course = courseDel.get();

			course.getStudents().clear();
			courseRepo.save(course);

			courseRepo.delete(course);
		}
	}
}
