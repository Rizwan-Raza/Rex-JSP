package com.rex.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rex.bean.AddressBean;
import com.rex.bean.MemberBean;
import com.rex.bean.SuccessBean;
import com.rex.bean.UserBean;
import com.rex.model.AdminModel;

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
		AdminModel admin = new AdminModel();
		HttpSession sess = request.getSession(true);
		MemberBean s_user = (MemberBean) sess.getAttribute("user");

		UserBean user = new UserBean(request.getParameter("fname"), request.getParameter("lname"),
				request.getParameter("email"), null, request.getParameter("gender"), request.getParameter("cont"));
		AddressBean address = new AddressBean(request.getParameter("street"), request.getParameter("town"),
				request.getParameter("city"), request.getParameter("state"));
		address.setAdd_id(s_user.getAddress().getAdd_id());
		user.setUid(s_user.getUser().getUid());

		MemberBean mem = new MemberBean(user, address);

		Object bean = admin.update(mem);
		if (bean instanceof SuccessBean) {
			((SuccessBean) bean).setQuery("success");
			mem.getUser().setEmail(s_user.getUser().getEmail());
			mem.getUser().setPassword(s_user.getUser().getPassword());
			mem.getUser().setSrc(s_user.getUser().getSrc());
			mem.getUser().setAuth(s_user.getUser().getAuth());
			mem.getUser().setTime(s_user.getUser().getTime());

			sess.setAttribute("user", mem);
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
