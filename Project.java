import java.sql.*;
import java.util.InputMismatchException;

public class Project {
	/*
	 * Authors JalenNall JoshCoward
	 *
	 */

	public static void main(String[] args) throws ClassNotFoundException,SQLException{
		/*
		 * things to set up database conection
		 */
		Statement selectStmt = null;
		Connection conn = null;  
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
			System.out.println();
			System.out.println("JDBC driver loaded");
			conn = makeConnection("58120", "CS310","1234567890");    
			/*
			 * 
			 *conditionals
			 */				
			if(selectStmt != null) {
				selectStmt.close();
			}		

			if(args[0] == "/?") {
				Usage();
			}
			/*
			 * has two different calls one for if there is a desc and one if there isnt
			 * table doesnt need a desc, user would need to enter just null for arg 2 to work
			 */
			else if(args[0].toLowerCase() == "createitem") {
				if(args[1] == null || args[3] == null) {
					System.out.println("Error in argument numbers");
					Usage();
					return;
				}
				if(args[1]!= null && args[2] == null && args[3]!=null) {
					runQuery(conn,createItem(Integer.parseInt(args[1])," n/a",Double.parseDouble(args[3])));
				}
				runQuery(conn,createItem(Integer.parseInt(args[1]), args[2], Double.parseDouble(args[3])));

			}
			else if(args[0].toLowerCase() == "createpurchase") {
				if (args[1] == null || args[2] == null) {
					System.out.println("Error in argument numbers");
					Usage();
					return;

				}
				runQuery(conn,createPurchase(Integer.parseInt(args[1]),Integer.parseInt(args[2])));
			}
			else if(args[0].toLowerCase() == "createshipment") {
				if(args[1] == null || args[2] == null || args[3] == null) {
					System.out.println("Error in argument numbers");
					Usage();
					return;
				}
				runQuery(conn,createShipment(Integer.parseInt(args[1]),Integer.parseInt(args[2]),Integer.parseInt(args[3])));
			}
			else if(args[0].toLowerCase() == "getitems") {
				if(args[1] == null) {
					System.out.println("Error in argument numbers");
					Usage();
					return;
				}
				if(args[1] == "%") {
					runQuery(conn,"select * from Item;");
				}
				runQuery(conn,getItems(Integer.parseInt(args[1])));


			}
			else if(args[0].toLowerCase() == "getshipments") {
				if(args[1] == null) {
					System.out.println("Error in argument numbers");
					Usage();
					return;
				}
				if(args[1] == "%") {
					runQuery(conn,"select * from Shipping;");
				}
				runQuery(conn,getShipments(Integer.parseInt(args[1])));
			}
			else if(args[0].toLowerCase() == "getpurchase") {
				if(args[1] == null) {
					System.out.println("Error in argument numbers");
					Usage();
					return;
				}
				if(args[1] == "%") {
					runQuery(conn,"select * from Purchase;");
				}
				runQuery(conn,getShipments(Integer.parseInt(args[1])));

			}
			else if(args[0].toLowerCase() == "itemsavailable") {
				if(args[1] == null) {
					System.out.println("Error in argument numbers");
					Usage();
					return;
				}
				runQuery(conn,itemsAvailable(Integer.parseInt(args[1])));
			}
			else if(args[0].toLowerCase() == "updateitem") {
				if(args[1] == null || args[2] == null) {
					System.out.println("Error in argument numbers");
					Usage();
					return;
				}
				runQuery(conn,updateItem(Integer.parseInt(args[1]),Double.parseDouble(args[2])));
			}
			else if(args[0].toLowerCase() == "deleteitem") {
				if(args[1] == null) {
					System.out.println("Error in argument numbers");
					Usage();
					return;
				}
				runQuery(conn,deleteItem(Integer.parseInt(args[1])));

			}
			else if(args[0].toLowerCase() == "deletepurchase") {
				if(args[1] == null) {
					System.out.println("Error in argument numbers");
					Usage();
					return;
				}
				runQuery(conn,deletePurchase(Integer.parseInt(args[1])));
			}
			else if(args[0].toLowerCase() == "deleteshipment") {
				if(args[1] == null) {
					System.out.println("Error in argument numbers");
					Usage();
					return;
				}
				runQuery(conn,deletePurchase(Integer.parseInt(args[1])));
			}
			else Usage();

			conn.close();
			System.out.println();
			System.out.println("Database connection closed");
			System.out.println();

		} 
		catch (InputMismatchException e) {
			System.err.println(e);
		}
		catch (Exception ex) {
			// handle the error
			System.err.println(ex);
		}


	}
	/*
	 *Method can be called to create the connection to the database
	 *
	 */
	public static Connection makeConnection(String port, String database, String password) {
		try {
			Connection conn = null;

			System.out.println("try to get a connection");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:" + port+ "/" + database+ "?verifyServerCertificate=false&useSSL=true&serverTimezone=UTC", "msandbox", password);
			// Do something with the Connection
			System.out.println("Database " + database +" connection succeeded!");
			System.out.println();
			return conn;
		} catch (SQLException ex) {
			// handle any errors
			System.err.println("SQLException: " + ex.getMessage());
			System.err.println("SQLState: " + ex.getSQLState());
			System.err.println("VendorError: " + ex.getErrorCode());
		}
		return null;
	}

	//Used to create/run querys
	public static void runQuery(Connection conn,String statement) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(statement);  // no real code required... just a real db connection
			// Now do something with the ResultSet ....

			rs.beforeFirst();
			while (rs.next()) {
				System.out.println(rs.getInt(1) 
						+ ":" + rs.getString(2) 
						+ ":" + rs.getString(3) 
						+ ":" + rs.getString(4));
			}
		} catch (SQLException ex) {
			// handle any errors
			System.err.println("SQLException: " + ex.getMessage());
			System.err.println("SQLState: " + ex.getSQLState());
			System.err.println("VendorError: " + ex.getErrorCode());
		} finally {
			// it is a good idea to release resources in a finally{} block
			// in reverse-order of their creation if they are no-longer needed
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} // ignore
				stmt = null;
			}
		}
	}


	public static String createItem(int iCode,String desc, double price) {
		String stmnt = "Insert into Item(itemCode,itemDescription, price"
				+ " Values ( " + iCode + ", " + desc + ", " + price +" );";
		return stmnt;

	}
	public static String createPurchase(int pCode, int quantity) {
		String stmnt = "Insert into Purchases(purchaseCode,Quantity) "
				+ "Values (" + pCode + ", " + quantity + ");";
		return stmnt;
	}
	public static String createShipment(int sCode,int shipQ, int shipDate) {
		String stmnt = "Insert into Shipment ";
		return stmnt;

	}
	public static String getItems(int iCode) {
		String stmnt = "Select itemCode from Items where itemCode = \' " + iCode + "\' "; 
		return stmnt;
	}
	public static String getShipments(int sCode) {
		String stmnt = "Select itemID from Shipping where itemID = \'" + sCode + "\';";
		return stmnt;
	}
	public String getPurchases(int pCode) {
		String stmnt = "Select itemID from Purchases where itemID = \'" + pCode + "\';";
		return stmnt;
	}
	public static String itemsAvailable(int Code) {
		return null;
	}
	public static String updateItem(int ICode, double price) {	
		return null;
	}
	public static String deleteItem(int iCode) {
		String deleteItem = "DELETE FROM Item where"  + iCode + ";";
		return deleteItem;
	}
	public static String deletePurchase(int pCode) {
		String deletePurchase = "DELETE FROM Purchase where"  + pCode + ";";
		return deletePurchase;
	}
	public static String deleteShipment(int sCode) {
		String deleteShipment = "DELETE FROM Shipment where"  + sCode + ";";
		return deleteShipment;
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
}
