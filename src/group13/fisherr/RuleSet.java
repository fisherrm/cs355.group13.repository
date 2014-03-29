package group13.fisherr;

import java.util.ArrayList;

public class RuleSet {
	
	private ArrayList<Rule> ruleSet;
	private String date;
	
	public RuleSet(ArrayList<Rule> ruleSet){
		this.ruleSet = ruleSet;
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
		
	
