import java.sql.*;
class basicJDBCHacked {

    public static void main(String args[]) throws ClassNotFoundException, SQLException {
        
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        
        Connection connection = DriverManager.getConnection("jdbc:odbc:northbrick");
        
        Statement statement = connection.createStatement();
        ResultSet result=null;
		
        
       
			
			//if (args.length==1)
			//{
				//int employeeID=Integer.parseInt(args[0]);
				String name=args[0];
				System.out.println(name);
				
				// You should write the sql to check if there is a problem
				String sql="Select * from Employees WHERE FirstName='" + name + "';";
				System.out.println("sql: " + sql);
				 result = statement.executeQuery(sql);
				
				while(result.next()) {
					System.out.print(result.getString("LastName")+", ");
					System.out.println(result.getString("FirstName")+ " , ");
					System.out.println(result.getString("City"));
				}
            
              result.close();
			//}
            statement.close();
       
        
        connection.close();
    }
    
}