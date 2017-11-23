
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rex.util.DBConnector;

/**
 * Servlet implementation class Example
 */
public class Example extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Example() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		DBConnector db = new DBConnector();
		Connection conn = db.getConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet set = stmt.executeQuery("SELECT * FROM clients");
			while (set.next()) {
				out.println(set.getString("cid") + " " + set.getString("firstname") + " " + set.getString("lastname")
						+ " " + set.getString("email") + " " + set.getString("password") + " " + set.getString("gender")
						+ " " + set.getString("contact") + " " + set.getString("street_no") + " "
						+ set.getString("city") + " " + set.getString("town") + " " + set.getString("state") + "<br>");
			}
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
		doGet(request, response);
	}

}
