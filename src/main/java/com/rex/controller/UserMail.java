package com.rex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rex.bean.UserBean;
import com.rex.model.ClientModel;
import com.rex.util.Mailer;

/**
 * Servlet implementation class UserMail
 */
@WebServlet("/User-Mail")
public class UserMail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserMail() {
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
		String email = request.getParameter("to");
		String from = request.getParameter("from");
		String msg = request.getParameter("msg");
		HttpSession sess = request.getSession(true);
		String admin = null;
		if (sess.getAttribute("user") != null) {
			admin = ((UserBean) sess.getAttribute("user")).getFname();
		}
		if (admin == null || admin.length() == 0) {
			admin = "Admin";
		}
		if (Mailer.send(email, "Admin Notice",
				Mailer.getUserMailMsg(admin, (new ClientModel()).getNameViaEmail(email), msg, from))) {
			response.getWriter().println("{\"response\": \"User has been mailed Successfully!\"}");
		} else {
			response.getWriter().println("{\"response\": \"Can't mail to the user right now, try again.\"}");
		}
	}

}
