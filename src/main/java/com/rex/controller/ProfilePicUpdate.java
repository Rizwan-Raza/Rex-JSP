package com.rex.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.rex.bean.AddressBean;
import com.rex.bean.ResponseBean;
import com.rex.bean.SuccessBean;
import com.rex.bean.UserBean;
import com.rex.model.ProfilePicUpdateModel;

/**
 * Servlet implementation class ProfilePicUpdateController
 */
@MultipartConfig(fileSizeThreshold = 6291456, // 6 MB
		maxFileSize = 10485760L, // 10 MB
		maxRequestSize = 20971520L // 20 MB
)
public class ProfilePicUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String filePath;

	public void init() {
		// Get the file location where it would be stored.
		filePath = getServletContext().getInitParameter("user-upload");
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfilePicUpdate() {
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

		String uFilePath = filePath + request.getSession(true).getAttribute("log").toString() + "s/";

		Part filePart = request.getPart("dp"); // Retrieves <input type="file" name="file">
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.

		// Write the file
		String oFilePath = getServletContext().getRealPath(uFilePath);
		int filenamePart = fileName.lastIndexOf("\\");
		String uploadedFile;
		if (filenamePart >= 0) {
			uploadedFile = fileName.substring(filenamePart);
		} else {
			uploadedFile = fileName.substring(filenamePart + 1);
		}
		filePart.write(oFilePath + uploadedFile);

		// Uploaded
		SuccessBean process = new SuccessBean("G-P-U-1", "Profile Pic Updated Succcessfully", "upload", null);
		if (!request.getParameter("old_dp").equals("resources/uploads/users/temp.png")) {
			new File(getServletContext().getRealPath(request.getParameter("old_dp"))).delete();
			process.setCleanUp("success");
		} else {
			process.setCleanUp("failed");
		}
		UserBean curr_user = (UserBean) sess.getAttribute("user");
		ProfilePicUpdateModel ppum = new ProfilePicUpdateModel();
		ResponseBean bean = ppum.update(uFilePath + uploadedFile, curr_user.getUid(), process);
		if (bean instanceof SuccessBean) {
			sess.setAttribute("user",
					new UserBean(curr_user.getUid(), curr_user.getFname(), curr_user.getLname(), curr_user.getEmail(),
							curr_user.getPassword(), curr_user.getGender(), curr_user.getContact(), curr_user.getAuth(),
							uFilePath + uploadedFile, curr_user.getTime(),
							new AddressBean(curr_user.getAddress().getAdd_id(), curr_user.getAddress().getStreet(),
									curr_user.getAddress().getTown(), curr_user.getAddress().getCity(),
									curr_user.getAddress().getState())));
			((SuccessBean) bean).setSession("success");
			sess.setAttribute("process", "success");
			((SuccessBean) bean).setCompletion("success");

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
