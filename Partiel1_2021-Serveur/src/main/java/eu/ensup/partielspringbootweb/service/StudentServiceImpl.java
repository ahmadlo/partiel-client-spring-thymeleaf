package eu.ensup.partielspringbootweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.ensup.partielspringbootweb.config.ResourceNotFoundException;
import eu.ensup.partielspringbootweb.entities.Student;
import eu.ensup.partielspringbootweb.repositories.StudentRepository;

@Service
public class StudentServiceImpl implements IStudentService
{
	@Autowired
	private StudentRepository studentRepo;

	/**
	 * @param studentRepo
	 */
	public StudentServiceImpl(StudentRepository studentRepo)
	{
		this.studentRepo = studentRepo;
	}

	/**
	 * Crée un étudiant.
	 * @param student L'étudiant à créer.
	 * @return L'étudiant créé.
	 */
	@Override
	public Student createStudent(Student student)
	{
		return studentRepo.save(student);
	}

	/**
	 * Récupère un étudiant par son id.
	 * 
	 * @param id L'id de l'étudiant à récupérer.
	 * @return L'étudiant correspondant à l'id.
	 */
	@Override
	public Student getStudent(Long id) throws ResourceNotFoundException
	{
		Student student = null;
		Optional<Student> stuFound = studentRepo.findById(id);
		if (stuFound.isPresent())
		{
			student = stuFound.get();
		}

		return student;
	}

	/**
	 * Récupère un étudiant par son mail.
	 * 
	 * @param mail Le mail de l'étudiant à récupérer.
	 * @return L'étudiant correspondant au mail.
	 */
	@Override
	public Student getStudentByMail(String mail)
	{
		return studentRepo.findByMail(mail);
	}

	/**
	 * Renvoie de la liste de tous les étudiants.
	 */
	@Override
	public List<Student> getAllStudents()
	{
		return (List<Student>) studentRepo.findAll();
	}

	/**
	 * Supprime un étudiant.
	 * 
	 * @param id L'id de l'étudiant à supprimer.
	 * 
	 */
	@Override
	public void deleteStudent(Long id)
	{
		Optional<Student> studentDel = studentRepo.findById(id);
		
		if (studentDel.isPresent())
		{
			Student student = studentDel.get();

			student.getCourses().clear();
			studentRepo.save(student);

			studentRepo.delete(student);
		}
	}

	/**
	 * Met à jour les informations d'un étudiant.
	 * 
	 * @param student L'étudiant à modifier.
	 * @return L'étudiant modifié.
	 */
	@Override
	public Student updateStudent(Student student)
	{
		System.out.println("Student to be saved :" + student);
		
		return studentRepo.save(student);
	}

	/**
	 * Recherche un étudiant par son prénom et nom.
	 * 
	 * @param firstName Le prénom de l'étudiant à rechercher.
	 * @param lastName Le nom de l'étudiant à rechercher.
	 * @return La liste des étudiants correspondant.
	 */
	@Override
	public List<Student> searchStudent(String firstName, String lastName)
	{
		return (List<Student>) studentRepo.findAllByFirstNameAndLastName(firstName, lastName);
	}
}
