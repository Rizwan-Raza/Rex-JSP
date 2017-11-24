package com.rex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rex.bean.ErrorBean;
import com.rex.bean.LoginBean;
import com.rex.bean.UserBean;
import com.rex.modal.AdminLoginModal;

/**
 * Servlet implementation class ClientLoginController
 */
public class AdminLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminLoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		LoginBean userData = new LoginBean(request.getParameter("usrname"), request.getParameter("psw"));
		AdminLoginModal client = new AdminLoginModal();
		Object data = client.auth(userData);
		if (data != null && data instanceof ErrorBean) {
			ErrorBean error = (ErrorBean) data;
			response.sendRedirect("./?error=" + error.getCode());
			return;
		}
		if (data != null && data instanceof UserBean) {
			UserBean user = (UserBean) data;
			HttpSession sess = request.getSession(true);
			sess.setAttribute("log", "admin");
			sess.setAttribute("user", user);
			response.sendRedirect("./");
			return;
		}
		response.sendRedirect("./?error=X-X-1");
		return;
	}

}
