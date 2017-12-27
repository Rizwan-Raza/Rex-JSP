package com.rex.controller.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rex.bean.AddressBean;
import com.rex.bean.PropBean;
import com.rex.bean.UserBean;
import com.rex.model.ClientModel;
import com.rex.util.Mailer;

/**
 * Servlet implementation class SellProperty
 */
public class SellProperty extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SellProperty() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String p_type = request.getParameter("p-type");
		String t_type = request.getParameter("t-type");
		String title = request.getParameter("title");
		String street = request.getParameter("street");
		String town = request.getParameter("town");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String bhk = request.getParameter("bhk");
		String bath = request.getParameter("bath");
		String age = request.getParameter("age");
		if (age == null || age == "") {
			age = "0";
		}
		String furn = request.getParameter("furnished");
		String p_area = request.getParameter("p-area");
		String land = request.getParameter("land");
		String price = request.getParameter("price");
		String price_display = request.getParameter("price-display");
		String available = request.getParameter("available");
		String amens = request.getParameter("in-house");
		String hosp = request.getParameter("h-dis");
		String schl = request.getParameter("s-dis");
		String rail = request.getParameter("r-dis");
		String units = request.getParameter("units");
		String floor = request.getParameter("floor");
		if (floor == null || floor == "") {
			floor = "NULL";
		}
		String t_floors = request.getParameter("t-floors");
		String desc = request.getParameter("desc");
		if (desc == null || desc == "") {
			desc = "NULL";
		}
		String tnc = request.getParameter("tnc");
		if (tnc == null || tnc == "") {
			tnc = "NULL";
		}

		String id = (new ClientModel()).sell(new PropBean());
		if (id.matches("\\d+")) {
			Mailer.send(email, "Activate Yourself", Mailer.getActivationMsg(id, name));
			response.getWriter()
					.println("{\"response\": \"OK\",\"name\": \"" + name + "\", \"email\": \"" + email + "\"}");
		} else {
			response.getWriter().println("{\"response\": \"KO\",\"message\": \"" + id + "\"}");
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
