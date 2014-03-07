package group13.fisherr;

import java.text.DecimalFormat;

public class Rule {
	
	public ItemSet antecedent;
	public ItemSet consequent;
	public double actualConfidenceLevel;
	public double support;
	
	


	public Rule(ItemSet antecedent, ItemSet consequent,  double minSupportLevel, double actualConfidenceLevel){
		this.antecedent = antecedent;
		this.consequent = consequent;
		this.actualConfidenceLevel = actualConfidenceLevel;
		
		
	}

	public Rule() {
		this.antecedent = new ItemSet();
		this.consequent = new ItemSet();
		this.actualConfidenceLevel = 0;
		
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

	public double getSupport() {
		return support;
	}
	
	public void setSupport(double support) {
		this.support = support;
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
