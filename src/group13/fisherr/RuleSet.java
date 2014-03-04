package group13.fisherr;

import java.util.ArrayList;

public class RuleSet {
	
	public ArrayList<Rule> ruleSet;
	
	public RuleSet(ArrayList<Rule> ruleSet){
		this.ruleSet = ruleSet;
	}

	public ArrayList<Rule> getRuleSet() {
		return ruleSet;
	}

	public void setRuleSet(ArrayList<Rule> ruleSet) {
		this.ruleSet = ruleSet;
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
		
	
