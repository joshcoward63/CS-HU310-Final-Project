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
		Statement selectStmt = null;
		Connection conn = null;  
		  try {
				Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
				System.out.println();
				System.out.println("JDBC driver loaded");
				conn = makeConnection("58120", "db1","2444666668888888");    
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
				else if(args[0].toLowerCase() == "createitem") {
				runQuery(conn,createItem(Integer.parseInt(args[1]), args[2], Double.parseDouble(args[3])));
					
				}
				else if(args[0].toLowerCase() == "createpurchase") {
					runQuery(conn,createPurchase(Integer.parseInt(args[1]),Integer.parseInt(args[2])));
				}
				else if(args[0].toLowerCase() == "createshipment") {
					runQuery(conn,createShipment(Integer.parseInt(args[1]),Integer.parseInt(args[2]),Integer.parseInt(args[3])));
				}
				else if(args[0].toLowerCase() == "getitems") {
					runQuery(conn,getItems(Integer.parseInt(args[1])));
					
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
				else Usage();

				conn.close();
				System.out.println();
				System.out.println("Database connection closed");
				System.out.println();

		} catch (Exception ex) {
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
		String stmnt = " ";
		return stmnt;
		
	}
	public static String getItems(int iCode) {
		String stmnt = "Select itemCode from Items where code = \' " + iCode + "\' "; 
		return stmnt;
	}
	public String getShipments(int sCode) {
		return null;
	}
	public String getPurchases(int pCode) {
		return null;
	}
	public String itemsAvailable(int Code) {
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
