package com.rex.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rex.bean.SuccessBean;
import com.rex.bean.UserBean;
import com.rex.modal.AdminModal;

/**
 * Servlet implementation class AdminUpdateController
 */
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminModal admin = new AdminModal();
		HttpSession sess = request.getSession(true);
		UserBean s_user = (UserBean) sess.getAttribute("user");

		UserBean user = new UserBean(request.getParameter("fname"), request.getParameter("lname"),
				request.getParameter("email"), null, request.getParameter("gender"), request.getParameter("cont"),
				request.getParameter("street"), request.getParameter("town"), request.getParameter("city"),
				request.getParameter("state"));
		user.setUid(s_user.getUid());

		Object bean = admin.update(user);
		if (bean instanceof SuccessBean) {
			((SuccessBean) bean).setQuery("success");
			user.setEmail(s_user.getEmail());
			user.setPassword(s_user.getPassword());
			user.setSrc(s_user.getSrc());
			user.setAuth(s_user.getAuth());
			user.setTime(s_user.getTime());

			sess.setAttribute("user", user);
			((SuccessBean) bean).setSession("success");
			((SuccessBean) bean).setCleanUp("success");
			sess.setAttribute("process", "success");
			sess.setAttribute("bean", bean);
		} else {
			sess.setAttribute("process", "failed");
			sess.setAttribute("bean", bean);
		}
		response.sendRedirect("./");
	}

}
