import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class InsertShipper extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
		
		String companyName = req.getParameter("compName");
		String phone = req.getParameter("phone");
		
		ShippersData shipper = new ShippersData(companyName,phone);
		int n = ShippersData.insertShipper(connection,shipper);
        
		res.sendRedirect("Shippers.html");
    }
}