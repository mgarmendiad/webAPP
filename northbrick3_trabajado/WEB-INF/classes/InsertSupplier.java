import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class InsertSupplier extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
        String contactName = req.getParameter("SupplierName");
		String city = req.getParameter("city");
		String country = req.getParameter("country");
		String companyName = req.getParameter("compName");
		String title = req.getParameter("contactTitle");
		
		SupplierData supplier = new SupplierData(contactName, city, country, companyName, title);
		
		int n = SupplierData.insertSupplier(connection,supplier);
		
		res.sendRedirect("Countries.html");
        
        toClient.close();
    }
}