package com.rex.controller.client;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.rex.bean.AddressBean;
import com.rex.bean.PropBean;
import com.rex.bean.SuccessBean;
import com.rex.bean.UserBean;
import com.rex.model.ClientModel;

/**
 * Servlet implementation class SellProperty
 */
@WebServlet("/SellProperty")
@MultipartConfig(fileSizeThreshold = 6291456, // 6 MB
		maxFileSize = 10485760L, // 10 MB
		maxRequestSize = 20971520L // 20 MB
)
public class SellProperty extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String filePath;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SellProperty() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {
		// Get the file location where it would be stored.
		filePath = getServletContext().getInitParameter("prop-upload");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sess = request.getSession(true);
		List<String> images = new ArrayList<String>();

		String oFilePath = getServletContext().getRealPath(filePath);
		List<Part> fileParts = request.getParts().stream().filter(part -> "images".equals(part.getName()))
				.collect(Collectors.toList()); // Retrieves <input type="file" name="images" multiple="true">
		for (Part filePart : fileParts) {
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
			int filenamePart = fileName.lastIndexOf("\\");
			String uploadedFile;
			if (filenamePart >= 0) {
				uploadedFile = fileName.substring(filenamePart);
			} else {
				uploadedFile = fileName.substring(filenamePart + 1);
			}
			uploadedFile = uploadedFile.replace(".", (new Random()).nextInt(100) + ".");

			filePart.write(oFilePath + uploadedFile);
			images.add(filePath + uploadedFile);
		}

		Object bean = (new ClientModel()).sell(new PropBean(((UserBean) sess.getAttribute("user")), 0,
				request.getParameter("p-type"), request.getParameter("t-type"), request.getParameter("title"),
				Integer.parseInt(request.getParameter("bhk")), Integer.parseInt(request.getParameter("bath")),
				((request.getParameter("age") == null || request.getParameter("age") == "") ? 0
						: Integer.parseInt(request.getParameter("age"))),
				Integer.parseInt(request.getParameter("furnished")), Integer.parseInt(request.getParameter("p-area")),
				Integer.parseInt(request.getParameter("land")), Integer.parseInt(request.getParameter("price")),
				Integer.parseInt(request.getParameter("price-display")),
				Integer.parseInt(request.getParameter("available")),
				Arrays.asList(request.getParameterValues("inHouse")), images,
				Integer.parseInt(request.getParameter("h-dis")), Integer.parseInt(request.getParameter("s-dis")),
				Integer.parseInt(request.getParameter("r-dis")), Integer.parseInt(request.getParameter("units")),
				((request.getParameter("floor") == null || request.getParameter("floor") == ""
						|| request.getParameter("floor").equals("")) ? -5
								: Integer.parseInt(request.getParameter("floor"))),
				Integer.parseInt(request.getParameter("t-floors")),
				(request.getParameter("desc") == null || request.getParameter("desc") == "") ? null
						: request.getParameter("desc"),
				(request.getParameter("tnc") == null || request.getParameter("tnc") == "") ? null
						: request.getParameter("tnc"),
				null, null, new AddressBean(0, request.getParameter("street"), request.getParameter("town"),
						request.getParameter("city"), request.getParameter("state")),
				null));
		if (bean instanceof SuccessBean) {
			sess.setAttribute("process", "success");
		} else {
			sess.setAttribute("process", "failed");
		}
		sess.setAttribute("bean", bean);
		response.sendRedirect("./");
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
