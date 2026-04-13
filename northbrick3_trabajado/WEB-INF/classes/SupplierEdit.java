import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class SupplierEdit extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
        String id = req.getParameter("supplierId");
		
		SupplierData supplier = SupplierData.getSupplier(connection,id);
		
        toClient.println(Utils.header("Supplier Form"));
		toClient.println("<form action='SupplierUpdate' method='GET'>");
		
        toClient.println("<table border='1'>");
		
		toClient.println("<tr>");
        toClient.println("<td> Id </td>");
		toClient.println("<td><input name='id' value='" + supplier.supplierId + "'> </td>");
		toClient.println("</tr>");
		
		toClient.println("<tr>");
        toClient.println("<td> Name </td>");
		toClient.println("<td><input name='contact' value='" + supplier.contactName + "'> </td>");
		toClient.println("</tr>");
		
		toClient.println("<tr>");
        toClient.println("<td> City </td>");
		toClient.println("<td><input name='city' value='" + supplier.city + "'> </td>");
		toClient.println("</tr>");
		
		toClient.println("<tr>");
        toClient.println("<td> Country </td>");
		toClient.println("<td><input name='country' value='" + supplier.country + "'> </td>");
		toClient.println("</tr>");

        toClient.println("</table>");
		toClient.println("<input type='submit' value='Submit'>");
		toClient.println("</form>");
        toClient.println(Utils.footer("Suppliers Form"));
        toClient.close();
    }
}