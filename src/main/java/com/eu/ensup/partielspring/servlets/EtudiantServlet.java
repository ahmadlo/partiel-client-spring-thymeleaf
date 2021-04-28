package com.eu.ensup.partielspring.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import com.eu.ensup.partielspring.domaine.Student;
import com.eu.ensup.partielspring.domaine.User;
import com.eu.ensup.partielspring.service.CoursServiceClient;
import com.eu.ensup.partielspring.service.ICoursServiceClient;
import com.eu.ensup.partielspring.service.IStudentServiceClient;
import com.eu.ensup.partielspring.service.StudentServiceClient;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;



/**
 * Servlet implementation class EtudiantServlet
 */
//@WebServlet("/Etudiant")
public class EtudiantServlet extends HttpServlet {
	
	//private static final long serialVersionUID = 1L;
	
	private IStudentServiceClient studentService;
	private ICoursServiceClient courseService;
	private RequestDispatcher dispatcher = null;
	private User user = null;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EtudiantServlet() {
		courseService = new CoursServiceClient();
		studentService = new StudentServiceClient();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		user = (User) session.getAttribute("user");
		
		session.setAttribute("message", null);
		session.setAttribute("students",studentService.getListStudent() );
		session.setAttribute("courses",courseService.getAllCours());
		
		dispatcher = request.getRequestDispatcher("etudiant.jsp");
		dispatcher.forward(request, response);
	}

	


	
}
