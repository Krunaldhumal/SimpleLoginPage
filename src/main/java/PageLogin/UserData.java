package PageLogin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
// import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connection.DBConnection;

@WebServlet("/UserData")
public class UserData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserData() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		try {
			Connection opcon = new DBConnection().getConnection();
			PreparedStatement prStm = opcon.prepareStatement("SELECT * FROM demo.login_tab");
			ResultSet rs = prStm.executeQuery();

			out.append("<html>\r\n" + "<head>\r\n" + "<meta charset=\"UTF-8\">\r\n" + "<title>User Data</title>\r\n"
					+ "<style>\r\n" + "body {\r\n" + "	font-family: Arial, sans-serif;\r\n"
					+ "	background-color: #f4f4f4;\r\n" + "}\r\n" + "\r\n" + ".login-container {\r\n"
					+ "	width: 100%;\r\n" + "	max-width: 400px;\r\n" + "	margin: 100px auto;\r\n"
					+ "	padding: 50px;\r\n" + "	background-color: #fff;\r\n" + "	border-radius: 5px;\r\n"
					+ "	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n" + "}\r\n" + "\r\n" + ".login-form h2 {\r\n"
					+ "	margin-bottom: 20px;\r\n" + "	text-align: center;\r\n" + "}\r\n" + "\r\n"
					+ ".input-group {\r\n" + "	margin-bottom: 20px;\r\n" + "}\r\n" + "\r\n"
					+ ".input-group label {\r\n" + "	display: block;\r\n" + "	margin-bottom: 5px;\r\n" + "}\r\n"
					+ "\r\n" + ".input-group input {\r\n" + "	width: 95%;\r\n" + "	padding: 10px;\r\n"
					+ "	font-size: 16px;\r\n" + "	border-radius: 5px;\r\n" + "	border: 1px solid #ccc;\r\n"
					+ "}\r\n" + "\r\n" + "button[type=\"submit\"] {\r\n" + "	width: 100%;\r\n"
					+ "	padding: 10px;\r\n" + "	font-size: 16px;\r\n" + "	border: none;\r\n"
					+ "	border-radius: 5px;\r\n" + "	background-color: #007bff;\r\n" + "	color: #fff;\r\n"
					+ "	cursor: pointer;\r\n" + "}\r\n" + "\r\n" + "button[type=\"submit\"]:hover {\r\n"
					+ "	background-color: blue;\r\n" + "}\r\n" + "\r\n" + "button[type=\"button\"] {\r\n"
					+ "	width: 100%;\r\n" + "	padding: 10px;\r\n" + "	font-size: 16px;\r\n" + "	border: none;\r\n"
					+ "	border-radius: 5px;\r\n" + "	background-color: blue;\r\n" + "	color: #fff;\r\n"
					+ "	cursor: pointer;\r\n" + "}\r\n" + "\r\n" + "button[type=\"button\"]:hover {\r\n"
					+ "	background-color: red;\r\n" + "}\r\n" + "</style>\r\n" + "</head>\r\n" + "<body>\r\n"
					+ "	<div class=\"login-container\">\r\n");
			out.println("<h2>User Data</h2>");

			out.println("<table border='1'>");

			out.print("<tr>");

			out.print("<th>ID</th><th>Email</th><th>Password</th><th>Action</th>");

			out.print("</tr>");

			while (rs.next()) {
				out.println("<tr><td>" + rs.getInt(1) + "</td><td>" + rs.getString(2) + "</td><td>" + rs.getString(3)
						+ "</td>" + "<td><a href=\"/SimpleLoginPage/DeleteData?id=" + rs.getInt(1) + "\">"
						+ "<button type=\"button\" style=\"width: 100%; display: inline-block;\">Delete</button>"
						+ "</a></td></tr>");
			}
			out.println("</table>");
			out.println("<br>");
			out.append("<div class=\"input-group\" style=\"display: inline-flex; width: 100%;\">\r\n"
					+ "	<a href=\"/SimpleLoginPage/index.jsp\" style=\"width: 100%; padding-left: 10px;\">\r\n"
					+ "	<button type=\"button\" style=\"width: 100%; display: inline-block;\">Back to Login</button>\r\n"
					+ "	</a>\r\n" + "</div>");

			out.append("</div>\r\n" + "</body>\r\n" + "</html>");

			new DBConnection().ConnectionClose(opcon);

		} catch (Exception e) {

		}

	}

}
