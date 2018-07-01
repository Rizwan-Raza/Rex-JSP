package com.rex.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rex.bean.AddressBean;
import com.rex.bean.ResponseBean;
import com.rex.bean.SuccessBean;
import com.rex.bean.UserBean;
import com.rex.model.AdminModel;

/**
 * Servlet implementation class AdminUpdateController
 */
@WebServlet("/Admin-Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Update() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminModel admin = new AdminModel();
		HttpSession sess = request.getSession(true);
		UserBean s_user = (UserBean) sess.getAttribute("user");

		UserBean user = new UserBean(s_user.getUid(), request.getParameter("fname"), request.getParameter("lname"),
				s_user.getEmail(), s_user.getPassword(), request.getParameter("gender"), request.getParameter("cont"),
				s_user.getAuth(), s_user.getSrc(), s_user.getTime(),
				new AddressBean(s_user.getAddress().getAdd_id(), request.getParameter("street"),
						request.getParameter("town"), request.getParameter("city"), request.getParameter("state")));

		ResponseBean bean = admin.update(user);
		if (bean instanceof SuccessBean) {
			((SuccessBean) bean).setQuery("success");

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
