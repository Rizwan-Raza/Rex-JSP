package com.rex.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rex.bean.ErrorBean;
import com.rex.bean.LoginBean;
import com.rex.bean.MemberBean;
import com.rex.bean.SuccessBean;
import com.rex.model.AdminModel;

/**
 * Servlet implementation class ClientLoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sess = request.getSession(true);
		LoginBean userData = new LoginBean(request.getParameter("usrname"), request.getParameter("psw"));
		AdminModel admin = new AdminModel();
		Object data = admin.auth(userData);
		if (data instanceof MemberBean) {
			MemberBean user = (MemberBean) data;
			sess.setAttribute("log", "admin");
			sess.setAttribute("user", user);
			sess.setAttribute("process", "success");
			sess.setAttribute("bean", new SuccessBean("A-A-1", "Login Successfull!", "login", "success"));
		} else {
			ErrorBean error = (ErrorBean) data;
			sess.setAttribute("process", "failed");
			sess.setAttribute("bean", error);
		}
		response.sendRedirect("./");
	}

}
