import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class ShippersList extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
        String Id = req.getParameter("cod");

        toClient.println(Utils.header("Shippers"));
        toClient.println("<table border='1'>");
		toClient.println("<tr>");
        toClient.println("<td> Id </td>");
        toClient.println("<td> CompanyName </td>");
        toClient.println("<td> Phone </td>");
        toClient.println("</tr>");
		
        Vector<ShippersData> shippersList;
		ShippersData shipper;
		
        if (Id != null) {
		
            shipper = ShippersData.getShipper(connection, Id);
			toClient.println("<tr>");
            toClient.println("<td>" + shipper.shipperId + " </td>");
            toClient.println("<td>" + shipper.compName + " </td>");
            toClient.println("<td>" + shipper.phone + " </td>");
            toClient.println("</tr>");
			
        } else {
            shippersList = ShippersData.getShippersList(connection);
			for (int i=0; i<shippersList.size(); i++){
				shipper = shippersList.elementAt(i);
				toClient.println("<tr>");
				toClient.println("<td>" + shipper.shipperId + " </td>");
				toClient.println("<td>" + shipper.compName + " </td>");
				toClient.println("<td>" + shipper.phone + " </td>");
				toClient.println("</tr>");
			}
        }
        toClient.println("</table>");
        toClient.println(Utils.footer("Shippers"));
        toClient.close();
    }
}