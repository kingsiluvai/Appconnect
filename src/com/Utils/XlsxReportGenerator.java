package com.Utils;

import  java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import  org.apache.poi.hssf.usermodel.HSSFSheet;
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import  org.apache.poi.hssf.usermodel.HSSFRow;
import com.Resource.InputData;

public class XlsxReportGenerator{

	public static String timeDifference(String startdate,String endDate){
		String timedifference = null;
		try{
			SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
			Date startTime = null;
			Date endTime = null;
			startTime = format.parse(startdate);
			endTime = format.parse(endDate);
				//in milliseconds
				long diff = endTime.getTime() - startTime.getTime() ;
				long diffSeconds = diff / 1000 % 60;
				long diffMinutes = diff / (60 * 1000) % 60;
				long diffHours = diff / (60 * 60 * 1000) % 24;
				//long diffDays = diff / (24 * 60 * 60 * 1000);
				
				timedifference =  diffHours +":" 
				+ diffMinutes+":" + diffSeconds ;
				System.out.println(timedifference);
		}catch(Exception exe){
			exe.printStackTrace();
		}
		return timedifference;
	}
		
	public String xlsxGenerations(HashMap<Integer,String> xlsxReportRecords){
		String filePath = null;
		try{
			filePath = InputData.XlsxReporFilePath;
			
			HSSFWorkbook workbook = new HSSFWorkbook();
	        HSSFSheet sheet = workbook.createSheet("FirstSheet");  
	        HSSFRow rowhead = sheet.createRow((short)0);
	        rowhead.createCell(0).setCellValue("Sl.No.");
	        rowhead.createCell(1).setCellValue("TestCase Name ");
	        rowhead.createCell(2).setCellValue("Start time");
	        rowhead.createCell(3).setCellValue("End time");
	        rowhead.createCell(4).setCellValue("Time Taken For Execution ");
	        rowhead.createCell(5).setCellValue("Status");
	        
	        for(int iterator : xlsxReportRecords.keySet()){
	        	System.out.println(iterator);
	            System.out.println(xlsxReportRecords.get(iterator));
	            String[] rowRecord =  xlsxReportRecords.get(iterator).split(",");
	            HSSFRow row = sheet.createRow((short)iterator+2);
		        System.out.println(rowRecord[0]);//TestCase Name
		        System.out.println(rowRecord[1]);//Start Time
		        System.out.println(rowRecord[2]);//End Time
		        System.out.println(rowRecord[3]);
		        row.createCell(0).setCellValue(iterator+1);
		        row.createCell(1).setCellValue(rowRecord[0]);//TestCase Name
		        row.createCell(2).setCellValue(rowRecord[1]);//Start Time
		        row.createCell(3).setCellValue(rowRecord[2]);//End Time
		        String timedifference = timeDifference(rowRecord[1],rowRecord[2]);
		        row.createCell(4).setCellValue(timedifference);//Time Taken
		        row.createCell(5).setCellValue(rowRecord[3]);//Status
		        System.out.println(timedifference);
	        }
		        FileOutputStream fileOut = new FileOutputStream(filePath);
		        workbook.write(fileOut);
		        fileOut.close();
		        System.out.println("Your excel file has been generated!");
	
		}catch(Exception exe){
			exe.printStackTrace();
		}
		return filePath;
	}
	}
	
