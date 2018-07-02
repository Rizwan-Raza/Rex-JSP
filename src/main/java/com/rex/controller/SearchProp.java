package com.rex.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rex.model.InactiveModel;

/**
 * Servlet implementation class SearchProp
 */
@WebServlet("/Search")
public class SearchProp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchProp() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		RequestDispatcher rd = request.getRequestDispatcher("/views/search-result.jsp");
		String range;
		int low, high;
		range = request.getParameter("range");
		range = range.replaceAll("[^0-9\\-]", "");

		if (request.getParameter("range-all") != null && request.getParameter("range-all").equalsIgnoreCase("on")) {
			low = 0;
			high = Integer.MAX_VALUE;
		} else {
			range = request.getParameter("range");
			range = range.replaceAll("[^0-9\\-]", "");
			low = Integer.parseInt(range.split("-")[0]) * 100000;
			high = Integer.parseInt(range.split("-")[1]) * 100000;
		}
		String type = request.getParameter("type");
		if (type.equalsIgnoreCase("All")) {
			type = "%";
		} else {
			type = "%" + type + "%";
		}
		request.setAttribute("result",
				new InactiveModel().search(type, low, high, "%" + request.getParameter("q") + "%"));
		rd.include(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
