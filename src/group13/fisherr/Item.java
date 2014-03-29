package group13.fisherr;

public class Item {
	
	private String itemName;
	private String itemDesc;
	private String itemPrice;
	

	//Constructor
	public Item(String itemName){
		this.itemName = itemName;
	}

	public String getItemName() {
		return itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.itemName;
	}

	

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Item item = (Item) obj;
		if(this.itemName.equals(item.itemName)){
			return true;
		}else{
			return false;
		}
		
	}
	
}
