package com.rex.controller.prop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rex.bean.PropBean;
import com.rex.bean.ResponseBean;
import com.rex.bean.SuccessBean;
import com.rex.model.CommonModel;

/**
 * Servlet implementation class Features
 */
public class Features extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Features() {
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
		ResponseBean bean = (new CommonModel()).changeFeatures(new PropBean(null,
				Integer.parseInt(request.getParameter("pid")), null, null, null,
				Integer.parseInt(request.getParameter("bhk")), Integer.parseInt(request.getParameter("bath")),
				Integer.parseInt(request.getParameter("age")), Integer.parseInt(request.getParameter("furnished")),
				Integer.parseInt(request.getParameter("p_area")), Integer.parseInt(request.getParameter("land")), 0, 0,
				0, null, null, Integer.parseInt(request.getParameter("h_dis")),
				Integer.parseInt(request.getParameter("s_dis")), Integer.parseInt(request.getParameter("r_dis")), 0, 0,
				0, null, null, null, null, null));
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
