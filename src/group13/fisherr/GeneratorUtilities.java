package group13.fisherr;

import javax.swing.text.html.MinimalHTMLWriter;

public class GeneratorUtilities {

	public double minSupportLevel;
	public double minConfidenceLevel;
	
	public GeneratorUtilities(double minSupportLevel, double minConfidenceLevel){
		
		this.minSupportLevel = minSupportLevel;
		this.minConfidenceLevel = minConfidenceLevel;
	}

	public double getMinSupportLevel() {
		return minSupportLevel;
	}

	public void setMinSupportLevel(double minSupportLevel) {
		this.minSupportLevel = minSupportLevel;
	}

	public double getMinConfidenceLevel() {
		return minConfidenceLevel;
	}

	public void setMinConfidenceLevel(double minConfidenceLevel) {
		this.minConfidenceLevel = minConfidenceLevel;
	}
	
	
	public RuleSet aPriori(double minSupportLevel, double minConfidenceLevel, TransactionSet ts){
		return null;
	}
	
}
