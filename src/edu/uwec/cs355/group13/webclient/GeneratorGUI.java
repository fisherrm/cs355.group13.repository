package edu.uwec.cs355.group13.webclient;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.restlet.Request;
import org.restlet.data.MediaType;
import org.restlet.resource.ClientResource;

import service.GeneratorUtilitiesPersistenceController;
import service.GeneratorUtilitiesResource;
import service.Item;
import service.ItemSet;
import service.ReadFile;
import service.Rule;
import service.RulePersistenceController;
import service.RuleSetPersistenceController;
import service.Timer;
import service.Transaction;
import service.TransactionPersistenceController;
import edu.uwec.cs355.group13.common.GeneratorUtilities;
import edu.uwec.cs355.group13.common.RuleSet;
import edu.uwec.cs355.group13.common.TransactionSet;
import service.TransactionSetPersistenceController;
import service.Vendor;
import service.VendorPersistenceController;

@SuppressWarnings("serial")
public class GeneratorGUI extends JFrame implements ActionListener{
	JPanel fullPanel = null;
	JPanel paramsPanel = null;
	JPanel rsPanel = null;
	JTextField inPath = null;
	JLabel FP = null;
	JLabel MS = null;
	JLabel MC = null;
	JTextField msParam = null;
	JTextField mcParam = null;
	JButton submit = null;
	JLabel rsLabel = null;
	JTextField outPath = null;
	JPanel pathPanel = null;
	JScrollPane ruleSet = null;
	JTextArea ruleSetText = null;
	String errorMsg = "";
	boolean valid = true;
	
	

	

	public GeneratorGUI()
	{
		valid = true;
		fullPanel = new JPanel();
		pathPanel = new JPanel();
		paramsPanel = new JPanel();
		rsPanel = new JPanel();
		fullPanel.setLayout(new BoxLayout(fullPanel, BoxLayout.Y_AXIS));
		fullPanel.setBorder(new TitledBorder("RULE GENERATOR"));
		pathPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		paramsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		rsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		inPath = new JTextField(20);
		//inPath.setBorder(new TitledBorder("Input File Path"));
		msParam = new JTextField(5);
		mcParam = new JTextField(5);
		FP = new JLabel("Input File Path: ");
		MS = new JLabel("Minimum Support: ");
		MC = new JLabel("Minimum Confidence: ");
		submit = new JButton("Generate Rule Set");
		submit.addActionListener(this);
		rsLabel = new JLabel("Output File Path: ");
		outPath = new JTextField(20);
		//outPath.setBorder(new TitledBorder("Output File Path"));
		ruleSetText = new JTextArea(10, 55);
		ruleSetText.setEditable(false);
		ruleSet = new JScrollPane(ruleSetText);
		ruleSet.setBorder(new TitledBorder("Rule Set"));
		pathPanel.add(FP);
		pathPanel.add(inPath);
		paramsPanel.add(MS);
		paramsPanel.add(msParam);
		paramsPanel.add(MC);
		paramsPanel.add(mcParam);
		paramsPanel.add(submit);
		
		rsPanel.add(rsLabel);
		rsPanel.add(outPath);
		rsPanel.add(ruleSet);
		
		fullPanel.add(pathPanel);
		fullPanel.add(rsPanel);
		fullPanel.add(paramsPanel);
		
		this.add(fullPanel);
	}
	
	public static void main(String[] args)
	{
		GeneratorGUI genGUI = new GeneratorGUI();
		
		genGUI.setSize(700, 400);
		genGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		genGUI.setVisible(true);
		
		
	

				
		
		
		
		
	}
	
