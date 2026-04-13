import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShippersData {
    int    shipperId;
    String compName;
    String    phone;

    ShippersData (int shipperId, String compName, String phone) {
        this.shipperId    = shipperId;
        this.compName  = compName;
        this.phone   = phone;
    }
	
	ShippersData (String compName, String phone) {
        this.compName  = compName;
        this.phone   = phone;
    }
	
	
    public static Vector<ShippersData> getShippersList(Connection connection) {
        Vector<ShippersData> vec = new Vector<ShippersData>();
        String sql = "Select ShipperID, CompanyName, Phone FROM Shippers";
        System.out.println("getShippersList: " + sql);
        try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()) {
                ShippersData shipper = new ShippersData(
                    Integer.parseInt(result.getString("ShipperID")),
                    result.getString("CompanyName"),
                    result.getString("Phone")
                );
                vec.addElement(shipper);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getShippersList: " + sql + " Exception: " + e);
        }
        return vec;
    }
    
    public static ShippersData getShipper(Connection connection, String id) {
        String sql = "Select ShipperID, CompanyName, Phone FROM Shippers";
        sql += " WHERE ShipperID=?";
        System.out.println("getShipper: " + sql);
        ShippersData shipper = null;;
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, id);
            ResultSet result = pstmt.executeQuery();
            if(result.next()) {
                shipper = new ShippersData(
                    Integer.parseInt(result.getString("ShipperID")),
                    result.getString("CompanyName"),
                    result.getString("Phone")
                );
            }
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getShipper: " + sql + " Exception: " + e);
        }
        return shipper;
    }
	
	public static int insertShipper(Connection connection, ShippersData shipper) {
        String sql ="INSERT INTO Shippers  ( CompanyName,Phone ) Values (?,?)";
        System.out.println("insertShipper: " + sql);
        int n = 0;
        try {
            PreparedStatement stmtInsert= connection.prepareStatement(sql);
            stmtInsert.setString(1,shipper.compName);
            stmtInsert.setString(2,shipper.phone);
            n = stmtInsert.executeUpdate();
            stmtInsert.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in insertShipper: " + sql + " Exception: " + e);
        }
        return n;
    }
	
	public static int deleteShipper(Connection connection, String nombre) {
        String sql ="DELETE FROM Shippers WHERE CompanyName = ?";
        System.out.println("deleteShipper: " + sql);
        int n = 0;
        try {
            PreparedStatement stmtInsert= connection.prepareStatement(sql);
            stmtInsert.setString(1,nombre);
            n = stmtInsert.executeUpdate();
            stmtInsert.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in deleteShipper: " + sql + " Exception: " + e);
        }
        return n;
    }
	
}
