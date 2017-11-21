package com.rex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rex.bean.SignUpBean;
import com.rex.modal.SignUpModal;

/**
 * Servlet implementation class SignUpController
 */
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		if(!request.getParameter("psw").equals(request.getParameter("repsw"))) {
			response.getWriter().println("{'response': 'KO','message': 'Password Mismatch!'}");
			return;
		}
		
		SignUpBean userData = new SignUpBean();
		userData.setFname(request.getParameter("fname"));
		userData.setLname(request.getParameter("lname"));
		userData.setEmail(request.getParameter("email"));
		userData.setPassword(request.getParameter("psw"));
		userData.setGender(request.getParameter("gender"));
		userData.setContact(request.getParameter("cont"));
		userData.setStreet(request.getParameter("street"));
		userData.setTown(request.getParameter("town"));
		userData.setCity(request.getParameter("city"));
		userData.setState(request.getParameter("state"));
		
		if((new SignUpModal()).add(userData)) {
			response.getWriter().println("{'response': 'OK','message': '"+request.getParameter("fname")+" "+request.getParameter("lname")+"'}");
		} else {
			response.getWriter().println("{'response': 'KO','message': '"+request.getParameter("fname")+" "+request.getParameter("lname")+"'}");			
		}
	}

}
