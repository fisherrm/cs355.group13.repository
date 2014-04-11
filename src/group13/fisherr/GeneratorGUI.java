package group13.fisherr;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
		ruleSetText = new JTextArea(5, 30);
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
		
		genGUI.setSize(500, 400);
		genGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		genGUI.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event)
	{
		if(event.getSource()==submit)
		{
			ruleSet.setBorder(new TitledBorder("Rule Set"));
			GeneratorUtilities generator = new GeneratorUtilities();
			
			String inFile = this.getInPath();//current location of Dr.Wagners test case from online
			
			System.out.println(inFile);
			TransactionSet textFileTranSet = this.getTransactionSetFromFile(inFile);
			System.out.println("Text file to TransSet");
			
			//System.out.println(textFileTranSet);		
			double minimumSupportLevel = this.getMS();
			//System.out.println(minimumSupportLevel);
			double minimumConfidenceLevel = this.getMC();
			//System.out.println(minimumConfidenceLevel);
			String outFile = this.getOutPath();
			if(this.valid){
				Timer timer = new Timer();
				timer.startTimer();
				TransactionSet apriori = generator.doApriori(textFileTranSet, minimumSupportLevel);
				System.out.println("APRIORI: \n" + apriori);
				timer.stopTimer();
				System.out.println("elapsed time in msec.: " + timer.getTotal() );
						 
				RuleSet ruleset = generator.generateRuleSet(textFileTranSet, apriori, minimumConfidenceLevel);
						
						
				System.out.println(ruleset);
				PrintWriter writer;
				try {
					writer = new PrintWriter(outFile);
					System.out.println(outFile);
					writer.println(ruleset);
					writer.close();
					ruleSetText.setText(ruleset.toString());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					//this.errorMsg += "File not found...\n";
					//ruleSet.setBorder(new TitledBorder("Error Console"));	
					//ruleSetText.setText(this.errorMsg);
					//this.errorMsg = "";
					//this.valid = true;
						}
				}else{
					ruleSet.setBorder(new TitledBorder("Error Console"));	
					ruleSetText.setText(this.errorMsg);
					this.errorMsg = "";
					this.valid = true;
				
				}
				
			}
		
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
		return filePath;
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
				this.errorMsg += "Minimum Support muse be ranging from 0.0 to 1.0...\n";
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
				this.errorMsg += "Minimum Confidence muse be ranging from 0.0 to 1.0...\n";
				this.valid = false;
			}
		}
		return minCon;
	}
	
	public TransactionSet getTransactionSetFromFile(String fileName) {

		TransactionSet allTransactions = new TransactionSet();
		try {
			ReadFile file = new ReadFile(fileName);
			String[] transactionSetLines = file.openFile();
			
			
			
			Pattern pattern = null;
			Matcher matcher = null;
			
			for (int i = 3; i < transactionSetLines.length; i++) {
				
				//Scanner scanner = new Scanner(transactionSetLines[i]);
				//continue if the length of the line is greater than 0
				if(transactionSetLines[i].length()>0){

				//check for a only 1 left brace at beginning and look ahead to see there is no other left braces
				
				String regex =	"(?<=\\{)(.*)(?=\\})";
				pattern = Pattern.compile(regex);
				matcher = pattern.matcher(transactionSetLines[i]);	
				String group = "";
				if(matcher.find()&& !matcher.group(0).isEmpty()){
					//System.out.println(matcher.group(0));
					group = matcher.group(0);
					//get rid of extra whitespace
					group =group.replaceAll(" {1,}", " ");
					
				}else{
					this.errorMsg += "Error in transaction \""+transactionSetLines[i]+ "\" at transaction " + (i-2) + "\n " ;
					this.errorMsg += "Each transaction requires opening and closing curly braces, as well as containing at least one item.\n";
					this.valid = false;
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

				// add the finished transaction to the total TransactionSet
				allTransactions.add(nextTransaction);
			}
			}//end of > length 0

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return allTransactions;

	}

}
