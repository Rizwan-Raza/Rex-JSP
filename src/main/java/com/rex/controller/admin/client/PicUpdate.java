package com.rex.controller.admin.client;

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
import com.rex.model.ProfilePicUpdateModel;

/**
 * Servlet implementation class PicUpdate
 */
public class PicUpdate extends HttpServlet {
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

		if (ServletFileUpload.isMultipartContent(request)) {
			String uFilePath = filePath + (request.getParameter("auth") == "2" ? "admins/" : "clients/");

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
						String oFilePath = getServletContext().getRealPath(uFilePath);
						int filePart = fileName.lastIndexOf("\\");
						String uploadedFile;
						if (filePart >= 0) {
							uploadedFile = fileName.substring(filePart);
						} else {
							uploadedFile = fileName.substring(filePart + 1);
						}
						fi.write(new File(oFilePath + uploadedFile));
						// Uploaded
						SuccessBean process = new SuccessBean("P-U-1", "Client's Pic Updated Succcessfully", "upload",
								null);
						if (!request.getParameter("old_dp").equals("resources/uploads/users/temp.png")) {
							file = new File(getServletContext().getRealPath(request.getParameter("old_dp")));
							file.delete();
							process.setCleanUp("success");
						} else {
							process.setCleanUp("failed");
						}
						ProfilePicUpdateModel ppum = new ProfilePicUpdateModel();
						Object bean = ppum.update(uFilePath + uploadedFile, request.getParameter("uid"), process);
						if (bean instanceof SuccessBean) {
							sess.setAttribute("process", "success");

						} else {
							sess.setAttribute("process", "failed");
						}
						sess.setAttribute("bean", bean);
					}
				}
			} catch (Exception ex) {
				sess.setAttribute("process", "failed");
				sess.setAttribute("bean", new ErrorBean("P-U-3", ex.toString(), this.getClass().toGenericString()));
			}
		} else {
			sess.setAttribute("process", "failed");
			sess.setAttribute("bean", new ErrorBean("P-U-2", "File Selection Problem", this.getClass().toGenericString()));
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
