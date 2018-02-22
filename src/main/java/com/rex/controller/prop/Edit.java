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
 * Servlet implementation class Edit
 */
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Edit() {
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

		ResponseBean bean = (new CommonModel()).edit(new PropBean(null, Integer.parseInt(request.getParameter("pid")),
				request.getParameter("p_type"), request.getParameter("t_type"), request.getParameter("title"), 0, 0, 0,
				0, 0, 0, Integer.parseInt(request.getParameter("price")),
				Integer.parseInt(request.getParameter("price_display")),
				Integer.parseInt(request.getParameter("available")), null, null, 0, 0, 0, 0, 0, 0, null, null, null,
				null, null));
		if (bean instanceof SuccessBean) {
			sess.setAttribute("process", "success");
		} else {
			sess.setAttribute("process", "failed");
		}
		sess.setAttribute("bean", bean);
		response.sendRedirect("./");
	}

}
