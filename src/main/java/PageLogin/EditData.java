package PageLogin;

import java.io.IOException;
import java.io.PrintWriter;
//import java.lang.ProcessBuilder.Redirect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DBConnection;
import com.model.User;
//import com.mysql.cj.Session;

/**
 * Servlet implementation class EditData
 */
@WebServlet("/editdata")
public class EditData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditData() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer editUserId = Integer.parseInt(request.getParameter("id"));
		// Integer userId = Integer.parseInt(request.getParameter("user-id"));
		System.out.println("EditUserId : " + editUserId);
		HttpSession session = request.getSession(false);

		System.out.println("Session creation time : " + session.getCreationTime());
		System.out.println("Session last access time : " + session.getLastAccessedTime());
		System.out.println("Session id : " + session.getId());
		System.out.println(session.getAttribute("id1"));
		// System.out.println(session);

		if (session != null && Optional.ofNullable(session.getAttribute("id1")).isPresent()) {
			Integer userId = (Integer) session.getAttribute("id1");
			System.out.println("edit data get user id " + userId);

			try {
				System.out.println("in edit get try method");
				Connection con = new DBConnection().getConnection();
				PreparedStatement ps = con.prepareStatement("select * from demo.login_tab where login_id = ?");
				ps.setInt(1, editUserId);

				/*
				 * if(userId.equals(editUserId)) { PrintWriter out = response.getWriter();
				 * 
				 * out.print("<html><body>"); out.
				 * append("<center style = \"color:red;\"><h2> You can not edit your ID</h2></center>"
				 * ); out.print("</body></html>"); RequestDispatcher rd =
				 * request.getRequestDispatcher("/login?user-id="+userId); rd.include(request,
				 * response);
				 * 
				 * }else {
				 */

				ResultSet rs = ps.executeQuery();

				if (rs.next()) {

					User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3));
					System.out.println("rs.getInt(1)" + rs.getInt(1));

					PrintWriter out = response.getWriter();
					out.append("<html>\r\n" + "<head>\r\n" + "<meta charset=\"UTF-8\">\r\n"
							+ "<title>Update page</title>\r\n" + "<style>\r\n" + "body {\r\n"
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
							+ "</style>\r\n" + "</head>\r\n" + "<body>\r\n" + "	<div class=\"login-container\">\r\n"
							+ "		<form action=\"/SimpleLoginPage/editdata\" method=\"POST\" class=\"login-form\">\r\n"
							+ "			<h2>Edit User Detail</h2>\r\n" + "			<div class=\"input-group\">\r\n"
							+ "    			<label for=\"email\">Email Id</label> "
							// + " <input type=\"hidden\" value =" + userId + " name=\"user-id\">\r\n"
							+ "					<input type=\"hidden\" value =" + user.getId()
							+ " 				name=\"id\" required>\r\n"
							+ "<input type=\"email\" id=\"email\" value =" + user.getEmail()
							+ " name=\"email\" required>\r\n" + "	</div>\r\n"
							+ "			<div class=\"input-group\">\r\n"
							+ "				<label for=\"password\">Password</label>"
							+ "				<input type=\"text\" id=\"text\" value = " + user.getPassword()
							+ " name=\"password\" required>\r\n" + "			</div>\r\n"
							+ "			<div class=\"input-group\" style=\"display: inline-flex; width: 100%;\">\r\n"
							+ "				<button type=\"submit\" style=\"width: 50%; display: inline-block;\">Edit</button>\r\n"
							+ "			</div>\r\n" + "		</form>\r\n" + "	</div>\r\n" + "</body>\r\n"
							+ "</html>");
				} else {
					System.out.println("no result set for edit get method");
				}
				// }
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

			System.out.println("edit data else get");
			RequestDispatcher rd = request.getRequestDispatcher("/");
			rd.include(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// Integer id = Integer.parseInt(request.getParameter("id"));
		// String email = request.getParameter("email");
		// String passWord = request.getParameter("password");

		// Integer userId = Integer.parseInt(request.getParameter("user-id"));

		User user = new User(Integer.parseInt(request.getParameter("id")), request.getParameter("email"),
				request.getParameter("password"));

		HttpSession session = request.getSession(false);

		System.out.println("Session creation time : " + session.getCreationTime());
		System.out.println("Session last access time : " + session.getLastAccessedTime());
		System.out.println("Session id : " + session.getId());

		if (session != null && Optional.ofNullable(session.getAttribute("id1")).isPresent()) {
			Integer userId = (Integer) session.getAttribute("id1");
			System.out.println("userid : " + userId);

			try {
				Connection con = new DBConnection().getConnection();
				PrintWriter out = response.getWriter();
				PreparedStatement ps = con
						.prepareStatement("update demo.login_tab set uEmail = ?, passWord = ? where login_id = ?");
				ps.setString(1, user.getEmail());
				ps.setString(2, user.getPassword());
				ps.setInt(3, user.getId());

				int value = ps.executeUpdate();

				if (value > 0) {
					System.out.println("edit data value :" + userId);
					out.print("<html><body>");
					out.append("<center style = \"color:green;\"><h2>" + value + "Record Updated</h2></center>");
					out.print("</body></html>");
					// response.sendRedirect("/SimpleLoginPage/login");
					RequestDispatcher rd = request.getRequestDispatcher("/login");
					rd.include(request, response);

				} else {
					out.print("<html><body>");
					out.append("<center style = \"color:red;\">No Data Updated</center>");
					out.print("</body></html>");
					// response.sendRedirect("/SimpleLoginPage/login");

					RequestDispatcher rd = request.getRequestDispatcher("/login");
					rd.include(request, response);
				}
				new DBConnection().ConnectionClose(con);

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}
		} else {
			System.out.println("edit data post else Post");
			RequestDispatcher rd = request.getRequestDispatcher("/");
			rd.include(request, response);
		}
	}
}
