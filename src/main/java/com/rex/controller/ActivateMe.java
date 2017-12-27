package com.rex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rex.bean.ErrorBean;
import com.rex.bean.SuccessBean;
import com.rex.model.AdminModel;

/**
 * Servlet implementation class ActivateMe
 */
public class ActivateMe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ActivateMe() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object obj = (new AdminModel()).activate("1", request.getParameter("uid"));
		HttpSession sess = request.getSession(false);
		if (obj instanceof String) {
			sess.setAttribute("process", "success");
			sess.setAttribute("bean", new SuccessBean("C-Act-1",
					"Client activated successfully, now you can access you client section using your registered email and password",
					"client-act", "success"));
		} else {
			ErrorBean eb = (ErrorBean) obj;
			sess.setAttribute("process", "failed");
			sess.setAttribute("bean", new ErrorBean("C-Act-1", eb.getMessage(), this.getClass().toGenericString()));
		}
		response.sendRedirect("./");
	}

}
