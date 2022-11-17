package CakeShop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import org.sqlite.*;
import java.util.Scanner;

public class DatabaseConnect {
//    static final Scanner scanner = new Scanner(System.in);
    public static Connection conn;
	
	public static void AddCustomer(Customer cstm) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:sqlite:./CakeShop.db");
		
		PreparedStatement stmt = conn.prepareStatement("INSERT INTO Customers(Name,Contact,Email,Address) VALUES(?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, cstm.getName());
		stmt.setLong(2, cstm.getContact());
		stmt.setString(3, cstm.getEmail());
		stmt.setString(4, cstm.getAddress());
		
		stmt.executeUpdate();
	}
	
	public static boolean LoginAdmin(Scanner scanner) throws SQLException{
		//create an empty admin object
		Admin admin = new Admin();
		
		//Initialize a connection object to connect to the sqlite database 
		Connection conn = DriverManager.getConnection("jdbc:sqlite:./CakeShop.db");
		
		//this initializes the statement object which is used to execute a sql string/query and return the values that it gives
		Statement stmt = conn.createStatement();
		
		String query = "SELECT * FROM Admin;";
		//the Resultset object stores the values returned from running a query as a iterable object
		ResultSet rs = stmt.executeQuery(query);
		
		//rs.next returns a boolean value. true if there is more information to be read from the resultset object
		//and false if there is no more information to be read
		while (rs.next()) {
			admin.setUsername(rs.getString("Username"));
			admin.setPassword(rs.getString("Password"));
		}
		
		System.out.println(admin);
		        
        System.out.print("Enter User Name: ");
        String name=scanner.nextLine();
        
        System.out.print("Enter Password: ");
        String pass = scanner.nextLine();
        
        //this block of if statements carries out validation on the values of name and pass that the user inputs
        //and it compares them with the values in the admin object and if they match, logs the user in
        if(name.equals(admin.getUsername()) && pass.equals(admin.getPassword())) {
        		System.out.println("Logging in!!");
//        		scanner.close();
        		return true;
        	}else {
        		System.out.println("Please Enter the correct username and password");
//                scanner.close();
        		return false;
        	}


		
	}
	
}