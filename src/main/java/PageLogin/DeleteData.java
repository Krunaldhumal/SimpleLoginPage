package PageLogin;

import java.io.IOException;
import java.io.PrintWriter;
// import java.lang.ProcessBuilder.Redirect;
import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
// import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DBConnection;

@WebServlet("/DeleteData")
public class DeleteData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteData() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
System.out.println("in delete delete");
		PrintWriter out = response.getWriter();
		Integer id = Integer.parseInt(request.getParameter("id"));

		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("id1");
		System.out.println("id" + id);
		System.out.println("userId" + userId);

		System.out.println("Session creation time : " + session.getCreationTime());
		System.out.println("Session last access time : " + session.getLastAccessedTime());
		System.out.println("Session id : " + session.getId());

		// Integer userId = Integer.parseInt(request.getParameter("user-id"));

		try {
			Connection opcon = new DBConnection().getConnection();
			PreparedStatement prStm = opcon.prepareStatement("delete from demo.login_tab where login_id=?");
			prStm.setInt(1, id);

			if (userId.equals(id)) {
				out.print("<html><body>");
				out.append("<center style = \"color:red;\"><h2> You can not delete your ID</h2></center>");
				out.print("</body></html>");
				RequestDispatcher rd = request.getRequestDispatcher("/login");
				rd.include(request, response);

			} else {
				int value = prStm.executeUpdate();
				if (value > 0) {
					out.print("<html><body>");
					out.append("<center style = \"color:red;\"><h2>" + value + "Record Deleted</h2></center>");
					out.print("</body></html>");
					RequestDispatcher rd = request.getRequestDispatcher("/login");
					rd.include(request, response);

				} else {
					out.print("<html><body>");
					out.append("<center style = \"color:red;\">No Data Deleted</center>");
					out.print("</body></html>");
					RequestDispatcher rd = request.getRequestDispatcher("/login");
					rd.include(request, response);
				}
				new DBConnection().ConnectionClose(opcon);

			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
