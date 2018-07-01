package com.rex.controller.prop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rex.bean.ReqProp;
import com.rex.bean.ResponseBean;
import com.rex.bean.SuccessBean;
import com.rex.model.CommonModel;

/**
 * Servlet implementation class EditRequest
 */
@WebServlet("/Edit-Request")
public class EditRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditRequest() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sess = request.getSession(true);

		String area = request.getParameter("c_area");
		String budget = request.getParameter("budget");

		area = area.replace(" Sq-Ft. - ", "-").replace(" Sq-Ft.", "");
		budget = budget.replaceAll("[^0-9\\-]", "");
		ReqProp rp = new ReqProp(Integer.parseInt(request.getParameter("pr_id")), null, request.getParameter("p_type"),
				request.getParameter("city"), request.getParameter("state"),
				Integer.parseInt(request.getParameter("bhk")), Integer.parseInt(request.getParameter("bath")), area,
				budget, null, null);
		ResponseBean bean = new CommonModel().editRequest(rp);

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
