package ecm;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.Connection;

public class Admin {
	static Scanner input = new Scanner(System.in);
	static Connection con;
	static Statement st;
	private static void fuctionalityAdmin() {
		System.out.println("Admin Menu ->");
		System.out.println("Press 1 to Update item ");
		System.out.println("Press 2 to List the top selling item ");
		System.out.println("Press 3 to Add a new customer ");
		System.out.println("Press 4 to Get Users details ");
		System.out.println("Press 5 to View avaliable products ");
		System.out.println("Press 0 to Logout");
		int c = input.nextInt();  
		try {	
			switch(c) { 
			case 1:
				updateItem();
				fuctionalityAdmin();
				break;
			case 2:
				topSelling();
				fuctionalityAdmin();
				break;
			case 3:
				addCustomer();
				fuctionalityAdmin();
				break;
			case 4: 
				getUserDetail();
				fuctionalityAdmin() ;
				break;
			case 5:
				viewItems();
				fuctionalityAdmin();
				break;
			case 0:
				return;
			default:
				System.out.println("Invalid input try again ..");
				fuctionalityAdmin();
		}	}catch(SQLException e) { e.getMessage(); }	
	}
	
	private static void getUserDetail() throws SQLException {
		String query = "select * from  userdetail";
		con = Main.getAccess();
		st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		while(rs.next()) {
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
		}    
		con.close();
	}
	
	public static void loginAdmin() throws SQLException {
		boolean execute = false;
		System.out.println("Enter your username : ");
		String un = input.next().toLowerCase();
		input.nextLine();
		System.out.println("Enter your password : ");
		String pd = input.nextLine();
		String query = "select uname,password from userdetail where role = \"admin\"";
		con = Main.getAccess();
		st = con.createStatement();
		ResultSet rs =  st.executeQuery(query);
		while(rs.next()) {
			if(rs.getString(1).equals(un) && rs.getString(2).equals(pd)) {
				execute = true; break;
			}
		}
		if(execute) {
			fuctionalityAdmin();
		}else {
			System.out.println("Something wrong try again  ...");
			loginAdmin();
		}
	}
	


	private static void viewItems() throws SQLException {
		String qurey = "select * from items;"; 
		st = con.createStatement();
		ResultSet rs = st.executeQuery(qurey);
		while(rs.next()) {
			System.out.println(
		rs.getInt(1)+" "+rs.getString(2)+" "+
		rs.getString(3)+" "+rs.getInt(4)+" "+
		rs.getString(5)+" "+rs.getInt(6));
		}
	}


	private static void addCustomer() throws SQLException {
		System.out.println("Add Customer Page");
		System.out.print("Enter username : ");
		String username = input.next().toLowerCase();
		System.out.print("Enter password : ");
		String pd = input.next();
		System.out.print("Enter role (customer): ");
		String role = input.next();
		String query = "insert into userdetail (uname,password,role) values (?,?,?);";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setString(1, username);
		pst.setString(2, pd);
		pst.setString(3, role);
		int check = pst.executeUpdate();
		if(check != 1) { 
			 System.out.println("Insertion Failed !");
			 topSelling();
			 return;
		}else {
			System.out.println("Insertion Success ...");
		}
		return;
	}


	private static void topSelling() throws SQLException {
		System.out.println("Top Selling Items Page");
		return;
	}


	private static void updateItem() throws SQLException {
		System.out.println("Update Items Page");
		System.out.print("Enter product name :");
		String pname = input.next().toLowerCase();
		System.out.print("Enter category : ");
		String category = input.next().toLowerCase();
		System.out.print("Enter Quantity : ");
		int quan = input.nextInt();
		System.out.print("Enter Unit (grm/kg/ltr/ml/pcs) : ");
		String unit = input.next();
		System.out.print("Enter Price : ");
		int price = input.nextInt();
		String query = "insert into items (pname,category,quentity,unit,price) values (?,?,?,?,?);";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setString(1, pname);
		pst.setString(2, category);
		pst.setInt(3, quan);
		pst.setString(4, unit);
		pst.setInt(5, price);
		int check = pst.executeUpdate();
		if(check != 1) System.out.println("Not updated !");
		else System.out.println("Successfully updated !");
		input.close();
		con.close();
		
	}
}
