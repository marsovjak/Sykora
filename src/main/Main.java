package main;

import java.util.Scanner;

import excelManager.ExcelManager;

/** Main class for running the application. */
public class Main {

	public static void main(String[] args) {
		
		System.out.println("Enter a valid path of the target file.");
		final Scanner inScanner = new Scanner(System.in);
		
		String filePath = inScanner.nextLine();
		
		ExcelManager excelManager = new ExcelManager(filePath);
		
		excelManager.printPrimesFromColumn("Data");

		inScanner.close();
		
		System.exit(0);
	}
}
