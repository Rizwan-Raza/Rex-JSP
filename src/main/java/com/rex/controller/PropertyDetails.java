package com.rex.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PropertyDetails
 */
@WebServlet("/PropertyDetails/*")
public class PropertyDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PropertyDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("prop_id", request.getRequestURI().replaceAll("[^0-9]", ""));
		// response.getWriter().println(request.getAttribute("prop_id"));
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/property-details.jsp");
		dispatcher.forward(request, response);
	}
}
