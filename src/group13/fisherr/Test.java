package group13.fisherr;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Test {
	
	public static void main(String[] args) {
		
		GeneratorUtilities generator = new GeneratorUtilities();
		
		
		//1. Provide hardcoded text file paths for the file reader
		
				String fileName1 = "src/transactionSet_01.txt";
				String fileName2 = "src/transactionSet_02.txt";
				String fileName3 = "src/transactionSet_03.txt";//current location of Dr.Wagners test case from online
				// String fileName = "H://CS/CS 355/GROUP13/GROUP13.FISHERR/src/transactionSet_01.txt";
				
				//2. Read the transaction set from the file
				TransactionSet textFileTranSet = generator.getTransactionSetFromFile(fileName3);
				
				//3. specify the minimumSupportLevel, calculated or hardcoded
				double minimumSupportLevel = 0.50;
				
				//4. specify the minimumConfidenceLevel
				double minimumConfidenceLevel = 0.50;
			   
				if(generator.validateMinLevel(minimumSupportLevel) && generator.validateMinLevel(minimumConfidenceLevel)){
					Timer timer = new Timer();
					timer.startTimer();
					TransactionSet apriori = generator.doApriori(textFileTranSet, minimumSupportLevel);
					//System.out.println("APRIORI: \n" + apriori);
					timer.stopTimer();
					System.out.println("elapsed time in msec.: " + timer.getTotal() );
					//5. generate the ruleSet from the apriori 
					RuleSet ruleset = generator.generateRuleSet(textFileTranSet, apriori, minimumConfidenceLevel);
					
					//6. Write ruleset to the output file
					System.out.println(ruleset);
					PrintWriter writer;
					try {
						writer = new PrintWriter("output.txt");
						writer.println(ruleset);
						writer.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					
					if(!generator.validateMinLevel(minimumSupportLevel)){
						System.out.println("Error: Minimum Support Level must be between 0.000 and 1.000");

					}
					if(!generator.validateMinLevel(minimumConfidenceLevel)){
						System.out.println("Error: Minimum Confidence Level must be between 0.000 and 1.000");

					}
					//System.out.println("Minimum Support Level and Minimum Confidence Level must be between 0.000 and 1.000");
				}
			}
			
			
			
			
			
		
		
	
	

}
