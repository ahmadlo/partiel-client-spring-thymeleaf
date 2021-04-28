package com.eu.ensup.partielspring.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

import com.eu.ensup.partielspring.domaine.Course;
import com.eu.ensup.partielspring.domaine.User;
import com.eu.ensup.partielspring.service.CoursServiceClient;
import com.eu.ensup.partielspring.service.ICoursServiceClient;

/**
 * Servlet implementation class ModifCoursServlet
 */
public class ModifCoursServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ICoursServiceClient coursService;
	private RequestDispatcher dispatcher = null;
	private User user = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifCoursServlet() {
    	coursService = new CoursServiceClient();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("message", null);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Course cours = new Course(
				request.getParameter("courseTheme"),
				Integer.valueOf(request.getParameter("courseTime"))
			);
		Long idCours = Long.valueOf(request.getParameter("id"));
		
		HttpSession session = request.getSession();
		session.setAttribute("cours", null);
		user = (User) session.getAttribute("user");
		
		Response responseFromService = coursService.updateCours(idCours, cours);
		if (responseFromService.getStatus()==200 || responseFromService.getStatus()==204) {
			session.setAttribute("message", "Elément modifié avec succès");
		} else {
			session.setAttribute("message", "Un problème est survenu. Veuillez réessayer.");
		}
		
		session.setAttribute("cours", null);
		session.setAttribute("courses", coursService.getAllCours());
		
		dispatcher = request.getRequestDispatcher("cours.jsp");
		dispatcher.forward(request, response);
		
	}

}
