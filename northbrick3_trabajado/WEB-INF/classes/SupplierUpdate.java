import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class SupplierUpdate extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
        int id = Integer.parseInt(req.getParameter("id"));
		String contact = req.getParameter("contact");
		String city = req.getParameter("city");
		String country = req.getParameter("country");
		
		SupplierData supplier = new SupplierData(id,contact,city,country);
		
		int n = SupplierData.updateSupplier(connection,supplier);
		
		res.sendRedirect("Countries.html");
        
        toClient.close();
    }
}