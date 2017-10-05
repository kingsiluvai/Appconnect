package com.Executor;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.Base.SeleniumBase;
import com.Resource.InputData;
import com.Resource.ReadExcelFile;
import com.Utils.BrowserUtils;
import com.Utils.EmailUtils;
import com.Utils.PDFReports;
import com.Utils.XlsxReportGenerator;
import com.listener.ScreenShots;

import atu.testrecorder.exceptions.ATUTestRecorderException;

public class appConnectExecutor {
	public static WebDriver driver = null;
	BrowserUtils utils = null;
	static org.apache.log4j.Logger log = null;
	SeleniumBase executor = null;
	String status = null;
	EmailUtils email = null;
	ArrayList<String> fileNameList = null;
	HashMap<Integer, String> xlsxReportRecords = null;

	@BeforeSuite
	public void beforeSuite() {
		try {
			utils = new BrowserUtils();
			utils.CleanlastExecution();
			log= Logger.getLogger("automateappConnect");
			driver = utils.getDriverDetails();
			System.out.println("*********"
					+ "*****************************************************");
			utils.OpenBrowser(driver);
			System.out.println("Browser Initiate Successfully ");
			System.out.println("**************************************************************");
			log.info("Browser Opened Successfully");
		} catch (Exception exe) {
			exe.printStackTrace();
			log.info("Unable to Login : " + exe);
		}
	}

	@BeforeTest
	public void beforeTest() {
		try {
			utils.initiateVideoRecord();
			System.out.println("**************************************************************");
			System.out.println("Video Recording Started ........");
			log.info("Video Recording Started ........");
		} catch (Exception exe) {
			exe.printStackTrace();
			log.info("Unable initiate Video Record " + exe);
		}
	}

	@Test
	public void  appAutomateExecutorTest() throws Exception {

		ReadExcelFile readExcel = null;
		executor = new SeleniumBase();
		 
		try {
			readExcel = new ReadExcelFile();
			fileNameList = new ArrayList<>();
			log = Logger.getLogger("automateappConnect");
			File folder = new File(InputData.fileDirectory);
			File[] listOfFiles = folder.listFiles();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date time = new Date();
			System.out.println(dateFormat.format(time)); //2016/11/16 12:08:43
				for (File file : listOfFiles) {
					if (file.isFile()) {
						System.out.println("File name is : " + file.getName());
						String xlsfilePath = file.getPath();
						System.out.println(xlsfilePath);
						fileNameList.add(file.getName());
						Workbook workbook = Workbook.getWorkbook(new File(xlsfilePath));
						 String [] sheetNames = workbook.getSheetNames();
						 xlsxReportRecords = new HashMap<Integer,String>();
						for (int i = 0; i < sheetNames.length; i++) {
							String sheetName = sheetNames[i] ;
							Sheet sh = workbook.getSheet(sheetName);
							String[][] data = readExcel.getExcelData(sh,sheetName, driver);
							String STime = time.getHours()+":"+time.getMinutes()+":"+time.getSeconds();
							String status = ExecuteTestCases(data, driver, sheetName, file.getName(),i);
							Date Endtime = new Date();
							String ETime = Endtime.getHours()+":"+Endtime.getMinutes()+":"+Endtime.getSeconds();
							String xlsxReportData = sheetName+","+STime+","+ETime+","+status;
							xlsxReportRecords.put(i,xlsxReportData);
							System.out.println(xlsxReportRecords);
						}
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Executor Fails : " + e);
		}
	}

	
	public String ExecuteTestCases(String[][] data, WebDriver driver,
			String sheetName, String fileName,int TestCaseNo) throws Exception {
		try {
			ScreenShots snap = null;
			snap = new ScreenShots();
			log.info("\n");
			log.info("*********************" + sheetName+ "********************************");
			System.out.println("*********************" + sheetName+ "********************************");
			log.info("Test Case Execution Started..............");
			System.out.println("Test Case Execution Started ..........");
			status = executor.ExecuteTestNG(data, driver);
			if (status.equals("Success")) {
				snap.OnTestSuccess(fileName,sheetName,TestCaseNo,driver);
			} else if (status.equals("Failure")) {
				snap.OnTestFailure(fileName,sheetName,TestCaseNo,driver);
			}
			log.info("Test Case Execution Ended Successfully");
			System.out.println("Test Case Execution Ended Successfully");
			System.out.println("**********************************************************************");
		} catch (Exception exe) {
			log.info("Test Case Execution Failed ");
			System.out.println("**Test Case Execution Failed**");
			exe.printStackTrace();
			System.out.println("*****************************************************");
		}
		return status;

	}

	@AfterTest
	public void afterTest() throws ATUTestRecorderException {
		PDFReports report = null;
		XlsxReportGenerator xlsxReport = null;
		ArrayList<String> PDFFileName = null;
		String xlsxFilePath = null; 
		try {
			utils.terminateVideoRecord();
			email = new EmailUtils();
			report = new PDFReports(); 
			xlsxReport = new XlsxReportGenerator();
			System.out.println("Video Ended Successfully");
			log.info("Video Recording Stopped");
			PDFFileName =report.PDFReport(fileNameList);
			xlsxFilePath = xlsxReport.xlsxGenerations(xlsxReportRecords);
			email.SendMail(fileNameList,PDFFileName,xlsxFilePath);
			System.out.println("**************************************************************\n");
		} catch (Exception exe) {
			log.info("Video Recording Termination Failed due to : " + exe);
			System.out.println("Video Recording Termination Failed due to : " + exe);
			exe.printStackTrace();
		}
	}

	@AfterSuite
	public void afterSuite() {
		driver.close();
		log.info("Browser is Closed");
	}

}
