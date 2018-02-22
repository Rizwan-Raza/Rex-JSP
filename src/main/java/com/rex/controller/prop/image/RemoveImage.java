package com.rex.controller.prop.image;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rex.bean.ErrorBean;
import com.rex.bean.ResponseBean;
import com.rex.bean.SuccessBean;
import com.rex.model.CommonModel;

/**
 * Servlet implementation class RemoveImage
 */
public class RemoveImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemoveImage() {
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
		ResponseBean obj = (new CommonModel()).removeImage(Integer.parseInt(request.getParameter("pid")),
				request.getParameter("id"));
		if (obj instanceof SuccessBean) {
			if ((new File(getServletContext().getRealPath(getServletContext().getInitParameter("prop-upload"))
					+ request.getParameter("id").substring(request.getParameter("id").lastIndexOf('/') + 1)))
							.delete()) {
				response.getWriter().println("{\"response\": \"OK\",\"message\": \"" + ((SuccessBean) obj).getMessage()
						+ "\", \"src\": \"" + request.getParameter("id") + "\"}");
			} else {
				response.getWriter().println(
						"{\"response\": \"OK\",\"message\": \"Entry removed, but file exists. Use CMS\", \"src\": \""
								+ request.getParameter("id") + "\"}");

			}
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
		doGet(request, response);
	}

}