	public void actionPerformed(ActionEvent event) 
	{
		if(event.getSource()==submit)
		{
			ruleSet.setBorder(new TitledBorder("Rule Set"));
			String filepath = this.getInPath();//current location of Dr.Wagners test case from online
			String outFile ="";
			System.out.println(filepath);
			
			//do a file read to check if the format is correct?
			TransactionSet textFileTranSet = this.getTransactionSetFromFile(filepath);
			System.out.println("Text file to TransSet");
			
			//System.out.println(textFileTranSet);		
			double minimumSupportLevel = this.getMS();
			//System.out.println(minimumSupportLevel);
			
			double minimumConfidenceLevel = this.getMC();
			//System.out.println(minimumConfidenceLevel);
			if(this.valid){
				
				/*Code to try with client*/
				ClientResource clientResource = new ClientResource("http://localhost:8111/");
				GeneratorUtilitiesResource proxy = clientResource.wrap(GeneratorUtilitiesResource.class);
				GeneratorUtilities generator = new GeneratorUtilities(minimumSupportLevel, minimumConfidenceLevel, filepath);
				Timer timer = new Timer();
				timer.startTimer();
				proxy.store(generator);
				timer.stopTimer();
				System.out.println("Total elapsed time in msec.: " + timer.getTotal() );
				
				//get the rule set
				RuleSet ruleset = null;
				ruleset = proxy.retrieve();
				

					
					outFile = this.getOutPath();
					//System.out.println(ruleset);
					PrintWriter writer;
					try {
						
						writer = new PrintWriter(outFile);
						System.out.println(outFile);
						writer.println(ruleset);
						System.out.println(ruleset);
						if(!generator.validateRuleSet(ruleset)){
							//System.out.println("No ruleset generated");
							ruleSetText.setText("No ruleset generated");
						}else{
	
							ruleSetText.setText(ruleset.toString());
						}
						writer.close();
					
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							//this.errorMsg += "File not found...\n";
							//ruleSet.setBorder(new TitledBorder("Error Console"));	
							//ruleSetText.setText(this.errorMsg);
							//this.errorMsg = "";
							//this.valid = true;
						}
					
			}//end of this.valid
				
				
				else{
					ruleSet.setBorder(new TitledBorder("Error Console"));	
					ruleSetText.setText(this.errorMsg);
					this.errorMsg = "";
					this.valid = true;
				
				}
				
			}//end of submit
		
	}
	
	
	/*
	 * Use this method to get the name of the transaction set file and to check if the path exists
	 */
	
	public String getInPath()
	{
		String filePath = "";
		if(this.inPath.getText().trim().length() == 0)
		{
			this.errorMsg += "No input file indicated...\n";
			this.valid = false;
		}
		else
		{
			filePath = this.inPath.getText();
			File path = new File(filePath);
			if(!path.exists())
			{
				this.errorMsg = "Input file path does not exist...\n";
				this.valid = false;
			}
			
		}
		return filePath;
	}
	
	/*
	 * Use this method to retreive the name of the output text file. Use "defaultOutput.txt" if none is provided in the GUI
	 */
	
	public String getOutPath()
	{
		String filePath = "";
		if(this.outPath.getText().trim().length() == 0)
		{
			//this.errorMsg += "No output file indicated...\n";
			//this.valid = false;
			filePath = "defaultOutput.txt";
		}
		else
		{
			filePath = this.outPath.getText();
			//File path = new File(filePath);
			/*
			if(!path.exists())
			{
				filePath = "defaultOutput.txt";
			}
			*/
		}
		outPath.setText(filePath);
		return filePath;
	
	}
	
	/*
	 * Use this method to validate the Minimum Confidence Level or MinimumSupportLevel
	 */
	
	public boolean validateMinLevel(double level) {
		// TODO Auto-generated method stub
		
		if(level >= 0.0 && level <= 1.0){
			return true;
		}else{
		//System.out.println("Invalid minimum support or confidence level");
			return false;
		}
		
	}
	
	/*
	 * Use this method to get the Minimum Support Level in the GUI
	 */
	
	public double getMS()
	{
		Double minSupport = 0.0;
		if(this.msParam.getText().trim().length() == 0)
		{
			this.errorMsg += "No minimum support level indicated...\n";
			this.valid = false;
		}
		else
		{
			try{
				minSupport = Double.parseDouble(this.msParam.getText());
			} catch(NumberFormatException nfe){
				
					
					this.errorMsg += "Minimum Support must be ranging from 0.0 to 1.0...\n";
					this.valid = false;
				
			}
			if(!validateMinLevel(minSupport)){
				
				this.errorMsg += "Minimum Support must be ranging from 0.0 to 1.0...\n";
				this.valid = false;
			}
			
		}
		return minSupport;
	}
	
	/*
	 * Use this method to get the minimum Confidence level from the GUI
	 */
	
