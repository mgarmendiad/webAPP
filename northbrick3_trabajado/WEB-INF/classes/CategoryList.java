import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class CategoryList extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();

        toClient.println(Utils.header("Categories"));
        toClient.println("<table border='1'>");
        
        Vector<CategoryData> categorylist = CategoryData.getCategoryList(connection);
        
        for(int i=0; i< categorylist.size(); i++){
                CategoryData category = categorylist.elementAt(i);
                toClient.println("<tr>");
                toClient.println("<td>" + category.Id + " </td>");
                toClient.println("<td>" + category.name + " </td>");
                toClient.println("<td>" + category.description + " </td>");
				toClient.println("<td> <A href='http://localhost:8082/northbrick3/ProductList?id=" + category.Id + "'> Products </a></td>");
				toClient.println("<td> <A href='http://localhost:8082/northbrick3/CategoryEdit?id=" + category.Id + "'> Edit </a></td>");
                toClient.println("</tr>");
        }

        toClient.println("</table>");
        toClient.println(Utils.footer("Categories"));
        toClient.close();
    }
}