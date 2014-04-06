package group13.fisherr;

import java.util.ArrayList;

public class TransactionSet {

	private ArrayList<Transaction> transactionSet;
	private String startDate;
	private String endDate;
	

	private ArrayList<Vendor> vendorSet;
	

	public TransactionSet(ArrayList<Transaction> transactionSet) {
		this.transactionSet = transactionSet;
		this.vendorSet = new ArrayList<Vendor>();
	}

	public TransactionSet() {
		// TODO Auto-generated constructor stub
		this.transactionSet = new ArrayList<Transaction>();
		this.vendorSet = new ArrayList<Vendor>();
	}

	public ArrayList<Transaction> getTransactionSet() {
		return transactionSet;
	}

	public void setTransactionSet(ArrayList<Transaction> transactionSet) {
		this.transactionSet = transactionSet;
		
	}
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		String str = "TransactionSet:"+ transactionSet.size()+"\n";
		
		for(int i = 0; i < transactionSet.size();i++){
			str+=transactionSet.get(i).toString();
		}
		return str;
	}
	
	public ItemSet getUniqueItems(){
		ItemSet itemSet = new ItemSet();
		//look in the current transaction set and search for unique items in each transaction
		for(int i = 0; i < this.transactionSet.size();i++){
			
			for(int j = 0; j < this.transactionSet.get(i).getItemSet().getItems().size();j++){
				//get the current item
				Item currentItem = this.transactionSet.get(i).getItemSet().getItems().get(j);
				
				//if you find an item that is unique to the list, then add it, else don't
				//override the equalsMethod
				if(!itemSet.containsItem(currentItem)){
					
					itemSet.add(currentItem);
				}
			}
			
		}
		return itemSet;
	}

	

	public void add(Transaction transaction) {
		// TODO Auto-generated method stub
		
		this.transactionSet.add(transaction);
		
	}
	
	public double  findSupportLevel(ItemSet itemSet){
		double  supportLevel = 0;
	
		for(int i = 0; i < this.transactionSet.size();i++){
			
				//if the transaction's itemset support if we find a subset 
				if(transactionSet.get(i).getItemSet().containsItemSet(itemSet)){
					
					supportLevel+=1;
				

			
			}
		}
		
		return supportLevel;
	}
	
	public ArrayList<Vendor> getVendorSet() {
		return vendorSet;
	}
	
	public void setVendorSet(ArrayList<Vendor> vendorSet) {
		this.vendorSet = vendorSet;
	}

	public void add(Vendor vendor) {
		// TODO Auto-generated method stub
		this.vendorSet.add(vendor);
	}
	
}
