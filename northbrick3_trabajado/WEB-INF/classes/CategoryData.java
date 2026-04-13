import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryData {
    String    name;
    String description;
	int Id;
	
    CategoryData (String name, String description, int Id) {
        this.name    = name;
        this.description  = description;
        this.Id   = Id;
    }
	
    public static Vector<CategoryData> getCategoryList(Connection connection) {
        Vector<CategoryData> vec = new Vector<CategoryData>();
        String sql = "SELECT CategoryID,CategoryName, Description FROM Categories";
        System.out.println("getCategoryList: " + sql);
        try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()) {
                CategoryData category = new CategoryData(
                    result.getString("CategoryName"),
                    result.getString("Description"),
                    Integer.parseInt(result.getString("CategoryID"))
                );
                vec.addElement(category);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getCategoryList: " + sql + " Exception: " + e);
        }
        return vec;
    }
	
	public static CategoryData getCategory(Connection connection, String id) {
        String sql = "SELECT CategoryID,CategoryName,Description FROM Categories";
        sql += " WHERE CategoryID=?";
        System.out.println("getCategory: " + sql);
		CategoryData category = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, id);
            ResultSet result = pstmt.executeQuery();
            if(result.next()) {
                category = new CategoryData(
                    result.getString("CategoryName"),
                    result.getString("Description"),
                    Integer.parseInt(result.getString("CategoryID"))
                );
            }
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getCategory: " + sql + " Exception: " + e);
        }
        return category;
    }
	
}
