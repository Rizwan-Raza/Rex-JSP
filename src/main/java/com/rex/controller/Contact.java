package com.rex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rex.bean.ErrorBean;
import com.rex.bean.FeedBean;
import com.rex.bean.ResponseBean;
import com.rex.bean.SuccessBean;
import com.rex.model.ContactModel;

/**
 * Servlet implementation class Contact
 */
@WebServlet("/Contact")
public class Contact extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		ResponseBean obj = (new ContactModel())
				.add(new FeedBean(0, Integer.parseInt(request.getParameter("uid")), request.getParameter("name"),
						request.getParameter("email"), request.getParameter("mobile"), request.getParameter("feedType"),
						request.getParameter("message"), Integer.parseInt(request.getParameter("rating"))));
		if (obj instanceof SuccessBean) {
			response.getWriter()
					.println("{\"response\": \"OK\",\"message\": \"" + ((SuccessBean) obj).getMessage() + "\"}");
		} else {
			response.getWriter()
					.println("{\"response\": \"KO\",\"message\": \"" + ((ErrorBean) obj).getMessage() + "\"}");
		}
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
