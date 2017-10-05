package com.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.Resource.InputData;
import com.itextpdf.text.Image;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


public class PDFReports {
	 public ArrayList<String> PDFReport(ArrayList<String> fileNameList) throws Exception
	   {
		 String currentFolderName = null;
		 ArrayList<String> successArrayFileList = null;
		 ArrayList<String> failureArrayFileList = null;
		 ArrayList<String> finalArrayFileList = null;
 		try{
 			currentFolderName = new SimpleDateFormat("ddMMMYYY/").format(new Date());
			  String SuccessPath = InputData.PDFSuccessFilePath+currentFolderName;
			  File SuccessFileDirectory = new File(SuccessPath);
			  SuccessFileDirectory.mkdirs();
			  successArrayFileList =generateSuccesPDF(fileNameList,SuccessPath);
			  String FailurePath = InputData.PDFFailureFilePath;
			  String FailureFile = FailurePath+currentFolderName;
			  File FailureFileDirectory = new File(FailureFile);
			  FailureFileDirectory.mkdirs();
			  failureArrayFileList = generateFailurePDF( fileNameList,FailureFile);
			  finalArrayFileList = new ArrayList<String>(successArrayFileList);
			  finalArrayFileList.addAll(failureArrayFileList);
		}catch(Exception exception){
			exception.printStackTrace();
		}
		return finalArrayFileList;
		
	   }
	 
	 public  ArrayList<String> generateSuccesPDF(ArrayList<String> xlsfileList,String fileName)throws Exception
	 {
		 String filePath = null;
		 String pdfFilename = null;
		 ArrayList<String> failureFileName = null;
		 try{
			 failureFileName = new ArrayList<String>();
			 for (String xlsIterator : xlsfileList) {
				 System.out.println("*******************************************"+xlsIterator);
			 Document document = new Document();
			 pdfFilename = new SimpleDateFormat("MMM_dd_HHmmss").format(new Date());
			 pdfFilename="Success"+pdfFilename;
			 filePath = fileName+xlsIterator+"_"+pdfFilename+".pdf";
			 PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
	          document.open();
	          document.add(new Paragraph("**** appCONNECT Automation Process Completed Successfully***** \n"));
	          document.add(new Paragraph("File Path -  "+filePath));
					File listSuccssExecution = new File(InputData.SuccessSnap+ "\\" + xlsIterator);
					File[] sucessList = listSuccssExecution.listFiles();
					document.add(new Paragraph("File Name xls  -  "+listSuccssExecution));
					if (sucessList != null) {
						for (File file : sucessList) {
							if (file.exists()) {
								document.add(new Paragraph("Sheet Name - " +file.getName()+"\n"));
								System.out.println(file.getName());
						         Image img = Image.getInstance(InputData.SuccessSnap+ "\\" + xlsIterator + "\\"+ file.getName());
						        	 img.scaleToFit(500f, 500f);
						         document.add(img);
						         System.out.println("File added");
						         
							} else {
								System.out.println("There is no Success Screenshots");
							}
						}
					}
					failureFileName.add(pdfFilename);
					document.close();
			          writer.close();
			 }
			 } catch (Exception e){
				 e.printStackTrace();
			 }
		return failureFileName;
			}
		 
			 
			 public static ArrayList<String> generateFailurePDF(ArrayList<String> xlsfileList,String fileName){
				 String filePath = null;
				 String pdfFilename = null;
				 ArrayList<String> failureFileName = null;
			 try{
				 failureFileName = new ArrayList<String>();
				 for (String xlsIterator : xlsfileList) {
					 System.out.println("*******************************************"+xlsIterator);
				 Document document = new Document();
				 pdfFilename = new SimpleDateFormat("MMM_dd_HHmmss").format(new Date());
				 pdfFilename="Failure"+pdfFilename;
				 filePath = fileName+xlsIterator+"_"+pdfFilename+".pdf";
				 PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
		          document.open();
		          document.add(new Paragraph("**** appCONNECT Automation Process Completed Successfully***** \n"));
		          document.add(new Paragraph("File Path -  "+filePath));
			 
					File listSuccssExecution = new File(InputData.FailureSnap+ "\\" + xlsIterator);
					File[] sucessList = listSuccssExecution.listFiles();
					document.add(new Paragraph("File Name xls  -  "+listSuccssExecution));
					if (sucessList != null) {
						for (File file : sucessList) {
							if (file.exists()) {
								document.add(new Paragraph("Sheet Name - " +file.getName()+"\n"));
								System.out.println(file.getName());
						         Image img = Image.getInstance(InputData.FailureSnap+ "\\" + xlsIterator + "\\"+ file.getName());
						         img.scaleToFit(500f, 500f);
						         document.add(img);
						         System.out.println("File added");
						         
							} else {
								System.out.println("There is no Failure Screenshots");
							}
						}
					}
					failureFileName.add(pdfFilename);
					document.close();
			        writer.close();
			 } 
		 }catch(Exception exception){
			 
		 }
			return failureFileName;
	 }
			 
			 
			 
}