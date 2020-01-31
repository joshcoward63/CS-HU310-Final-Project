
public class Project {
	/*
	 * Authors JalenNall JoshCoward
	 *
	 */

	public static void main(String[] args) {
		if(args[0] == "/?") {
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
	public void createItem(int iCode,String desc, double price) {

	}
	public void createPurchase(int pCode, int quantity) {

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
