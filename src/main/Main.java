package main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import excelManager.ExcelManager;

/** Main class for running the application. */
public class Main {

	/** Logger */
	protected static Logger logger = LogManager.getLogger(Main.class);
	
	public static void main(String[] args) {
		
		if (args.length == 0) {
			logger.error("There are no arguments for the file path. Exiting the program.");
			
			System.exit(1);
		}
		
		String filePath = args[0];
		
		ExcelManager excelManager = new ExcelManager(filePath);
		excelManager.printPrimesFromColumn("Data");
		
		System.exit(0);
	}
}
