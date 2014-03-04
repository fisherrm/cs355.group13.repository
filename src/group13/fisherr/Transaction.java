package group13.fisherr;

public class Transaction {
	
	public ItemSet itemSet;
	public double totalCost = 0;
	public int numberOfItems = 0;
	public String date;
	
	public Transaction(ItemSet itemSet){
		this.itemSet = itemSet;
		this.date = date;
	}
	
	
	public ItemSet getItemSet() {
		return itemSet;
	}


	public void setItemSet(ItemSet itemSet) {
		this.itemSet = itemSet;
	}


	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public int getNumberOfItems() {
		return numberOfItems;
	}

	public void setNumberOfItems(int numberOfItems) {
		this.numberOfItems = numberOfItems;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		int currentSize = itemSet.getItems().size();
		String str = itemSet.toString();
		return str +"\n";
	}


	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Transaction trans = (Transaction) obj;
		if(this.itemSet.equals(trans.itemSet)){
			return true;
		}else{
			return false;
		}
		
	}
	
	
	

}
