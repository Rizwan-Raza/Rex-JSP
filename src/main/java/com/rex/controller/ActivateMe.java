package com.rex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rex.bean.ErrorBean;
import com.rex.bean.ResponseBean;
import com.rex.bean.SuccessBean;
import com.rex.model.AdminModel;

/**
 * Servlet implementation class ActivateMe
 */
@WebServlet("/ActivateMe")
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
		ResponseBean obj = (new AdminModel()).activate(1, Integer.parseInt(request.getParameter("uid")));
		HttpSession sess = request.getSession(false);
		if (obj instanceof SuccessBean) {
			sess.setAttribute("process", "success");
			sess.setAttribute("bean", new SuccessBean("C-S-A-1",
					"Client activated successfully, now you can access you client section using your registered email and password",
					"client-act", "success"));
		} else {
			ErrorBean eb = (ErrorBean) obj;
			sess.setAttribute("process", "failed");
			sess.setAttribute("bean", new ErrorBean("C-S-A-1", eb.getMessage(), this.getClass().toGenericString()));
		}
		response.sendRedirect("./");
	}

}
