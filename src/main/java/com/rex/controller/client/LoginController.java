package com.rex.controller.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rex.bean.UserBean;
import com.rex.bean.ErrorBean;
import com.rex.bean.LoginBean;
import com.rex.bean.SuccessBean;
import com.rex.modal.ClientModal;

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
		// TODO Auto-generated method stub
		LoginBean userData = new LoginBean(request.getParameter("usrname"), request.getParameter("psw"));
		ClientModal client = new ClientModal();
		HttpSession sess = request.getSession(true);
		Object data = client.auth(userData);
		if (data instanceof UserBean) {
			UserBean user = (UserBean) data;
			sess.setAttribute("log", "client");
			sess.setAttribute("user", user);
			sess.setAttribute("process", "success");
			sess.setAttribute("bean", new SuccessBean("C-A-1", "Login Successfull!", "login", "success"));
		} else {
			ErrorBean error = (ErrorBean) data;
			sess.setAttribute("process", "failed");
			sess.setAttribute("bean", error);
		}
		response.sendRedirect("./");
	}

}