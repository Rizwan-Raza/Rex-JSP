package com.rex.controller.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rex.bean.ErrorBean;
import com.rex.bean.ReqProp;
import com.rex.bean.ResponseBean;
import com.rex.bean.SuccessBean;
import com.rex.bean.UserBean;
import com.rex.model.ClientModel;

/**
 * Servlet implementation class PostRequirement
 */
@WebServlet("/Post-Requirement")
public class PostRequirement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PostRequirement() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String area = request.getParameter("c-area");
		String budget = request.getParameter("budget");
		area = area.replace(" Sq-Ft. - ", "-").replace(" Sq-Ft.", "");
		budget = budget.replaceAll("[^0-9\\-]", "");
		ReqProp rp = new ReqProp(0, (UserBean) request.getSession().getAttribute("user"),
				request.getParameter("p-type"), request.getParameter("city"), request.getParameter("state"),
				Integer.parseInt(request.getParameter("bhk")), Integer.parseInt(request.getParameter("bath")), area,
				budget, null, null);
		ResponseBean obj = new ClientModel().postRequirement(rp);

		if (obj instanceof SuccessBean) {
			response.getWriter()
					.println("{\"response\": \"OK\",\"message\": \"" + ((SuccessBean) obj).getMessage() + "\"}");
		} else {
			response.getWriter()
					.println("{\"response\": \"KO\",\"message\": \"" + ((ErrorBean) obj).getMessage() + "\"}");
		}
	}
}
