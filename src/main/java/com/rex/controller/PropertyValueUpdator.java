package com.rex.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PropertyValueUpdator
 */
@WebServlet("/PropValUpdator")
public class PropertyValueUpdator extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PropertyValueUpdator() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String filename = request.getParameter("file");
		System.out.println(filename);
		OutputStream out = null;
		try {

//			Properties props = new Properties();
			Properties props = System.getProperties();
			File f = new File(filename + ".properties");
			if (f.exists()) {

				props.load(new FileReader(f));
				// Change your values here
				props.setProperty(request.getParameter("key"), request.getParameter("value"));
			} else {
				f.createNewFile();
			}

			out = new FileOutputStream(f);
			props.store(out, "This is an optional header comment string");

			for (String key : props.stringPropertyNames()) {
				String value = props.getProperty(key);
				System.out.println(key + "=" + value);
			}
			System.out.println(f.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (out != null) {

				try {

					out.close();
				} catch (IOException ex) {

					System.out
							.println("IOException: Could not close myApp.properties output stream; " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		}

		// String filename = request.getParameter("file");
		// InputStream in = getClass().getClassLoader().getResourceAsStream(filename +
		// ".properties");
		// Properties props = new Properties();
		// props.load(in);
		// in.close();
		//
		// FileOutputStream out = new FileOutputStream(getClass().getResource("/" +
		// filename + ".properties").getPath());
		// props.setProperty(request.getParameter("key"),
		// request.getParameter("value"));
		// props.store(out, null);
		// out.close();

	}

}
