package excelManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import utils.Parser;
import utils.PrimeCalculator;

/** Manages work with excel documents */
public class ExcelManager {
	
	// Creating PROTECTED variables for testing and inheritance purposes
	/** Path to the processed excel file */
	protected String filePath;
	
	/** Logger */
	protected static Logger logger = LogManager.getLogger(ExcelManager.class);

	
	/** Create new instance of Excel Manager */
	public ExcelManager(String filePath) {
		this.filePath = filePath;
	}
	
	/** Prints all primes from a column with specified title */
	public void printPrimesFromColumn(final String title) {
		
		try {	
				final FileInputStream fileInputStream = new FileInputStream(new File(this.filePath));  
				
				// get excel file
				final XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);   
				
				// get first sheet
				final XSSFSheet sheet = workbook.getSheetAt(0);
				
				// get first row of the sheet
				final Row firstRow = sheet.getRow(0);
				
				// get column index where the data are by title
				int columnIndex = this.getColumnIndexByTitle(firstRow, title);
				
				// get all strings from the column except for the first row
				final List<String> values = this.getListOfValuesFromColumn(sheet, columnIndex);
				
				// process all strings
				System.out.println("Printing out primes:");
				for (String value : values) {
					
					// check if string is a valid number
					final Integer parsedValue = Parser.tryParse(value);
					
					// if its not, continue to the next string
					if (parsedValue == null) {
						continue;
					}
					
					// if the number is prime, print it out
					if (PrimeCalculator.isPrime(parsedValue)) {
						System.out.println(parsedValue);
					}
				}
				
				workbook.close();
				
		} catch (IOException ioException) {
			logger.error("Unable to open file, check if the entered path is correct.");
		}
	}	
	
	/** Getter for filePath. */
	public String getFilePath() {
		return filePath;
	}

	/** Setter for filePath. */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/** Gets index of a column where in the first row the value of the cell is equal to title */
	protected int getColumnIndexByTitle(Row row, String title) {
		
		int columnIndex = 0;
		
		for (Cell cell : row) {
			final String stringCellValue = cell.getStringCellValue();
			
			if (title.equals(stringCellValue)) {
				columnIndex = cell.getColumnIndex();
				break;
			}
		}
		
		return columnIndex;
	}

	/** Gets all values from a specified column */
	protected List<String> getListOfValuesFromColumn(XSSFSheet sheet, int columnIndex) {
		
		final List<String> values = new ArrayList<>();
		for (Row row : sheet) {
			
			// skip first row
			if (row.getRowNum() == 0) {
				continue;
			}
			
			final Cell dataCell = row.getCell(columnIndex);
			values.add(dataCell.getStringCellValue());
		}
		
		return values;
	}
	
	
}
