package com.rex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rex.bean.AddressBean;
import com.rex.bean.UserBean;
import com.rex.model.SignUpModel;
import com.rex.util.Mailer;

/**
 * Servlet implementation class SignUpController
 */
 @WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignUp() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		String psw = request.getParameter("psw");
		if (!psw.equals(request.getParameter("repsw"))) {
			response.getWriter().println("{\"response\": \"KO\",\"message\": \"Password Mismatch!\"}");
			return;
		}
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String name = fname + " " + lname;
		String id = (new SignUpModel()).add(new UserBean(0, fname, lname, email, psw, request.getParameter("gender"),
				request.getParameter("cont"), 0, null, null, new AddressBean(0, request.getParameter("street"),
						request.getParameter("town"), request.getParameter("city"), request.getParameter("state"))));
		if (id.matches("\\d+")) {
			if (Mailer.send(email, "Activate Yourself", Mailer.getActivationMsg(id, name))) {
				response.getWriter()
						.println("{\"response\": \"OK\",\"name\": \"" + name + "\", \"email\": \"" + email + "\"}");
			} else {
				response.getWriter()
						.println("{\"response\": \"KK\",\"name\": \"" + name + "\", \"email\": \"" + email + "\"}");
			}
		} else {
			response.getWriter().println("{\"response\": \"KO\",\"message\": \"" + id + "\"}");
		}
	}

}
