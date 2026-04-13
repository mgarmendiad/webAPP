import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierData {
	int    supplierId;	
    String contactName;
    String city;
	String country;
	String company;
	String title;
	
    
    SupplierData (int supplierId, String contactName, String city) {
        this.supplierId    = supplierId;
        this.contactName  = contactName;
        this.city   = city;
    }
	
	SupplierData (int supplierId, String contactName, String city, String country) {
        this.supplierId    = supplierId;
        this.contactName  = contactName;
        this.city   = city;
		this.country = country;
    }
	
	SupplierData (String contactName, String city, String country, String company, String title) {
        this.contactName  = contactName;
        this.city   = city;
		this.country = country;
		this.company = company;
		this.title = title;
    }
	
    public static Vector<SupplierData> getSuppliersList(Connection connection, String pais) {
        Vector<SupplierData> vec = new Vector<SupplierData>();
        String sql = "Select Suppliers.SupplierID as SupplierID, ContactName, City FROM Suppliers";
        sql += " WHERE Country = ?";
        System.out.println("getSuppliersList: " + sql);
        try {
			PreparedStatement statement= connection.prepareStatement(sql);
            statement.setString(1,pais);
            ResultSet result = statement.executeQuery();
            
            while(result.next()) {
                SupplierData supplier = new SupplierData(
                    Integer.parseInt(result.getString("SupplierID")),
					result.getString("ContactName"),
                    result.getString("City")
                );
                vec.addElement(supplier);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getProductList: " + sql + " Exception: " + e);
        }
        return vec;
    }
	
	public static SupplierData getSupplier(Connection connection, String id){
		String sql = "Select Suppliers.SupplierID as SupplierID, ContactName, City, Country FROM Suppliers";
        sql += " WHERE Suppliers.SupplierID = ?";
        System.out.println("getSupplier: " + sql);
		SupplierData supplier = null;
		
        try {
			PreparedStatement statement= connection.prepareStatement(sql);
            statement.setString(1,id);
            ResultSet result = statement.executeQuery();
            
            while(result.next()) {
                supplier = new SupplierData(
                    Integer.parseInt(result.getString("SupplierID")),
					result.getString("ContactName"),
                    result.getString("City"),
					result.getString("Country")
                );
			}
			
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getProductList: " + sql + " Exception: " + e);
        }
        return supplier;
	}
	
	public static int updateSupplier(Connection connection, SupplierData supplier) {
        String sql ="UPDATE Suppliers "
            + "SET ContactName = ?, City = ?, Country = ?"
            + " WHERE Suppliers.SupplierID = ?";
        System.out.println("updateSupplier: " + sql);
        int n = 0;
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            stmtUpdate.setString(1,supplier.contactName);
            stmtUpdate.setString(2,supplier.city);
            stmtUpdate.setString(3,supplier.country);
			stmtUpdate.setInt(4,supplier.supplierId);
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in updateSupplier: " + sql + " Exception: " + e);
        }
        return n;
    }
	
	public static int insertSupplier(Connection connection, SupplierData supplier) {
        String sql ="INSERT INTO Suppliers "
            + "(ContactName,City,Country,CompanyName,ContactTitle) Values (?,?,?,?,?)";
        System.out.println("insertSupplier: " + sql);
        int n = 0;
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            stmtUpdate.setString(1,supplier.contactName);
            stmtUpdate.setString(2,supplier.city);
            stmtUpdate.setString(3,supplier.country);
			stmtUpdate.setString(4,supplier.company);
			stmtUpdate.setString(5,supplier.title);
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in insertSupplier: " + sql + " Exception: " + e);
        }
        return n;
    }
}