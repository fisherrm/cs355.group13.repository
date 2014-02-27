package group13.fisherr;

public class Rule {
	
	public ItemSet antecedent;
	public ItemSet consequent;
	public double actualConfidenceLevel;
	

	public Rule(ItemSet antecedent, ItemSet consequent,  double minSupportLevel, double actualConfidenceLevel){
		this.antecedent = antecedent;
		this.consequent = consequent;
		this.actualConfidenceLevel = actualConfidenceLevel;
		
		
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return antecedent.toString() + consequent.toString() + actualConfidenceLevel;
	}
	
	
}
