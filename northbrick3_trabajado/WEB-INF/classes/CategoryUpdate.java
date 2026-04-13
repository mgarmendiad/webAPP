import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class CategoryUpdate extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("nombre");
		String desc = req.getParameter("descripcion"); 
		
		String sql ="UPDATE Categories " + "SET CategoryName = ?, Description = ?"+ " WHERE CategoryID = ?";
        System.out.println("updateCategory: " + sql);
        int n = 0;
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            stmtUpdate.setString(1,name);
            stmtUpdate.setString(2,desc);
			stmtUpdate.setInt(3,id);
			
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in updateProduct: " + sql + " Exception: " + e);
        }
		
        PrintWriter toClient = res.getWriter();
        toClient.println(Utils.header("Category Updated"));
        toClient.close();
    }
}