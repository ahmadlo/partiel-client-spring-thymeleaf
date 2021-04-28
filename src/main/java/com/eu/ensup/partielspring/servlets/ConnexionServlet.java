package com.eu.ensup.partielspring.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eu.ensup.partielspring.domaine.User;
import com.eu.ensup.partielspring.exceptions.InternalServerException;
import com.eu.ensup.partielspring.exceptions.UserNotFoundException;
import com.eu.ensup.partielspring.service.CoursServiceClient;
import com.eu.ensup.partielspring.service.ICoursServiceClient;
import com.eu.ensup.partielspring.service.IStudentServiceClient;
import com.eu.ensup.partielspring.service.IUserServiceClient;
import com.eu.ensup.partielspring.service.StudentServiceClient;
import com.eu.ensup.partielspring.service.UserServiceClient;


/**
 * Servlet implementation class ConnexionServlet
 */
@WebServlet("/Connexion")
public class ConnexionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IUserServiceClient userService;
	private IStudentServiceClient studentService;
	private ICoursServiceClient courseService;
	private RequestDispatcher dispatcher = null;
//	private IEtudiantDao etudiantDao = new EtudiantDao();

	/**
	 * Default constructor.
	 */
	public ConnexionServlet() {
		userService = new UserServiceClient();
		courseService = new CoursServiceClient();
		studentService = new StudentServiceClient();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("error");
		dispatcher = request.getRequestDispatcher("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("error");
		
		methode(request, response);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void methode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NullPointerException {
		
		HttpSession session = request.getSession();
		
		try
		{
			User user = new User();
			user.setLogin(request.getParameter("login"));
			user.setPassword(request.getParameter("password"));
			
			System.out.println(user.getLogin() + user.getPassword());
			
			User userRetour = userService.login(user);
			
			System.out.println(userRetour.toString());
			
			if (userRetour != null && userRetour.getLogin().equalsIgnoreCase(request.getParameter("login"))
					&& userRetour.getPassword().equalsIgnoreCase(request.getParameter("password")))
			{
					
				dispatcher = request.getRequestDispatcher("home.jsp");
				String profil;
				if(userRetour.getProfil().equalsIgnoreCase("D")) {
					profil = "Directeur";
				}
				else {
					profil = "Responsable";
				}
				session.setAttribute("user", userRetour);
				session.setAttribute("profil", profil);
				session.setAttribute("students", studentService.getListStudent());
				session.setAttribute("courses", courseService.getAllCours());
					
			}
			else
			{
				dispatcher = request.getRequestDispatcher("index.jsp");
			}
		}
		catch (UserNotFoundException e)
		{
			session.setAttribute("error", e.getMessage());
			dispatcher = request.getRequestDispatcher("index.jsp");
		}
		
		dispatcher.forward(request, response);
	}
}
