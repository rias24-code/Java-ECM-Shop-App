package ecm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Customer {
	
	static Scanner input = new Scanner(System.in);
	static Connection con;
	static Statement st;
	static String cusName;
	public static void fuctionalityCustomer() {
		System.out.println("Customer Menu ->");
		System.out.println("Press 1 to Place on order ");
		System.out.println("Press 2 to View the order history ");
		System.out.println("Press 0 to Logout");
		int c = input.nextInt();
		try {	switch(c) { 
			case 1:
				placeOrder();
				fuctionalityCustomer();
				break;
			case 2:
				orderHistory();
				fuctionalityCustomer();
				break;
			case 0:
				return;
			default:
				System.out.println("Invalid input try again ..");
				fuctionalityCustomer();
		}	}catch(SQLException e) { e.getMessage(); }	
	}
	
	private static void orderHistory() throws SQLException{
		String query = "select * from orderHistory;";
		st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		while(rs.next()) {
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+
		rs.getString(3)+" "+rs.getInt(4)+" "+rs.getString(5)+" "+rs.getInt(6));
		}
	}
	
      	private static void placeOrder() throws SQLException{
		viewItems();
		boolean exe = false;
		System.out.println("Enter product id: ");
		int id = input.nextInt();
		System.out.println("Enter product quantity: ");
		int qnty = input.nextInt();
		input.nextLine();
		System.out.println("Enter product unit (grm/kg/ltr/ml/pcs) or null: ");
		String unit = input.nextLine();
		String pname = null;
		int price = 0;
		int qnt = 0;
		String q1 = "select pname,quentity,price from items where pid = ?";
		PreparedStatement pst = con.prepareStatement(q1);
		pst.setInt(1, id);
		ResultSet rs = pst.executeQuery();
		if(rs.next()) {
			pname = rs.getString(1);
			qnt = rs.getInt(2);
			price = rs.getInt(3);	
			System.out.println("product name : "+pname+" "+"Qnty: "+qnt+" price: "+price);
		}else {
	        System.out.println("No product found with ID: "+id);
	        return;
	    }
		
		if(qnt > qnty) {
			System.out.println("Not enough quantity in stock.Try again");
			placeOrder();
			return;
			
		}
		
		String q3 = "insert into orderHistory (cus_name,pname,quantity,unit,price) values (?,?,?,?,?);";
		pst = con.prepareStatement(q3);
		pst.setString(1, cusName);
		pst.setString(2,pname);
		pst.setInt(3,qnty);
		pst.setString(4,unit.equalsIgnoreCase("null")?null :" ");
		pst.setInt(5,(price*qnty));
		int check = pst.executeUpdate();
		if(check != 1) { System.out.println("Not updated !"); } 
		else { System.out.println("Successfully updated !");  exe = true;}
		if(exe) {
			String q = "update items set quentity = ? where pid = ?";
			pst = con.prepareStatement(q);
			pst.setInt(1,(qnt-qnty));
			pst.setInt(2, id);
			int updateCheck = pst.executeUpdate();
	        if (updateCheck == 1) {
	            System.out.println("Inventory updated.");
	        } else {
	            System.out.println("Failed to update inventory.");
	        }
		}
		input.close();
		con.close();	
		}
		
	public static void viewItems() throws SQLException {
		String qurey = "select * from items"; 
		st = con.createStatement();
		ResultSet rs = st.executeQuery(qurey);
		while(rs.next()) {
			System.out.println(
		rs.getInt(1)+" "+rs.getString(2)+" "+
		rs.getString(3)+" "+rs.getInt(4)+" "+
		rs.getString(5)+" "+rs.getInt(6));
		}
	}
	
	public static void loginCustomer() throws SQLException {
		boolean execute = false;
		System.out.println("Enter your username : ");
		String un = input.next().toLowerCase();
		cusName = un;
		input.nextLine();
		System.out.println("Enter your password : ");
		String pd = input.nextLine();
		String query = "select uname,password from userdetail where role = \"customer\"";
		con = Main.getAccess();
		st = con.createStatement();
		ResultSet rs =  st.executeQuery(query);
		while(rs.next()) {
			if(rs.getString(1).equals(un) && rs.getString(2).equals(pd)) {
				execute = true; break;
			}
		}
		if(execute) {
			fuctionalityCustomer();
		}else {
			System.out.println("Something wrong try again  ...");
			loginCustomer();
		}
	}

}

