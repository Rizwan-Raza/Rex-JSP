package com.rex.controller.admin.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rex.bean.ResponseBean;
import com.rex.bean.SuccessBean;
import com.rex.model.AdminModel;

/**
 * Servlet implementation class Password
 */
@WebServlet("/Admin-Client-Change-Password")
public class Password extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Password() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sess = request.getSession(true);
		ResponseBean bean = (new AdminModel()).clientPassword(request.getParameter("npsw"),
				Integer.parseInt(request.getParameter("uid")));
		if (bean instanceof SuccessBean) {
			sess.setAttribute("process", "success");
		} else {
			sess.setAttribute("process", "failed");
		}
		sess.setAttribute("bean", bean);
		response.sendRedirect("./");
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
