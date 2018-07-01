package com.rex.controller.prop;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rex.bean.PropBean;
import com.rex.bean.ResponseBean;
import com.rex.bean.SuccessBean;
import com.rex.model.CommonModel;

/**
 * Servlet implementation class Informations
 */
@WebServlet("/Prop-Change-Informations")
public class Informations extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Informations() {
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

		ResponseBean bean = (new CommonModel())
				.changeInfo(new PropBean(null, Integer.parseInt(request.getParameter("pid")), null, null, null, 0, 0, 0,
						0, 0, 0, 0, 0, 0, Arrays.asList(request.getParameterValues("inHouse")), null, 0, 0, 0,
						Integer.parseInt(request.getParameter("units")),
						((request.getParameter("floor") == null || request.getParameter("floor") == ""
								|| request.getParameter("floor").equals("")) ? -5
										: Integer.parseInt(request.getParameter("floor"))),
						Integer.parseInt(request.getParameter("t_floors")),
						((request.getParameter("desc") == null || request.getParameter("desc") == "") ? null
								: request.getParameter("desc")),
						((request.getParameter("tnc") == null || request.getParameter("tnc") == "") ? null
								: request.getParameter("tnc")),
						null, null, null, null));

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
