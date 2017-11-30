package com.rex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rex.bean.ErrorBean;
import com.rex.bean.SuccessBean;

/**
 * Servlet implementation class LogoutController
 */
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sess = request.getSession(false);
		if (request.getParameter("logout").equals("true")) {
			// session.setAttribute("user", null);
			sess.removeAttribute("log");
			sess.removeAttribute("user");
			sess.setAttribute("process", "success");
			sess.setAttribute("bean", new SuccessBean("L-O-1", "Logout Successfull!", "logout", "success"));
		} else {
			sess.setAttribute("process", "failed");
			sess.setAttribute("bean", new ErrorBean("L-O-1", "Logout Failed!", "LogoutController"));
		}
		response.sendRedirect("./");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
