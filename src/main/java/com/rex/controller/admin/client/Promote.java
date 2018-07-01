package com.rex.controller.admin.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rex.bean.ErrorBean;
import com.rex.bean.ResponseBean;
import com.rex.bean.SuccessBean;
import com.rex.model.AdminModel;

/**
 * Servlet implementation class PromotionController
 */
@WebServlet("/Admin-Client-Promote")
public class Promote extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Promote() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		ResponseBean obj = (new AdminModel()).promote(request.getParameter("promote").equals("true") ? 2 : 1,
				Integer.parseInt(request.getParameter("id")));
		if (obj instanceof SuccessBean) {
			response.getWriter().println("{\"response\": \"OK\",\"message\": \"" + ((SuccessBean) obj).getMessage()
					+ "\", \"user_id\": \"" + request.getParameter("id") + "\"}");
		} else {
			response.getWriter()
					.println("{\"response\": \"KO\",\"message\": \"" + ((ErrorBean) obj).getMessage() + "\"}");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
