package service;

import org.restlet.resource.ServerResource;

import common.GeneratorUtilities;
import service.GeneratorUtilitiesPersistenceController;
import service.Rule;
import service.RulePersistenceController;
import common.RuleSet;
import service.RuleSetPersistenceController;
import service.Transaction;
import service.TransactionPersistenceController;
import common.TransactionSet;
import service.TransactionSetPersistenceController;
import service.Vendor;
import service.VendorPersistenceController;

public class GeneratorUtilitiesServerResource extends ServerResource implements
		GeneratorUtilitiesResource {
	// data
	private static GeneratorUtilities genUtils = new GeneratorUtilities();
	private static RuleSet ruleset = new RuleSet();

	// methods
	public GeneratorUtilitiesServerResource() {
		// TODO Auto-generated constructor stub
		//System.out.println("GeneratorUtilitiesServerResource constructor");
	}

	public RuleSet retrieve() {
		System.out.println("retrieve called");
		System.out.println(ruleset);
		System.out.println("retrieve finished");
		return ruleset;
	}

	public void store(GeneratorUtilities genUtils) {
		System.out.println("store called");
		GeneratorUtilitiesServerResource.genUtils = new GeneratorUtilities(genUtils);
		//set the parameters
		double minimumSupportLevel = GeneratorUtilitiesServerResource.genUtils.getMinimumSupportLevel();
		double minimumConfidenceLevel = GeneratorUtilitiesServerResource.genUtils.getMinimumConfidenceLevel();
		String filepath = GeneratorUtilitiesServerResource.genUtils.getFilepath();
//		System.out.println("MSL: "+ GeneratorUtilitiesServerResource.genUtils.getMinimumSupportLevel());
//		System.out.println("MCL: "+ GeneratorUtilitiesServerResource.genUtils.getMinimumConfidenceLevel());
//		System.out.println("filepath: "+ GeneratorUtilitiesServerResource.genUtils.getFilepath());
		//read from the filepath
		TransactionSet textFileTranSet = GeneratorUtilitiesServerResource.genUtils.getTransactionSetFromFile(filepath);
		//do A Priori
		Timer timer2 = new Timer();
		timer2.startTimer();
		TransactionSet apriori = GeneratorUtilitiesServerResource.genUtils.doApriori(textFileTranSet, minimumSupportLevel);
		timer2.stopTimer();
		System.out.println("A Priori: elapsed time in msec.: " + timer2.getTotal() );
		//generate the rules
		ruleset = GeneratorUtilitiesServerResource.genUtils.generateRuleSet(textFileTranSet, apriori, minimumConfidenceLevel);
		//store a generator in the database
		persistAllDAOControllers(GeneratorUtilitiesServerResource.genUtils , textFileTranSet,ruleset);
		System.out.println("store finished");
		
	
	}
	
	
public static void persistAllDAOControllers(GeneratorUtilities generator, TransactionSet transactionSet, RuleSet ruleSet){
		
		
		/*DAO MAIN*/
		
		GeneratorUtilitiesPersistenceController generatorPC = new GeneratorUtilitiesPersistenceController();
		VendorPersistenceController vendorPC = new VendorPersistenceController();		// controller for delegating vendor persistence
		RulePersistenceController rulePC = new RulePersistenceController();		// controller for delegating rule persistence
		RuleSetPersistenceController ruleSetPC = new RuleSetPersistenceController();		// controller for delegating ruleSet persistence
		TransactionPersistenceController tranPC = new TransactionPersistenceController();		// controller for delegating transaction persistence
		TransactionSetPersistenceController tranSetPC = new TransactionSetPersistenceController();		// controller for delegating transactionSet persistence

		String
		daoString = "Mock";
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
