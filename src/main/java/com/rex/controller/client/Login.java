package com.rex.controller.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rex.bean.ErrorBean;
import com.rex.bean.LoginBean;
import com.rex.bean.SuccessBean;
import com.rex.bean.UserBean;
import com.rex.model.ClientModel;

/**
 * Servlet implementation class ClientLoginController
 */
@WebServlet("/Client-Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
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
		HttpSession sess = request.getSession(true);
		Object data = (new ClientModel())
				.auth(new LoginBean(request.getParameter("usrname"), request.getParameter("psw")));
		if (data instanceof UserBean) {
			UserBean user = (UserBean) data;
			sess.setAttribute("log", "client");
			sess.setAttribute("user", user);
			sess.setAttribute("process", "success");
			sess.setAttribute("bean", new SuccessBean("C-L-A-1", "Login Successfull!", "login", "success"));
		} else {
			ErrorBean error = (ErrorBean) data;
			sess.setAttribute("process", "failed");
			sess.setAttribute("bean", error);
		}
		response.sendRedirect("./");
	}

}
