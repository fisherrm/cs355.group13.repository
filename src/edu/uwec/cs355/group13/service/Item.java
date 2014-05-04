package edu.uwec.cs355.group13.service;

import java.io.Serializable;

public class Item implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String itemName;
	
	

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
