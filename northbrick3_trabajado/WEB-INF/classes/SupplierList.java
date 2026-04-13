import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class SupplierList extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
        String country = req.getParameter("country");

        toClient.println(Utils.header("Suppliers from " + country));
		toClient.println("<h2 align='center'>Developed by Maria</h2>");
        toClient.println("<table border='1'>");
		
		toClient.println("<tr>");
        toClient.println("<td> ID </td>");
        toClient.println("<td> Contact Name </td>");
        toClient.println("<td> City </td>");
		toClient.println("<td> Edit info </td>");
        toClient.println("</tr>");
		
        Vector<SupplierData> suppliersList = SupplierData.getSuppliersList(connection,country);
        
        for(int i=0; i< suppliersList.size(); i++){
                SupplierData supplier = suppliersList.elementAt(i);
                toClient.println("<tr>");
                toClient.println("<td>" + supplier.supplierId + " </td>");
                toClient.println("<td>" + supplier.contactName + " </td>");
                toClient.println("<td>" + supplier.city + " </td>");
				toClient.println("<td><a href='SupplierEdit?supplierId=" + supplier.supplierId + "'> Edit supplier </a> </td>");
                toClient.println("</tr>");
        }

        toClient.println("</table>");
        toClient.println(Utils.footer("Suppliers"));
        toClient.close();
    }
}