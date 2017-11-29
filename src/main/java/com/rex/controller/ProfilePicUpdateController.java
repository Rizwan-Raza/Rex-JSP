package com.rex.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class ProfilePicUpdateController
 */
public class ProfilePicUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String filePath;
	private int maxFileSize = 5000 * 1024;
	private int maxMemSize = 5000 * 1024;
	private File file;

	public void init() {
		// Get the file location where it would be stored.
		filePath = getServletContext().getInitParameter("user-upload");
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfilePicUpdateController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("In<br>");

		if (ServletFileUpload.isMultipartContent(request)) {
			out.println("Multipart<br>");
			filePath += request.getSession(true).getAttribute("log").toString() + "s/";

			DiskFileItemFactory factory = new DiskFileItemFactory(maxMemSize,
					new File(getServletContext().getInitParameter("temp-upload")));

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);

			// maximum file size to be uploaded.
			upload.setSizeMax(maxFileSize);

			try {
				out.println("Try Block<br>");
				// Parse the request to get file items.
				List<FileItem> fileItems = upload.parseRequest(request);

				// Process the uploaded file items
				Iterator<FileItem> i = fileItems.iterator();

				while (i.hasNext()) {
					out.println("File Selected<br>");
					FileItem fi = (FileItem) i.next();
					if (!fi.isFormField()) {
						out.println("With Form Field<br>");
						// Get the uploaded file parameters
						// String fieldName = fi.getFieldName();
						String fileName = fi.getName();
						// String contentType = fi.getContentType();
						// boolean isInMemory = fi.isInMemory();
						// long sizeInBytes = fi.getSize();

						// Write the file
						filePath = getServletContext().getRealPath(filePath);
						if (fileName.lastIndexOf("\\") >= 0) {
							file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\")));
						} else {
							file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\") + 1));
						}
						fi.write(file);
						// Uploaded
						out.println("Uploaded " + file.getAbsolutePath());
					}
				}
			} catch (Exception ex) {
				out.println("Catch<br>");
				out.println(ex);
			}
			// TODO Auto-generated method stub
			// String oldFile = request.getParameter("old_dp");
			// HttpSession sess = request.getSession(true);
			// String targetDir = "uploads/users/" + sess.getAttribute("login").toString() +
			// "s/";
		} else {
			// Hehehehe
			out.println("File Selection problem");
		}
		out.println("End<br>");
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
