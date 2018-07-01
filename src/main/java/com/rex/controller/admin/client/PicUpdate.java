package com.rex.controller.admin.client;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.rex.bean.ResponseBean;
import com.rex.bean.SuccessBean;
import com.rex.model.ProfilePicUpdateModel;

/**
 * Servlet implementation class PicUpdate
 */
@WebServlet("/Admin-Client-Profile-Update")
@MultipartConfig(fileSizeThreshold = 6291456, // 6 MB
		maxFileSize = 10485760L, // 10 MB
		maxRequestSize = 20971520L // 20 MB
)
public class PicUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String filePath;

	public void init() {
		// Get the file location where it would be stored.
		filePath = getServletContext().getInitParameter("user-upload");
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PicUpdate() {
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

		String uFilePath = filePath + (request.getParameter("auth") == "2" ? "admins/" : "clients/");

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
		SuccessBean process = new SuccessBean("C-P-U-1", "Client's Pic Updated Succcessfully", "upload", null);
		if (!request.getParameter("old_dp").equals("resources/uploads/users/temp.png")) {
			new File(getServletContext().getRealPath(request.getParameter("old_dp"))).delete();

			process.setCleanUp("success");
		} else {
			process.setCleanUp("failed");
		}
		ProfilePicUpdateModel ppum = new ProfilePicUpdateModel();
		ResponseBean bean = ppum.update(uFilePath + uploadedFile, Integer.parseInt(request.getParameter("uid")),
				process);
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