	public double getMC()
	{
		Double minConfidence = 0.0;
		if(this.mcParam.getText().trim().length() == 0)
		{
			this.errorMsg += "No minimum confidence level indicated...\n";
			this.valid = false;
		}
		else
		{
			try{
				minConfidence = Double.parseDouble(this.mcParam.getText());
			} catch(NumberFormatException nfe){
				
					this.errorMsg += "Minimum Confidence must be ranging from 0.0 to 1.0...\n";
					this.valid = false;
				
			}
			if(!validateMinLevel(minConfidence)){
				this.errorMsg += "Minimum Confidence must be ranging from 0.0 to 1.0...\n";
				this.valid = false;
			}
		}
		return minConfidence;
	}
	
	
	
	

	/* METHOD NOTES: 
	 * 
	 * Used to read a transactions set from a file
	 * This is will need to have validation methods later
	 * */

	public TransactionSet getTransactionSetFromFile(String fileName) {

		TransactionSet allTransactions = new TransactionSet();
		try {
			ReadFile file = new ReadFile(fileName);
			String[] transactionSetLines = file.openFile();
			
			
			//validate the size of transaction file
			Pattern pattern = null;
			Matcher matcher = null;
			Vendor vendor = new Vendor(transactionSetLines[0]);
			String vendorRegex = "\\w+";
			pattern = Pattern.compile(vendorRegex);
			matcher = pattern.matcher(transactionSetLines[0]);	
			//validate a vendor
			boolean validVendor = true;
			if(!matcher.find()){
				System.out.println("No vendor name in Transaction Set");
				this.valid = false;
				this.errorMsg += "Error: No vendor name is provided\n";
				validVendor = false;
			}
			
			String startDate = transactionSetLines[1];//for TransactionSet
			String endDate = transactionSetLines[2];//for TransactionSet
			String transactionDate = "2014-04-04 12:00:00";//default date
			
			String dateRegex = "\\d{4}-\\d{2}-\\d{2}";
			pattern = Pattern.compile(dateRegex);
			matcher = pattern.matcher(startDate);	
			System.out.println("START DATE: " +startDate);
			System.out.println("END DATE: " + endDate);

			//validate if both dates exist
			boolean validStartDate = true;
			boolean validEndDate = true;
			if(!matcher.find()){
			
				this.valid = false;
				this.errorMsg += "Error: START DATE in Transaction Set not found or not in correct format (YYYY-MM-DD)\n";
				validStartDate = false;
				
			
				
			
				
			}
			pattern = Pattern.compile(dateRegex);
			matcher = pattern.matcher(endDate);
				
			if(!matcher.find()){
			
				this.valid=false;
				validEndDate = false;
				this.errorMsg += "Error: END DATE in Transaction Set not found or not in correct format (YYYY-MM-DD)\n";
				
			}
			if(!validStartDate || !validEndDate || !validVendor){
				return null;
			}
				
				
			
			
			//start the transaction set processing
			int numberOfLines = 0;
			for (int i = 3; i < transactionSetLines.length; i++) {
				numberOfLines++;
				
				
				if(numberOfLines>10000){
					this.valid = false;
					this.errorMsg += "Error: Cannot process over 10000 transactions in file\n";
					return null;
				}
				//Scanner scanner = new Scanner(transactionSetLines[i]);
				//continue if the length of the line is greater than 0
				if(transactionSetLines[i].length()>0){
				
					
					//figure out how to validate a date and maintain it?
					
					String datetimeRegex = "(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2})";
					pattern = Pattern.compile(datetimeRegex);
					matcher = pattern.matcher(transactionSetLines[i]);	
					String date = "";
					if(matcher.find()&& !matcher.group(0).isEmpty()){
						//found a date in transaction
						date = matcher.group(0);
						transactionDate = date;
						transactionSetLines[i]  = transactionSetLines[i].replaceAll(datetimeRegex, "");
						
					}
					
					
					
					//System.out.println("validating line....?");
					if(!validateLine(transactionSetLines[i], i)){
						this.valid = false;
						return null;
					}
					
					
					
					
					//get the contents in teh brackets
						String regex =	"(?<=\\{)(.*)(?=\\})";
						pattern = Pattern.compile(regex);
						matcher = pattern.matcher(transactionSetLines[i]);	
						String group = "";
						if(matcher.find()&& !matcher.group(0).isEmpty()){
							//System.out.println("VALID CONTENTS");
			
							group = matcher.group(0);
							//get rid of extra whitespace
							group =group.replaceAll(" {1,}", " ");
							
						}else{
							//System.out.println("Error: in transaction \""+transactionSetLines[i]+ "\" at transaction " + (i-2) );
							//System.out.println("Each transaction requires opening and closing curly braces, as well as containing at least one item.");
							this.errorMsg+="Error: in transaction \""+transactionSetLines[i]+ "\" at transaction " + (i-2)+"...\n"; 
							this.errorMsg+="	No content in brackets\n";
							this.valid=false;
							//return null;
						}
						//separate by commas
						
						
						//
						
						
						
						String[] candidates = group.split(",");
						
						
						
						
						
						
						// make a new ItemSet to store
						ItemSet itemset = new ItemSet();
						System.out.println("Number of Items: " + candidates.length);
						if(candidates.length > 25){
							this.valid = false;
							this.errorMsg += "Error: Cannot process more than 25 items in a transaction\n";
							//return null;
						}
						for(int k = 0; k<candidates.length; k++)
						{
							candidates[k] = candidates[k].trim();
							
							
							String validCharacters ="\\w+";
							
							pattern = Pattern.compile(validCharacters);
							matcher = pattern.matcher(candidates[k]);	
							if(!matcher.find()){
								
								this.errorMsg+="Error: in transaction \""+candidates[k]+ "\" at transaction " + (i-2)+" (line "+(i+1)+")...\n"; 
								this.errorMsg+="	Invalid characters: Valid characters include alphanumeric, commas, whitespace and brackets\n";
								this.valid=false;
								
							}
							
							
							
							
							//System.out.println("Candidate " + k + ": " + candidates[k]);
							Item nextItem = new Item(candidates[k].toUpperCase());
		
							itemset.add(nextItem);
						}
						
		
						// create a new transaction from the itemSet
						Transaction nextTransaction = new Transaction(itemset);
						
						
						//System.out.println("SET DATE: " + transactionDate);
						nextTransaction.setDate(transactionDate);
		
						// add the finished transaction to the total TransactionSet
						allTransactions.add(nextTransaction);
					}//
				//}//end of brace regex
			}//end of > length 0
			System.out.println("NUMBER OF Transactions: " + numberOfLines);
			
			allTransactions.add(vendor);
			allTransactions.setStartDate(startDate);
			allTransactions.setEndDate(endDate);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		
		System.out.println("TOTAL ITEMS:" +  allTransactions.getUniqueItems().getItems().size() );
		if(allTransactions.getUniqueItems().getItems().size() >1000){
			this.valid = false;
			this.errorMsg += "Error: Cannot process over 1000 unique items in Transaction Set";
			return null;
		}
		
		return allTransactions;

	}
	
	
	/*
	 * validateLine is used to parse the transactions in the correct format 
	 */
	
	
	
	private boolean validateLine(String line, int linenumber) {
		
		boolean validLine = true;
		//check for a only 1 left brace at beginning and look ahead to see there is no other left braces
		Pattern pattern = null;
		Matcher matcher = null;
		String bracesRegex =  "\\{{2,}|\\}{2,}|\\{\\s*\\}";//look for multiple left braces, right braces, or no contents
		pattern = Pattern.compile(bracesRegex);
		
		matcher = pattern.matcher(line);
		if(matcher.find()&& !matcher.group(0).isEmpty()){
			this.errorMsg+="Error: in transaction \""+line+ "\" at transaction " + (linenumber-2)+" (line "+(linenumber+1)+")...\n"; 
			this.errorMsg+="	Improper bracketing: no item contents, or extra/missing left and right braces...\n";
			this.valid=false;
			//System.out.println("Bad brace format");
			//return an empty transaction set
			
			validLine = false;
		}
			
		
		
		
			
			
			String validCharacters ="[^\\sA-Za-z0-9,\\{\\}]+";
			
			pattern = Pattern.compile(validCharacters);
			matcher = pattern.matcher(line);	
			if(matcher.find()){
				
				this.errorMsg+="Error: in transaction \""+line+ "\" at transaction " + (linenumber-2)+" (line "+(linenumber+1)+")...\n"; 
				this.errorMsg+="	Invalid characters: Valid characters include alphanumeric, commas, whitespace and brackets\n";
				this.valid=false;
				validLine = false;
			}
			
			
			
			
			
		
		return validLine;
		
		
		

		
	}

	

}
