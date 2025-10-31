package ecm;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
	static String url = "jdbc:mysql://localhost:3306/project";
	static String username = "root";
	static String pw = "root123";
	static Scanner input = new Scanner(System.in);
	public static Connection getAccess() throws SQLException {
		return DriverManager.getConnection(url,username,pw);
	}
	
	public static void main(String[] args) throws SQLException {
		System.out.println("---- Welcome to Our Shop ----");
		login();  
	}
	
	public static void login() throws SQLException {
		System.out.print("Type the login option ->  1-admin / 2-user : ");
		int choice = input.nextInt();
		if(choice == 1) { Admin.loginAdmin(); return; }
		else if(choice == 2) { Customer.loginCustomer(); return; }
		else { System.out.println("Try Again !"); login(); }
	}

}
              