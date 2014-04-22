package webclient;


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

import service.GeneratorUtilities;
import service.GeneratorUtilitiesPersistenceController;
import service.GeneratorUtilitiesResource;
import service.Item;
import service.ItemSet;
import service.ReadFile;
import service.Rule;
import service.RulePersistenceController;
import service.RuleSet;
import service.RuleSetPersistenceController;
import service.Timer;
import service.Transaction;
import service.TransactionPersistenceController;
import service.TransactionSet;
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
				Timer timer = new Timer();
				timer.startTimer();
				
				/*Code to try with client*/
				ClientResource clientResource = new ClientResource("http://localhost:8111/");
				GeneratorUtilitiesResource proxy = clientResource.wrap(GeneratorUtilitiesResource.class);
				GeneratorUtilities generator = new GeneratorUtilities(minimumSupportLevel, minimumConfidenceLevel, filepath);
				proxy.store(generator);
				
				//get the rule set
				RuleSet ruleset = null;
				ruleset = proxy.retrieve();
				
				
				
				

				
				timer.stopTimer();
				System.out.println("elapsed time in msec.: " + timer.getTotal() );
				if(!generator.validateRuleSet(ruleset)){
					//System.out.println("No ruleset generated");
					ruleSetText.setText("No \"Rule Set\" generated");
				}else{
					outFile = this.getOutPath();
					ruleSetText.setText(ruleset.toString());
					
					
					//System.out.println(ruleset);
					PrintWriter writer;
					try {
						
						writer = new PrintWriter(outFile);
						System.out.println(outFile);
						writer.println(ruleset);
						System.out.println("wrote: " + ruleset);
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
	
	public boolean validateMinLevel(double level) {
		// TODO Auto-generated method stub
		
		if(level >= 0.0 && level <= 1.0){
			return true;
		}else{
		//System.out.println("Invalid minimum support or confidence level");
			return false;
		}
		
	}
	
	
	public double getMS()
	{
		Double minSup = 0.0;
		if(this.msParam.getText().trim().length() == 0)
		{
			this.errorMsg += "No minimum support level indicated...\n";
			this.valid = false;
		}
		else
		{
			try{
				minSup = Double.parseDouble(this.msParam.getText());
			} catch(NumberFormatException nfe){
				
					
					this.errorMsg += "Minimum Support must be ranging from 0.0 to 1.0...\n";
					this.valid = false;
				
			}
			if(!validateMinLevel(minSup)){
				
				this.errorMsg += "Minimum Support must be ranging from 0.0 to 1.0...\n";
				this.valid = false;
			}
			
		}
		return minSup;
	}
	
	public double getMC()
	{
		Double minCon = 0.0;
		if(this.mcParam.getText().trim().length() == 0)
		{
			this.errorMsg += "No minimum confidence level indicated...\n";
			this.valid = false;
		}
		else
		{
			try{
				minCon = Double.parseDouble(this.mcParam.getText());
			} catch(NumberFormatException nfe){
				
					this.errorMsg += "Minimum Confidence must be ranging from 0.0 to 1.0...\n";
					this.valid = false;
				
			}
			if(!validateMinLevel(minCon)){
				this.errorMsg += "Minimum Confidence must be ranging from 0.0 to 1.0...\n";
				this.valid = false;
			}
		}
		return minCon;
	}
	
	
	public boolean validateTransactionSet(TransactionSet tranSet){
		if(tranSet.getTransactionSet()==null){
			System.out.println("Transaction Set is NULL");
			return false;
		}else{
			return true;
		}
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
			
			/*If we need to use a FileReader
			
			FileReader filereader = new FileReader(fileName);
			Scanner fileScanner = new Scanner(filereader);
			
			ArrayList<String> transactionLines = new ArrayList<String>();
			while(fileScanner.hasNextLine()){
				transactionLines.add(fileScanner.nextLine());
				
			}
			
			for(String insideBracket : transactionLines){
				//no leading brace
				validateBraces(insideBracket);
				//no closing brace
			}
			*/
			//System.out.println("transactionSetLines" + transactionSetLines);
			//Get the Date
			
			Pattern pattern = null;
			Matcher matcher = null;
			Vendor vendor = new Vendor(transactionSetLines[0]);
			String startDate = transactionSetLines[1];//for TransactionSet
			String endDate = transactionSetLines[2];//for TransactionSet
			String transactionDate = "2014-04-04 12:00:00";//default date
			
			for (int i = 3; i < transactionSetLines.length; i++) {
				
				//Scanner scanner = new Scanner(transactionSetLines[i]);
				//continue if the length of the line is greater than 0
				if(transactionSetLines[i].length()>0){
				
					
					//figure out how to validate a date and maintain it?
					
					String datetimeRegex = "(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2})";
					pattern = Pattern.compile(datetimeRegex);
					matcher = pattern.matcher(transactionSetLines[i]);	
					String date = "";
					if(matcher.find()&& !matcher.group(0).isEmpty()){
						date = matcher.group(0);
						transactionDate = date;
						System.out.println("FOUND A NEW DATE");
						transactionSetLines[i]  = transactionSetLines[i].replaceAll(datetimeRegex, "");
						
					}
					
				
					
					
					
					if(!validateLine(transactionSetLines[i], i)){
						
						return null;
					}
					
					/*
				
				//check for a only 1 left brace at beginning and look ahead to see there is no other left braces
				
				String bracesRegex =  "\\{{2,}|\\}{2,}|\\{\\s*\\}";//look for multiple left braces, right braces, or no contents
				pattern = Pattern.compile(bracesRegex);
				
				matcher = pattern.matcher(transactionSetLines[i]);
				if(matcher.find()&& !matcher.group(0).isEmpty()){
					this.errorMsg+="Error: in transaction \""+transactionSetLines[i]+ "\" at transaction " + (i-2)+" (line "+(i+1)+")...\n"; 
					this.errorMsg+="	Improper bracketing: no item contents, or extra/missing left and right braces...\n";
					this.valid=false;
					//System.out.println("Bad brace format");
					//return an empty transaction set
					//return new TransactionSet();
					return null;
				}
					
					System.out.println("VALID BRACKETS");
					
					
					String validCharacters ="[^\\sA-Za-z0-9,\\{\\}]+";
					
					pattern = Pattern.compile(validCharacters);
					matcher = pattern.matcher(transactionSetLines[i]);	
					if(matcher.find()){
						
						this.errorMsg+="Error: in transaction \""+transactionSetLines[i]+ "\" at transaction " + (i-2)+" (line "+(i+1)+")...\n"; 
						this.errorMsg+="	Invalid characters: Valid characters include alphanumeric, commas, whitespace and brackets\n";
						this.valid=false;
						return null;
					}
						System.out.println("VALID CHARACTERS");
					*/
					
					
					
					
					
						String regex =	"(?<=\\{)(.*)(?=\\})";
						pattern = Pattern.compile(regex);
						matcher = pattern.matcher(transactionSetLines[i]);	
						String group = "";
						if(matcher.find()&& !matcher.group(0).isEmpty()){
							System.out.println("VALID CONTENTS");
							//System.out.println(matcher.group(0));
							group = matcher.group(0);
							//get rid of extra whitespace
							group =group.replaceAll(" {1,}", " ");
							
						}else{
							//System.out.println("Error: in transaction \""+transactionSetLines[i]+ "\" at transaction " + (i-2) );
							//System.out.println("Each transaction requires opening and closing curly braces, as well as containing at least one item.");
							this.errorMsg+="Error: in transaction \""+transactionSetLines[i]+ "\" at transaction " + (i-2)+"...\n"; 
							this.errorMsg+="	No content in brackets\n";
							this.valid=false;
							return null;
						}
						//separate by commas
						String[] candidates = group.split(",");
						// make a new ItemSet to store
						ItemSet itemset = new ItemSet();
						for(int k = 0; k<candidates.length; k++)
						{
							candidates[k] = candidates[k].trim();
							
							//System.out.println("Candidate " + k + ": " + candidates[k]);
							Item nextItem = new Item(candidates[k]);
		
							itemset.add(nextItem);
						}
						
		
						// create a new transaction from the itemSet
						Transaction nextTransaction = new Transaction(itemset);
						
						
						
						
						System.out.println("SET DATE: " + transactionDate);
						nextTransaction.setDate(transactionDate);
		
						// add the finished transaction to the total TransactionSet
						allTransactions.add(nextTransaction);
					}//
				//}//end of brace regex
			}//end of > length 0

			allTransactions.add(vendor);
			allTransactions.setStartDate(startDate);
			allTransactions.setEndDate(endDate);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return allTransactions;

	}
	
	
	
	
	
	
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
			//return new TransactionSet();
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
			
			
			
			
			
		
		System.out.println("Invalid line");
		return validLine;
		
		
		
		// TODO Auto-generated method stub
		
	}

	public static void DAOController(GeneratorUtilities generator, TransactionSet transactionSet, RuleSet ruleSet){
		
		
		/*DAO MAIN*/
		
		GeneratorUtilitiesPersistenceController generatorPC = new GeneratorUtilitiesPersistenceController();
		
		
		
		VendorPersistenceController vendorPC = new VendorPersistenceController();		// controller for delegating vendor persistence
		RulePersistenceController rulePC = new RulePersistenceController();		// controller for delegating rule persistence
		RuleSetPersistenceController ruleSetPC = new RuleSetPersistenceController();		// controller for delegating ruleSet persistence
		TransactionPersistenceController tranPC = new TransactionPersistenceController();		// controller for delegating transaction persistence
		TransactionSetPersistenceController tranSetPC = new TransactionSetPersistenceController();		// controller for delegating transactionSet persistence

		String daoString = "MySQL";
		/*
	    InputStreamReader unbuffered = new InputStreamReader( System.in );
	    BufferedReader keyboard = new BufferedReader( unbuffered );
		try {
			System.out.println("Use (Mock) DAO or (MySQL) DAO? Mock");
			daoString = keyboard.readLine();
			daoString = "MySQL";
		}
		catch (IOException error) {
			System.err.println("Error reading input");
		}
		*/
		//set the daoStrings
		generatorPC.setDAO(daoString);
		vendorPC.setDAO(daoString);
		rulePC.setDAO(daoString);
		ruleSetPC.setDAO(daoString);
		tranPC.setDAO(daoString);
		tranSetPC.setDAO(daoString);
		
		
		//persist Vendor
		for(Vendor vendor: transactionSet.getVendorSet()){
			vendorPC.persistVendor(vendor);
		}
		
		
		//System.out.println(transactionSet.getVendorSet());
		
		//persist TransactionSet
		tranSetPC.persistTransactionSet(transactionSet);
		
		
		//iterate through each transaction in transactionSet and persist
		for(Transaction transaction: transactionSet.getTransactionSet()){
			//System.out.println("persisting transaction");
			tranPC.persistTransaction(transaction);
			
		}
		//persist GeneratorUtilities
		generatorPC.persistGeneratorUtilities(generator);
		//persist RuleSet
		ruleSetPC.persistRuleSet(ruleSet);
		
		
		//iterate through each rule in ruleSet and persist
		for(Rule rule: ruleSet.getRuleSet()){
			//System.out.println("persisting rule");
			rulePC.persistRule(rule);
		}
		
		
		
	}

}
