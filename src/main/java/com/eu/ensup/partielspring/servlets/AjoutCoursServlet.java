package com.eu.ensup.partielspring.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

import com.eu.ensup.partielspring.domaine.Course;
import com.eu.ensup.partielspring.domaine.Student;
import com.eu.ensup.partielspring.domaine.User;
import com.eu.ensup.partielspring.service.CoursServiceClient;
import com.eu.ensup.partielspring.service.ICoursServiceClient;
import com.eu.ensup.partielspring.service.IStudentServiceClient;

/**
 * Servlet implementation class AjoutCoursServlet
 */
public class AjoutCoursServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher dispatcher = null;
	private ICoursServiceClient courseService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjoutCoursServlet() {
        courseService = new CoursServiceClient();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("message", null);
		dispatcher = request.getRequestDispatcher("coursAjout.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nbHeure = Integer.valueOf(request.getParameter("courseTime"));
		Course cours = new Course(request.getParameter("courseTheme"),nbHeure);		
		HttpSession session = request.getSession();
		//session.setAttribute("cours", null);
		//user = (User) session.getAttribute("user");
		
		System.out.println(cours.getNumberHours());
		
		Response responseFromService = courseService.createCours(cours);
		if (responseFromService.getStatus()==200 || responseFromService.getStatus()==204) {
			session.setAttribute("message", "Elément enregistré avec succès");
		} else {
			session.setAttribute("message", "Un problème est survenu. Veuillez réessayer.");
		}
		
		session.setAttribute("courses", courseService.getAllCours());
		
		dispatcher = request.getRequestDispatcher("cours.jsp");
		dispatcher.forward(request, response);

	}

}
