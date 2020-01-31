import java.sql.*;

public class Project {
	/*
	 * Authors JalenNall JoshCoward
	 *
	 */

	public static void main(String[] args) throws ClassNotFoundException,SQLException{
		/*
		 * things to set up database conection
		 */
		Connection connection = null;
		String database = "whatever we name it";
		int port = 0;//port number
		String userName = "whatever the name is";
		String password = "the pass";
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("url");
		connection.setAutoCommit(false);
		Statement statement = connection.createStatement();
		statement.executeUpdate("use " + database);
		
		/*
		 * 
		 *conditionals
		 */
		
		
		if(statement != null) {
			statement.close();
		}
		
		
		if(args[0] == "/?") {
			Usage();
		}
		else if(args[0].toLowerCase() == "createitem") {
			createItem(Integer.parseInt(args[1]), args[2], Double.parseDouble(args[3]));
			java.sql.Statement stmt;
			stmt =connection.createStatement();
			String sqlstmt = "insert into Items( ";
			ResultSet set = stmt.executeQuery(sqlstmt);
		}
		else if(args[0].toLowerCase() == "createpurchase") {
			createPurchase(Integer.parseInt(args[1]),Integer.parseInt(args[2]));
		}
		else if(args[0].toLowerCase() == "createshipment") {

		}
		else if(args[0].toLowerCase() == "getitems") {
			
		}
		else if(args[0].toLowerCase() == "getshipment") {

		}
		else if(args[0].toLowerCase() == "getpurchase") {
			
		}
		else if(args[0].toLowerCase() == "itemsavailable") {
			
		}
		else if(args[0].toLowerCase() == "updateitem") {
			
		}
		else if(args[0].toLowerCase() == "deleteitem") {
			
		}
		else if(args[0].toLowerCase() == "deletepurchase") {
			
		}
		else if(args[0].toLowerCase() == "deleteshipment") {
			
		}
		else {
			Usage();
		}

	}

	public static void Usage() {
		System.out.println("USAGE:\nFOR Create items\n "
				+ " CreateItem <itemCode> <itemDescription> <price>\n"
				+"\n"
				+ "FOR createPurchase\n"
				+ " CreatePurchase <itemCode> <PurchaseQuantity>\n"
				+"\n"
				+ "FOR creatShipment\n"
				+ " CreateShipment <itemCode> <ShipmentQuantity> <shipmentDate>\n"
				+"\n"
				+ "FOR getItems\n"
				+ " GetItems <itemCode>\n"
				+"\n"
				+ "FOR getShipment\n"
				+ " GetShipments <itemCode>\n"
				+"\n"
				+ "FOR getPurchase\n"
				+ " GetPurchases <itemCode>\n"
				+"\n"
				+ "FOR itemsAvailable\n"
				+ " ItemsAvailable <itemCode>\n"
				+"\n"
				+ "FOR updateItem\n"
				+ " UpdateItem <itemCode> <price>\n"
				+"\n"
				+ "FOR deleteItem\n"
				+ " DeleteItem <itemCode>\n"
				+"\n"
				+ "FOR deletePurchase\n"
				+ "DeletePurchase <itemCode>\n"
				+"\n"
				+ "FOR deleteShipment\n"
				+ " DeleteShipment <itemCode> "
				);
	}
	public static void createItem(int iCode,String desc, double price) {

	}
	public static void createPurchase(int pCode, int quantity) {

	}
	public void createShipment(int sCode,int shipQ, int shipDate) {

	}
	public Object getItems(int iCode) {
		return null;
	}
	public Object getShipments(int sCode) {
		return null;
	}
	public Object getPurchases(int pCode) {
		return null;
	}
	public Object itemsAvailable(int Code) {
		return null;
	}
	public void updateItem(int ICode, double price) {	
	}
	public void deleteItem(int iCode) {
	}
	public void deletePurchase(int pcode) {
	}
	public void deleteShipment(int sCode) {
	}

}
