package com.rex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rex.bean.ErrorBean;
import com.rex.bean.SuccessBean;

/**
 * Servlet implementation class LogoutController
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Logout() {
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
			if (sess != null) {
				sess.removeAttribute("log");
				sess.removeAttribute("user");
			} else {
				sess = request.getSession(true);
			}
			sess.setAttribute("process", "success");
			sess.setAttribute("bean", new SuccessBean("G-L-O-1", "Logout Successfull!", "logout", "success"));
		} else {
			sess.setAttribute("process", "failed");
			sess.setAttribute("bean", new ErrorBean("G-L-O-1", "Logout Failed!", this.getClass().toGenericString()));
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
