package common;

import java.io.Serializable;
import java.util.ArrayList;

import service.Rule;

public class RuleSet implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Rule> ruleSet;
	private String date;
	
	public RuleSet(ArrayList<Rule> ruleSet){
		this.ruleSet = ruleSet;
	}

	public RuleSet() {
		// TODO Auto-generated constructor stub
		this.ruleSet = new ArrayList<Rule>();
	}

	public ArrayList<Rule> getRuleSet() {
		return ruleSet;
	}

	public void setRuleSet(ArrayList<Rule> ruleSet) {
		this.ruleSet = ruleSet;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		String str = "";
		for(Rule rule : this.ruleSet){
			str +=rule +"\n";
		}
		return str;
	}
	
	
	
	
}
		
	
