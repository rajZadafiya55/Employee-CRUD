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

@WebServlet("/ShowEmployee")
public class ShowEmployee extends HttpServlet {
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


        try {
            Class.forName(DriverName);
            Connection conn = DriverManager.getConnection(ConnectionStr, DBId, DBPwd);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from employee");

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Employee Data</title>");
            out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">");
            out.println("<style>");
            out.println("table {");
            out.println("    width: 100%;");
            out.println("    border-collapse: collapse;");
            out.println("}");
            out.println("th, td {");
            out.println("    padding: 12px 15px;");
            out.println("    text-align: left;");
            out.println("}");
            out.println("th {");
            out.println("    background-color: #17a2b8; /* Bootstrap primary color */");
            out.println("    color: white;");
            out.println("}");
            out.println("tr:nth-child(odd) {");
            out.println("    background-color: #bfbfbf /* Bootstrap light color */");
            out.println("}");
            out.println("tr:hover {");
            out.println("    background-color: #e9ecef; /* Bootstrap secondary color */");
            out.println("}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");

            out.println("<div class=\"\">");
            out.println("<table class=\"table table-striped\">");
            out.println("<thead class=\"thead-dark\">");
            out.println("<tr>");
            out.println("<th>Id</th>");
            out.println("<th>Name</th>");
            out.println("<th>Phone</th>");
            out.println("<th>City</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td><h4>" + rs.getInt(1) + "</h4></td>");
                out.println("<td><h4>" + rs.getString(2) + "</h4></td>");
                out.println("<td><h4>" + rs.getString(3) + "</h4></td>");
                out.println("<td><h4>" + rs.getString(4) + "</h4></td>");
                out.println("</tr>");
            }

            out.println("</tbody>");
            out.println("</table>");
            out.println("</div>");

            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {
            out.println("error");
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
