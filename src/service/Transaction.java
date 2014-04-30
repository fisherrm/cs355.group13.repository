package service;

import java.io.Serializable;

public class Transaction implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ItemSet itemSet;
	private String date;
	private int transactionSet_ID = 0;
	
	
	
	


	public Transaction(ItemSet itemSet){
		this.itemSet = itemSet;
		
		//this.date = date;
	}
	
	
	public Transaction() {
		// TODO Auto-generated constructor stub
		this.itemSet = new ItemSet();
	}


	public ItemSet getItemSet() {
		return itemSet;
	}


	public void setItemSet(ItemSet itemSet) {
		this.itemSet = itemSet;
	}


	

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
	public int getTransactionSet_ID() {
		return transactionSet_ID;
	}


	public void setTransactionSet_ID(int transactionSet_ID) {
		this.transactionSet_ID = transactionSet_ID;
	}
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
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
