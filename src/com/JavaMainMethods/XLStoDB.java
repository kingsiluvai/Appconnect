package com.JavaMainMethods;
import java.io.File;
import java.sql.*;
import jxl.Sheet;
import jxl.Workbook;

public class XLStoDB {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/STUDENTS";
   //  Database credentials
   static final String USER = "username";
   static final String PASS = "password";
   
   public static void main(String[] args) {
	   
   Connection conn = null;
   Statement stmt = null;
   String[][] xlsRecords = null;
   try{
	   xlsRecords = getExcelData();   
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      stmt = conn.createStatement();
      for(String[] iterator :xlsRecords){
    	  String Eventno = iterator[0];
    	  String Description = iterator[1]; 
    	  String Searchby = iterator[2]; 
    	  String Type = iterator[3];
    	  String value = iterator[4];
    	  String content= iterator[5];
    	  //Inserting to the Table 
    	  String sql = "INSERT INTO Table_name " +
         "VALUES ("+Eventno+","+Description +"," +Searchby +","+Type+","+value+","+content+")";
     stmt.executeUpdate(sql);  
     System.out.println("Inserted records into the table...");
     System.out.println(Eventno+"--"+Description +"," +Searchby +","+Type+","+value+","+content);
    	  
      }
            System.out.println("Inserted records into the table...");

   }catch(SQLException se){
      se.printStackTrace();
   }catch(Exception e){
      e.printStackTrace();
   }finally{
      try{
         if(stmt!=null)
            conn.close();
      }catch(SQLException se){
      }
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }
   }
   System.out.println("Succeded");
}
   
   public static String[][] getExcelData() {
		String[][] arrayExcelData = null;
		try {
			//Enter the file path you want to upload
			String xlsfilePath = "E:/appCONNECT_Workspace/Execution Input Files/Xpath input File - Extract creation.xls";
			System.out.println(xlsfilePath);
			Workbook workbook = Workbook.getWorkbook(new File(xlsfilePath));
			 String [] sheetNames = workbook.getSheetNames();
			for (int sheet = 0; sheet < sheetNames.length; sheet++) {
				String sheetName = sheetNames[sheet] ;
				Sheet sh = workbook.getSheet(sheetName);
				int totalNoOfCols = sh.getColumns();
				int totalNoOfRows = sh.getRows();
				arrayExcelData = new String[totalNoOfRows-1][totalNoOfCols];
				for (int row= 1 ; row < totalNoOfRows; row++) {
					for (int column=0; column < totalNoOfCols; column++) {
						arrayExcelData[row-1][column] = sh.getCell(column, row).getContents();
					}
				}
			}
			
		} catch (Exception e) {
			System.out.println("Xls Sheet is Read Failed : ");
			e.printStackTrace();
		}
		System.out.println("Xls Sheet is Read Completed");
		return arrayExcelData;
	}
}//end JDBCExample