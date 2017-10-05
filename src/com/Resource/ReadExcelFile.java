package com.Resource;

import jxl.Sheet;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class ReadExcelFile {
	org.apache.log4j.Logger log = null;
	
	public String[][] getExcelData(Sheet sh, String sheetName,WebDriver driver) {
		String[][] arrayExcelData = null;
		try {
			log= Logger.getLogger("automateappConnect");
			int totalNoOfCols = sh.getColumns();
			int totalNoOfRows = sh.getRows();
			arrayExcelData = new String[totalNoOfRows-1][totalNoOfCols];
			for (int i= 1 ; i < totalNoOfRows; i++) {
				for (int j=0; j < totalNoOfCols; j++) {
					arrayExcelData[i-1][j] = sh.getCell(j, i).getContents();
				}
			}
		} catch (Exception e) {
			System.out.println("Xls Sheet is Read Failed : " + sheetName);
			log.info("Xls Sheet is Read Failed : " + sheetName);
			e.printStackTrace();
			throw e;
		}
		System.out.println("Xls Sheet is Read Completed");
		log.info("Xls Sheet is Read Completed");
		return arrayExcelData;
	}
	
	
}
