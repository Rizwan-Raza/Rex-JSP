package com.rex.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rex.util.DBConnector;

/**
 * Servlet implementation class EmailChecker
 */
public class EmailChecker extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;
	private PreparedStatement stmt;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmailChecker() {
		conn = new DBConnector().getConnection();
		try {
			stmt = conn.prepareStatement("SELECT count(email) FROM clients WHERE email=?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			stmt.setString(1, request.getParameter("email"));

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				response.getWriter().println("\"response\": \"OK\"");
			} else {
				response.getWriter().println("\"response\": \"KO\"");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
