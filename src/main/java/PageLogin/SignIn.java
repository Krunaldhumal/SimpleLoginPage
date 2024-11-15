package PageLogin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connection.DBConnection;

@WebServlet("/SignIn")
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SignIn() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		try {
			Connection opcon = new DBConnection().getConnection();
			PreparedStatement prStm = opcon
					.prepareStatement("select * from demo.login_tab where uEmail = ? and passWord = ?");
			prStm.setString(1, email);
			prStm.setString(2, password);
			ResultSet rs = prStm.executeQuery();

			if (rs.next()) {
				out.append("<html>\r\n" + "<head>\r\n" + "<meta charset=\"UTF-8\">\r\n" + "<title>Sign In</title>\r\n"
						+ "<style>\r\n" + "body {\r\n" + "	font-family: Arial, sans-serif;\r\n"
						+ "	background-color: #f4f4f4;\r\n" + "}\r\n" + "\r\n" + ".login-container {\r\n"
						+ "	width: 100%;\r\n" + "	max-width: 400px;\r\n" + "	margin: 100px auto;\r\n"
						+ "	padding: 50px;\r\n" + "	background-color: #fff;\r\n" + "	border-radius: 5px;\r\n"
						+ "	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n" + "}\r\n" + "\r\n" + ".login-form h2 {\r\n"
						+ "	margin-bottom: 20px;\r\n" + "	text-align: center;\r\n" + "}\r\n" + "\r\n"
						+ ".input-group {\r\n" + "	margin-bottom: 20px;\r\n" + "}\r\n" + "\r\n"
						+ ".input-group label {\r\n" + "	display: block;\r\n" + "	margin-bottom: 5px;\r\n"
						+ "}\r\n" + "\r\n" + ".input-group input {\r\n" + "	width: 95%;\r\n" + "	padding: 10px;\r\n"
						+ "	font-size: 16px;\r\n" + "	border-radius: 5px;\r\n" + "	border: 1px solid #ccc;\r\n"
						+ "}\r\n" + "\r\n" + "button[type=\"submit\"] {\r\n" + "	width: 100%;\r\n"
						+ "	padding: 10px;\r\n" + "	font-size: 16px;\r\n" + "	border: none;\r\n"
						+ "	border-radius: 5px;\r\n" + "	background-color: #007bff;\r\n" + "	color: #fff;\r\n"
						+ "	cursor: pointer;\r\n" + "}\r\n" + "\r\n" + "button[type=\"submit\"]:hover {\r\n"
						+ "	background-color: #0056b3;\r\n" + "}\r\n" + "\r\n" + "button[type=\"button\"] {\r\n"
						+ "	width: 100%;\r\n" + "	padding: 10px;\r\n" + "	font-size: 16px;\r\n"
						+ "	border: none;\r\n" + "	border-radius: 5px;\r\n" + "	background-color: red;\r\n"
						+ "	color: #fff;\r\n" + "	cursor: pointer;\r\n" + "}\r\n" + "\r\n"
						+ "button[type=\"button\"]:hover {\r\n" + "	background-color: red;\r\n" + "}\r\n"
						+ "</style>\r\n" + "</head>\r\n" + "<body>\r\n" + "	<div class=\"login-container\">\r\n");
				out.append("<h2>You already signin.</h2>");
				out.append("<div class=\"input-group\" style=\"display: inline-flex; width: 100%;\">\r\n"
						+ "				<a href=\"/SimpleLoginPage/index.jsp\" style=\"width: 100%; padding-left: 10px;\">\r\n"
						+ "					<button type=\"button\" style=\"width: 100%; display: inline-block;\">Back to Login In</button>\r\n"
						+ "				</a>\r\n" + "			</div>");

				out.append("</div>\r\n" + "</body>\r\n" + "</html>");
			} else if (!rs.next()) {
				PreparedStatement prStm1 = opcon
						.prepareStatement("insert into demo.login_tab (uEmail, passWord) values (?, ?)");
				prStm1.setString(1, email);
				prStm1.setString(2, password);
				int affectedrows = prStm1.executeUpdate();
				if (affectedrows > 0) {
					out.append("<html>\r\n" + "<head>\r\n" + "<meta charset=\"UTF-8\">\r\n"
							+ "<title>Sign In</title>\r\n" + "<style>\r\n" + "body {\r\n"
							+ "	font-family: Arial, sans-serif;\r\n" + "	background-color: #f4f4f4;\r\n" + "}\r\n"
							+ "\r\n" + ".login-container {\r\n" + "	width: 100%;\r\n" + "	max-width: 400px;\r\n"
							+ "	margin: 100px auto;\r\n" + "	padding: 50px;\r\n" + "	background-color: #fff;\r\n"
							+ "	border-radius: 5px;\r\n" + "	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n" + "}\r\n"
							+ "\r\n" + ".login-form h2 {\r\n" + "	margin-bottom: 20px;\r\n"
							+ "	text-align: center;\r\n" + "}\r\n" + "\r\n" + ".input-group {\r\n"
							+ "	margin-bottom: 20px;\r\n" + "}\r\n" + "\r\n" + ".input-group label {\r\n"
							+ "	display: block;\r\n" + "	margin-bottom: 5px;\r\n" + "}\r\n" + "\r\n"
							+ ".input-group input {\r\n" + "	width: 95%;\r\n" + "	padding: 10px;\r\n"
							+ "	font-size: 16px;\r\n" + "	border-radius: 5px;\r\n" + "	border: 1px solid #ccc;\r\n"
							+ "}\r\n" + "\r\n" + "button[type=\"submit\"] {\r\n" + "	width: 100%;\r\n"
							+ "	padding: 10px;\r\n" + "	font-size: 16px;\r\n" + "	border: none;\r\n"
							+ "	border-radius: 5px;\r\n" + "	background-color: #007bff;\r\n" + "	color: #fff;\r\n"
							+ "	cursor: pointer;\r\n" + "}\r\n" + "\r\n" + "button[type=\"submit\"]:hover {\r\n"
							+ "	background-color: #0056b3;\r\n" + "}\r\n" + "\r\n" + "button[type=\"button\"] {\r\n"
							+ "	width: 100%;\r\n" + "	padding: 10px;\r\n" + "	font-size: 16px;\r\n"
							+ "	border: none;\r\n" + "	border-radius: 5px;\r\n" + "	background-color: red;\r\n"
							+ "	color: #fff;\r\n" + "	cursor: pointer;\r\n" + "}\r\n" + "\r\n"
							+ "button[type=\"button\"]:hover {\r\n" + "	background-color: red;\r\n" + "}\r\n"
							+ "</style>\r\n" + "</head>\r\n" + "<body>\r\n" + "	<div class=\"login-container\">\r\n");
					out.append("<h2>You are successfully signin</h2>");
					out.append("<h2>Your email is : " + email + "</h2>");
					out.append("<div class=\"input-group\" style=\"display: inline-flex; width: 100%;\">\r\n"
							+ "<a href=\"/SimpleLoginPage/index.jsp\" style=\"width: 100%; padding-left: 10px;\">\r\n"
							+ "<button type=\"button\" style=\"width: 100%; display: inline-block;\">Back to Login</button>\r\n"
							+ "</a>\r\n" + "</div>");

					out.append("</div>\r\n" + "</body>\r\n" + "</html>");
					// RequestDispatcher rd = request.getRequestDispatcher("/Login");
					// rd.forward(request, response);

				}

			} else {
				out.append("<html>\r\n" + "<head>\r\n" + "<meta charset=\"UTF-8\">\r\n" + "<title>Sign In</title>\r\n"
						+ "<style>\r\n" + "body {\r\n" + "	font-family: Arial, sans-serif;\r\n"
						+ "	background-color: #f4f4f4;\r\n" + "}\r\n" + "\r\n" + ".login-container {\r\n"
						+ "	width: 100%;\r\n" + "	max-width: 400px;\r\n" + "	margin: 100px auto;\r\n"
						+ "	padding: 50px;\r\n" + "	background-color: #fff;\r\n" + "	border-radius: 5px;\r\n"
						+ "	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n" + "}\r\n" + "\r\n" + ".login-form h2 {\r\n"
						+ "	margin-bottom: 20px;\r\n" + "	text-align: center;\r\n" + "}\r\n" + "\r\n"
						+ ".input-group {\r\n" + "	margin-bottom: 20px;\r\n" + "}\r\n" + "\r\n"
						+ ".input-group label {\r\n" + "	display: block;\r\n" + "	margin-bottom: 5px;\r\n"
						+ "}\r\n" + "\r\n" + ".input-group input {\r\n" + "	width: 95%;\r\n" + "	padding: 10px;\r\n"
						+ "	font-size: 16px;\r\n" + "	border-radius: 5px;\r\n" + "	border: 1px solid #ccc;\r\n"
						+ "}\r\n" + "\r\n" + "button[type=\"submit\"] {\r\n" + "	width: 100%;\r\n"
						+ "	padding: 10px;\r\n" + "	font-size: 16px;\r\n" + "	border: none;\r\n"
						+ "	border-radius: 5px;\r\n" + "	background-color: #007bff;\r\n" + "	color: #fff;\r\n"
						+ "	cursor: pointer;\r\n" + "}\r\n" + "\r\n" + "button[type=\"submit\"]:hover {\r\n"
						+ "	background-color: #0056b3;\r\n" + "}\r\n" + "\r\n" + "button[type=\"button\"] {\r\n"
						+ "	width: 100%;\r\n" + "	padding: 10px;\r\n" + "	font-size: 16px;\r\n"
						+ "	border: none;\r\n" + "	border-radius: 5px;\r\n" + "	background-color: red;\r\n"
						+ "	color: #fff;\r\n" + "	cursor: pointer;\r\n" + "}\r\n" + "\r\n"
						+ "button[type=\"button\"]:hover {\r\n" + "	background-color: red;\r\n" + "}\r\n"
						+ "</style>\r\n" + "</head>\r\n" + "<body>\r\n" + "	<div class=\"login-container\">\r\n");
				out.append("<h2>You are alredy signin.</h2>");
				out.append("</div>\r\n" + "</body>\r\n" + "</html>");

			}
			new DBConnection().ConnectionClose(opcon);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
