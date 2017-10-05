package com.Resource;

public class InputData {
	
	public static final String Browser = "Chrome";
	public static final String driverPath = "E:/appCONNECT_Workspace/drivers/chromedriver.exe"; 
	public static final String LogFile = "E:/appCONNECT_Workspace/Log/AutomateLog.log" ;
	public static final String XlsxReporFilePath = "E:/appCONNECT_Workspace/Report/XlsxReport/XslxReportFile.xlsx";
	public static final String PDFSuccessFilePath = "E:/appCONNECT_Workspace/Report/SuccessPDFRecord/";
	public static final String PDFFailureFilePath = "E:/appCONNECT_Workspace/Report/FailurePDFRecord/";
	public static final String TO = "Praveen.leo@chainsys.com";
	public static final String CC = "Praveen.leo@chainsys.com  ";
			/*+ ",udhayakumar.anbumani@chainsys.com,mahalakshmi.s@chainsys.com,"
			+ "rajapandian.c@chainsys.com";*/
    //public static final String CC = "Praveen.leo@chainsys.com";
	
//**************************************Login Information************************************************************************

//Patch Login

	//public static final String URL = "http://192.168.57.81:8080/appconnect/core/LoginLaunchMapping.echn";
	//public static final String Username = "praveen_test_user";
	//public static final String Password ="Welcome#1";
	
//QA Login 
	
	public static final String URL = "https://lv2apin1.chainsys.com/appplatform/core/UserLoginLaunchMapping.echn";
	public static final String Username = "PraveenTestUser@appdataqa.com";
	//public static final String Username = "automatecreateuser";
	public static final String Password ="Welcome#1";
	
//*****************************************************************************************************************************
	
	//Exception Handling 
	public static final String SuccessSnap = "E:\\appCONNECT_Workspace\\ScreenShot\\Success";
	public static final String FailureSnap = "E:\\appCONNECT_Workspace\\ScreenShot\\Failure";
	public static final String ManualSnap = "E:\\appCONNECT_Workspace\\ScreenShot\\ManualScreenShot";
	public static final String videoPath = "E:/appCONNECT_Workspace/Video Recorded/";
	public static final int generalsleep = 5000;
	public static final int Iterator = 100;
	
	
	
//******************************************Sheet Details*************************************************************************
	
//Test Directory
	public static final String fileDirectory = "E:/appCONNECT_Workspace/Execution Input Files";
//Fusion Directory 
      //public static final String fileDirectory = "E:/appCONNECT_Workspace/Completed Sheets/Fusion Complete Automation";
//EBS Directory 
	//public static final String fileDirectory = "E:/appCONNECT_Workspace/Completed Sheets/EBS Complete Automation";
//SAP Directory 
	//public static final String fileDirectory = "E:/appCONNECT_Workspace/Completed Sheets/SAP Complete Execution";

//********************************************************************************************************************************
	
}
