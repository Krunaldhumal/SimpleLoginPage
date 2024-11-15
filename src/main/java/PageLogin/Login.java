package PageLogin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSession;

import com.connection.DBConnection;
import com.model.User;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Integer id = 1;
		// String password = request.getParameter("password");

		// Integer userId = Integer.parseInt(request.getParameter("user-id"));
		HttpSession session = request.getSession(false);

		if (session != null && Optional.ofNullable(session.getAttribute("id1")).isPresent()) {
			Integer userId = (Integer) session.getAttribute("id1");
			System.out.println("login get userid : " + userId);

			try {

				System.out.println("Session creation time : " + session.getCreationTime());
				System.out.println("Session last access time : " + session.getLastAccessedTime());
				System.out.println("Session id : " + session.getId());

				Connection con = new DBConnection().getConnection();
				PreparedStatement prStm = con.prepareStatement("select * from demo.login_tab where login_id = ?");
				prStm.setInt(1, userId);

				ResultSet rs = prStm.executeQuery();
				// User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3));
				getUser(rs, request, response, con);

				new DBConnection().ConnectionClose(con);

			} catch (ClassNotFoundException | SQLException e) {
				System.out.println(e);
			}
		} else {

			System.out.println("edit data else");
			RequestDispatcher rd = request.getRequestDispatcher("/");
			rd.include(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// PrintWriter out = response.getWriter();
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		// User user = new User(request.getParameter("email"),
		// request.getParameter("password"));

		try {
			Connection con = new DBConnection().getConnection();
			PreparedStatement prStm = con
					.prepareStatement("select * from demo.login_tab where uEmail = ? and passWord = ?");
			prStm.setString(1, email);
			prStm.setString(2, password);

			ResultSet rs = prStm.executeQuery();

			// User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3));
			getUser(rs, request, response, con);
			HttpSession session = request.getSession();
			session.setAttribute("email", email);
			session.setAttribute("id1", rs.getInt(1));
			System.out.println("loging post user id1 " + rs.getInt(1));

			System.out.println("Session creation time : " + session.getCreationTime());
			System.out.println("Session last access time : " + session.getLastAccessedTime());
			System.out.println("Session id : " + session.getId());

			new DBConnection().ConnectionClose(con);

		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
	}

	public void getUser(ResultSet rs, HttpServletRequest request, HttpServletResponse response, Connection con)
			throws IOException, SQLException, ServletException {

		PrintWriter out = response.getWriter();

		// String email = request.getParameter("email");
		if (rs.next()) {
			User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3));

			out.append("<html>\r\n" + "<head>\r\n" + "<meta charset=\"UTF-8\">\r\n" + "<title>Sign In</title>\r\n"
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
					+ "	background-color: #0056b3;\r\n" + "}\r\n" + "\r\n" + "button[type=\"button\"] {\r\n"
					+ "	width: 100%;\r\n" + "padding: 10px;\r\n" + "	font-size: 16px;\r\n" + "	border: none;\r\n"
					+ "border-radius: 5px;\r\n" + "	background-color: red;\r\n" + "	color: #fff;\r\n"
					+ "cursor: pointer;\r\n" + "}\r\n" + "\r\n" + "button[type=\"button\"]:hover {\r\n"
					+ "	background-color: red;\r\n" + "}\r\n" + "</style>\r\n" + "</head>\r\n" + "<body>\r\n"
					+ "	<div class=\"login-container\">\r\n");
			out.append("<h2>Welcome " + user.getEmail() + "</h2>");
			/*
			 * out.
			 * append("<form action=\"UserData\" method=\"Post\" class=\"login-form\">\r\n"
			 * + "<div>\r\n" + "<a href=\"/SimpleLoginPage/UserData\">\r\n" +
			 * "<button type=\"submit\">View User Data</button>\r\n" + "</a>\r\n" +
			 * "</div><br>\r\n" + "</form>\r\n" + "	");
			 */

			PreparedStatement prStm1 = con.prepareStatement("SELECT * FROM demo.login_tab");
			ResultSet rs1 = prStm1.executeQuery();

			out.println("<h2>User Data</h2>");

			out.println("<center><table border='1'>");

			out.print("<tr>");

			out.print("<th>ID</th><th>Email</th><th>Password</th><th>Delete</th><th>Edit</th>");

			out.print("</tr>");

			while (rs1.next()) {
				User user1 = new User(rs1.getInt(1), rs1.getString(2), rs1.getString(3));
				out.println("<tr><td>" + user1.getId() + "</td><td>" + user1.getEmail() + "</td><td>"
						+ user1.getPassword() + "</td>" + "<td><a href=\"/SimpleLoginPage/DeleteData?id="
						+ user1.getId() + "\">Delete</a></td>" + "<td><a href=\"/SimpleLoginPage/editdata?id="
						+ user1.getId() + "\">Edit</a></td></tr>");

				// HttpSession session = request.getSession();
				// session.setAttribute("userid", user.getId());
			}
			out.println("</table>");
			out.println("<br>");
			out.append("<a href=\"/SimpleLoginPage/logout\" style=\"width: 100%;\">\r\n"
					+ "	<button type=\"button\" style=\"width: 100%; display: inline-block;\">Back to Login In</button>\r\n"
					+ "	</a>\r\n </center>");

			out.append("</div>\r\n" + "</body>\r\n" + "</html>");
		} else {
			out.print("<html><body>");
			out.append("<center style = \"color:red;\">Please Enter valid Email or Password</center>");
			out.print("</body></html>");
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.include(request, response);
		}
	}
}
