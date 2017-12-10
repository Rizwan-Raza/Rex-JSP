package com.rex.controller.admin.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rex.bean.ErrorBean;
import com.rex.modal.AdminModal;

/**
 * Servlet implementation class DeleteController
 */
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Delete() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		Object obj = (new AdminModal()).delete(request.getParameter("id"));
		if (obj instanceof String) {
			response.getWriter().println("{\"response\": \"OK\",\"message\": \"" + obj + "\", \"user_id\": \""
					+ request.getParameter("id") + "\"}");
		} else {
			ErrorBean eb = (ErrorBean) obj;
			response.getWriter().println("{\"response\": \"KO\",\"message\": \"" + eb.getMessage() + "\"}");
		}
	}

}