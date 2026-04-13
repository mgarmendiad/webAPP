import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class CategoryEdit extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
		String id = req.getParameter("id");
        PrintWriter toClient = res.getWriter();

        toClient.println(Utils.header("Category Form"));
		toClient.println("<form action='CategoryUpdate' method='GET'>");
        toClient.println("<table border='1'>");
        
        CategoryData category = CategoryData.getCategory(connection,id);
        
        toClient.println("<tr>");
		toClient.println("<td> Id </td>");
        toClient.println("<td><input type='hidden' name='id' value='" + category.Id + "'>" + category.Id + " </td>");
		toClient.println("</tr>");
		toClient.println("<tr>");
		toClient.println("<td> Name </td>");
        toClient.println("<td><input name='nombre' value='" + category.name + "'> </td>");
		toClient.println("</tr>");
		toClient.println("<tr>");
		toClient.println("<td> Description </td>");
        toClient.println("<td><input name='descripcion' value='" + category.description + "'></td>");
        toClient.println("</tr>");
        toClient.println("</table>");
		toClient.println("<input type='submit' value='Enviar' >");
		
		toClient.println("</form>");
        toClient.println(Utils.footer("Categories"));
        toClient.close();
    }
}