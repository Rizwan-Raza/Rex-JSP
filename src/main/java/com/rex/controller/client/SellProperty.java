package com.rex.controller.client;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.rex.bean.AddressBean;
import com.rex.bean.ErrorBean;
import com.rex.bean.PropBean;
import com.rex.bean.SuccessBean;
import com.rex.bean.UserBean;
import com.rex.model.ClientModel;

/**
 * Servlet implementation class SellProperty
 */
public class SellProperty extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String filePath;
	private int maxFileSize = 5000 * 1024;
	private int maxMemSize = 5000 * 1024;

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
		if (ServletFileUpload.isMultipartContent(request)) {
			response.setContentType("text/html");
			List<String> amens = new ArrayList<String>();
			List<String> images = new ArrayList<String>();
			DiskFileItemFactory factory = new DiskFileItemFactory(maxMemSize,
					new File(getServletContext().getInitParameter("temp-upload")));

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);

			// maximum file size to be uploaded.
			upload.setSizeMax(maxFileSize);
			try {
				List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				for (FileItem item : items) {
					if (item.isFormField()) {
						// Process regular form field (input type="text|radio|checkbox|etc", select,
						// etc).
						if (item.getFieldName().equalsIgnoreCase("inHouse")) {
							amens.add(item.getString());
						} else {
							request.setAttribute(item.getFieldName(), item.getString());
						}
					} else {
						// Process form file field (input type="file").
						if (item.getFieldName().equalsIgnoreCase("images")) {
							String oFilePath = getServletContext().getRealPath(filePath);
							String fileName = FilenameUtils.getName(item.getName());
							int filePart = fileName.lastIndexOf("\\");
							String uploadedFile;
							if (filePart >= 0) {
								uploadedFile = fileName.substring(filePart);
							} else {
								uploadedFile = fileName.substring(filePart + 1);
							}
							item.write(new File(oFilePath + uploadedFile));
							images.add(filePath + uploadedFile);
						}
					}
				}
			} catch (Exception e) {
				sess.setAttribute("process", "failed");
				sess.setAttribute("bean", new ErrorBean("C-S-P-2", e.toString(), this.getClass().toGenericString()));
			}
			// Enumeration<String> names = request.getAttributeNames();
			// while (names.hasMoreElements()) {
			// String e = names.nextElement();
			// out.println(e + "=" + request.getAttribute(e) + "<br>");
			// }
			// for (String amen : amens) {
			// out.println("Amenity=" + amen + "<br>");
			// }
			// for (String image : images) {
			// out.println("Images=" + image + "<br>");
			// }
			// List<String> list = Arrays.asList(request.getParameterValues("inHouse"));
			// for (String e : list)
			// out.println(e);
			// out.close();
			// HttpSession sess = request.getSession(true);
			Object bean = (new ClientModel()).sell(new PropBean(((UserBean) sess.getAttribute("user")), null,
					(String) request.getAttribute("p-type"), (String) request.getAttribute("t-type"),
					(String) request.getAttribute("title"), (String) request.getAttribute("bhk"),
					(String) request.getAttribute("bath"),
					(String) ((request.getAttribute("age") == null || request.getAttribute("age") == "") ? "0"
							: request.getAttribute("age")),
					(String) request.getAttribute("furnished"), (String) request.getAttribute("p-area"),
					(String) request.getAttribute("land"), (String) request.getAttribute("price"),
					(String) request.getAttribute("price-display"), (String) request.getAttribute("available"), amens,
					(String) request.getAttribute("h-dis"), (String) request.getAttribute("s-dis"),
					(String) request.getAttribute("r-dis"), (String) request.getAttribute("units"),
					(String) ((request.getAttribute("floor") == null || request.getAttribute("floor") == "") ? "-5"
							: request.getAttribute("floor")),
					(String) request.getAttribute("t-floors"),
					(String) ((request.getAttribute("desc") == null || request.getAttribute("desc") == "") ? "NULL"
							: request.getAttribute("desc")),
					(String) ((request.getAttribute("tnc") == null || request.getAttribute("tnc") == "") ? "NULL"
							: request.getAttribute("tnc")),
					new AddressBean(null, (String) request.getAttribute("street"),
							(String) request.getAttribute("town"), (String) request.getAttribute("city"),
							(String) request.getAttribute("state")),
					null, null, images));
			if (bean instanceof SuccessBean) {
				sess.setAttribute("process", "success");
			} else {
				sess.setAttribute("process", "failed");
			}
			sess.setAttribute("bean", bean);
			response.sendRedirect("./");
		} else {
			sess.setAttribute("process", "failed");
			sess.setAttribute("bean",
					new ErrorBean("C-S-P-1", "Data Transmission Problem", this.getClass().toGenericString()));
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
