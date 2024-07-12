package p1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InsertEmployee")
public class InsertEmployee extends HttpServlet {
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

		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String city = request.getParameter("city");

		try {
			Class.forName(DriverName);
			Connection conn = DriverManager.getConnection(ConnectionStr, DBId, DBPwd);
			PreparedStatement ps = conn.prepareStatement("insert into employee values (?,?,?,?)");

			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, phone);
			ps.setString(4, city);

			int i = ps.executeUpdate();

			if (i > 0) {
				out.print("Data Inserted");
				response.sendRedirect("http://localhost:8090/employee/Home.html");
			} else {
				out.print("Data not Inserted");
				response.sendRedirect("http://localhost:8090/employee/Home.html");
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
