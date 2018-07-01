package com.rex.controller.prop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rex.bean.ErrorBean;
import com.rex.bean.ResponseBean;
import com.rex.bean.SuccessBean;
import com.rex.bean.UserBean;
import com.rex.model.CommonModel;

/**
 * Servlet implementation class DeleteRequest
 */
@WebServlet("/Delete-Req-Prop")
public class DeleteRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteRequest() {
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
		String id = request.getParameter("id");
		ResponseBean obj = (new CommonModel()).deleteRequest(Integer.parseInt(id));
		if (obj instanceof SuccessBean) {
			response.getWriter().println("{\"response\": \"OK\",\"message\": \"" + ((SuccessBean) obj).getMessage()
					+ "\", \"pid\": \"" + request.getParameter("id") + "\", \"pr_id\": \"" + id + "\", \"type\":\""
					+ (((UserBean) request.getSession().getAttribute("user")).getAuth() == 2 ? "admin" : "client")
					+ "\"}");
		} else {
			response.getWriter()
					.println("{\"response\": \"KO\",\"message\": \"" + ((ErrorBean) obj).getMessage() + "\"}");
		}

	}

}
