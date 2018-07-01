package com.rex.controller.prop.image;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Random;

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
import com.rex.model.CommonModel;

/**
 * Servlet implementation class AddImage
 */
@WebServlet("/Prop-Add-Image")
@MultipartConfig(fileSizeThreshold = 6291456, // 6 MB
		maxFileSize = 10485760L, // 10 MB
		maxRequestSize = 20971520L // 20 MB
)
public class AddImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String filePath;

	public void init() {
		// Get the file location where it would be stored.
		filePath = getServletContext().getInitParameter("prop-upload");
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddImage() {
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

		Part filePart = request.getPart("propImage"); // Retrieves <input type="file" name="file">
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
		// Write the file
		String oFilePath = getServletContext().getRealPath(filePath);
		int filenamePart = fileName.lastIndexOf("\\");
		String uploadedFile;
		if (filenamePart >= 0) {
			uploadedFile = fileName.substring(filenamePart);
		} else {
			uploadedFile = fileName.substring(filenamePart + 1);
		}
		uploadedFile = uploadedFile.replace(".", (new Random()).nextInt(100) + ".");

		filePart.write(oFilePath + uploadedFile);
		ResponseBean bean = (new CommonModel()).addImage(Integer.parseInt(request.getParameter("pid")),
				filePath + uploadedFile);
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
		doGet(request, response);
	}

}
