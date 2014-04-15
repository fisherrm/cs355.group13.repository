package service;


public class Rule {
	
	private ItemSet antecedent;
	private ItemSet consequent;
	private double actualConfidenceLevel;
	private double supportLevel;
	private int ruleSet_ID;
	
	


	

	public Rule(ItemSet antecedent, ItemSet consequent,  double minSupportLevel, double actualConfidenceLevel){
		this.antecedent = antecedent;
		this.consequent = consequent;
		this.actualConfidenceLevel = actualConfidenceLevel;
		this.ruleSet_ID = 0;
		this.supportLevel = 0;
		
	}

	public Rule() {
		this.antecedent = new ItemSet();
		this.consequent = new ItemSet();
		this.actualConfidenceLevel = 0;
		this.ruleSet_ID = 0;
		this.supportLevel = 0;
		
		// TODO Auto-generated constructor stub
	}

	public ItemSet getAntecedent() {
		return antecedent;
	}

	public void setAntecedent(ItemSet antecedent) {
		this.antecedent = antecedent;
	}

	public ItemSet getConsequent() {
		return consequent;
	}

	public void setConsequent(ItemSet consequent) {
		this.consequent = consequent;
	}

	public double getActualConfidenceLevel() {
		return actualConfidenceLevel;
	}
	
	public void setActualConfidenceLevel(double actualConfidenceLevel) {
		this.actualConfidenceLevel = actualConfidenceLevel;
	}

	public double getSupportLevel() {
		return supportLevel;
	}
	
	public void setSupportLevel(double supportLevel) {
		this.supportLevel = supportLevel;
	}
	
	
	
	
	public int getRuleSet_ID() {
		return ruleSet_ID;
	}

	public void setRuleSet_ID(int ruleSet_ID) {
		this.ruleSet_ID = ruleSet_ID;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		//antecedent.toString().replaceAll(",", " and ");
		String formatActualConf = ""+actualConfidenceLevel;
		
			while(formatActualConf.length()<6){
				formatActualConf+="0";
			}
		
		
		String str = "If " +antecedent.toString().replaceAll(",", " and ") + " THEN "+ consequent.toString().replaceAll(",", " and ") +" ("+ formatActualConf +")";
		str = str.replaceAll(" {2,}", " ");
		return str;
	}
	
	
}
