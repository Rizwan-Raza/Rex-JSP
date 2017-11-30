package com.rex.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.rex.bean.ErrorBean;
import com.rex.bean.SuccessBean;
import com.rex.bean.UserBean;
import com.rex.modal.ProfilePicUpdateModal;

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
		HttpSession sess = request.getSession(true);

		if (ServletFileUpload.isMultipartContent(request)) {
			filePath += request.getSession(true).getAttribute("log").toString() + "s/";

			DiskFileItemFactory factory = new DiskFileItemFactory(maxMemSize,
					new File(getServletContext().getInitParameter("temp-upload")));

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);

			// maximum file size to be uploaded.
			upload.setSizeMax(maxFileSize);

			try {
				// Parse the request to get file items.
				List<FileItem> fileItems = upload.parseRequest(request);

				// Process the uploaded file items
				Iterator<FileItem> i = fileItems.iterator();

				while (i.hasNext()) {
					FileItem fi = (FileItem) i.next();
					if (!fi.isFormField()) {
						// Get the uploaded file parameters
						// String fieldName = fi.getFieldName();
						String fileName = fi.getName();
						// String contentType = fi.getContentType();
						// boolean isInMemory = fi.isInMemory();
						// long sizeInBytes = fi.getSize();

						// Write the file
						String oFilePath = filePath;
						filePath = getServletContext().getRealPath(filePath);
						int filePart = fileName.lastIndexOf("\\");
						String uploadedFile;
						if (filePart >= 0) {
							uploadedFile = fileName.substring(filePart);
						} else {
							uploadedFile = fileName.substring(filePart + 1);
						}
						file = new File(filePath + uploadedFile);
						fi.write(file);
						// Uploaded
						SuccessBean process = new SuccessBean("P-U-1", "Profile Pic Updated Succcessfully", "upload", null);
						if (!request.getParameter("old_dp").equals("resources/uploads/users/temp.png")) {
							file = new File(getServletContext().getRealPath(request.getParameter("old_dp")));
							file.delete();
							process.setCleanUp("success");
						} else {
							process.setCleanUp("failed");
						}
						UserBean curr_user = (UserBean) sess.getAttribute("user");
						ProfilePicUpdateModal ppum = new ProfilePicUpdateModal();
						Object bean = ppum.update(oFilePath + uploadedFile, curr_user.getUid(), process);
						if (bean instanceof SuccessBean) {
							sess.setAttribute("user",
									new UserBean(curr_user.getUid(), curr_user.getFname(), curr_user.getLname(),
											curr_user.getEmail(), curr_user.getPassword(), curr_user.getGender(),
											curr_user.getContact(), curr_user.getStreet(), curr_user.getTown(),
											curr_user.getCity(), curr_user.getState(), null, oFilePath + uploadedFile,
											curr_user.getTime()));
							((SuccessBean) bean).setSession("success");
							sess.setAttribute("process", "success");
							((SuccessBean) bean).setCompletion("success");
							sess.setAttribute("bean", bean);

						} else {
							sess.setAttribute("process", "failed");
							sess.setAttribute("bean", bean);
						}
					}
				}
			} catch (Exception ex) {
				sess.setAttribute("process", "failed");
				sess.setAttribute("bean", new ErrorBean("P-U-3", ex.getMessage(), "ProfilePicUpdateController"));
			}
		} else {
			sess.setAttribute("process", "failed");
			sess.setAttribute("bean", new ErrorBean("P-U-2", "File Selection Problem", "ProfilePicUpdateController"));
		}
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
