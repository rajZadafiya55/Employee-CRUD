package p1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		ServletContext context = getServletContext();
		String DriverName = context.getInitParameter("Driver");
		String ConnectionStr = context.getInitParameter("connStr");
		String DBId = context.getInitParameter("id");
		String DBPwd = context.getInitParameter("pwd");

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		RequestDispatcher rd;
		try {
			Class.forName(DriverName);
			Connection conn = DriverManager.getConnection(ConnectionStr, DBId, DBPwd);
			PreparedStatement ps = conn.prepareStatement(
					"select * from register where email='" + email + "' and password='" + password + "'");

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				String name = rs.getString("name");
				out.print("<script>localStorage.setItem('userID', '" + name + "');</script>");
				out.print("<script>alert('Login Successfully.!')</script>");

				rs.close();
				ps.close();
				conn.close();

				out.print("<script>window.onload = function() { window.location.href = 'Home.html';}</script>");

			} else {
				out.print("<script>alert('Please Enter Valid Username and Password');</script>");
				rd = request.getRequestDispatcher("./Login.html");
				rd.include(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
