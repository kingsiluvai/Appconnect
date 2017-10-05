package com.Utils;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;

import com.Resource.InputData;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;

public class BrowserUtils {

	static WebDriver driver = null; 
	ATUTestRecorder recorder ;
	static org.apache.log4j.Logger log = null;
	String  Browser = InputData.Browser;
	  @FindBy(id= "username") private WebElement userName;
	  @FindBy(id= "password") private WebElement password;
	  @FindBy(id= "login") private WebElement signInBtn;
	  
	public WebDriver OpenBrowser(WebDriver driver) throws Exception{
		try{
				log= Logger.getLogger("automateappConnect");
				log.info("Browser Opened Successfullly");
				log.info("Login Process initiated");
	  			driver.get(InputData.URL);
	  			driver.manage().window().maximize();
	  			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  			WebElement username =driver.findElement(By.id("userName"));
	  			username.sendKeys(InputData.Username);
	  			WebElement password =driver.findElement(By.id("password"));
	  			password.sendKeys(InputData.Password);
	  			WebElement clickbtn =driver.findElement(By.xpath("//input[@type="+"\"submit"+"\"]"));
	  			clickbtn.click();
				log.info("Login Process Completed");
	  			if(!driver.switchTo().alert().equals(null)){
	  				driver.switchTo().alert().accept();	
  			}
  			log.info("Loged in Successfully");
  			System.out.println("Driver Status:"+driver);
		
		}catch (Exception e){
			log.error("Class Utils | Method OpenBrowser | Exception desc : "+e.getMessage());
		}
		return driver;
	}
	
	public ATUTestRecorder initiateVideoRecord(){
		  try{
			  DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
			  Date date = new Date();
			  int currentDate = date.getDate();
		      int currentMonth = date.getMonth();
		      int currentYear = date.getYear();
		      String foldername = currentDate+"-"+currentMonth+"-"+currentYear;
			  File dir = new File(InputData.videoPath+foldername);
		      dir.mkdir();
			  recorder = new ATUTestRecorder(InputData.videoPath+foldername,"TestVideo-"+dateFormat.format(date),false);
			  recorder.start();
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		return recorder;
	}
	
	 public void terminateVideoRecord() throws ATUTestRecorderException {
		  try{
			  System.out.println(recorder);
		  recorder.stop();
		  log.info("Video Recording Stopped");
		  }catch(Exception e){
			  e.printStackTrace();
		  }
	  }

	
	public WebDriver getDriverDetails(){
		try{
			System.out.println("*******************");
			  System.out.println("Before Suite Execution Started");
			if(Browser.equals("Chrome")){
				String driverPath = InputData.driverPath;
				System.out.println(driverPath);
				System.setProperty("webdriver.chrome.driver", driverPath);
				driver = new ChromeDriver();
			}else if(Browser.equals("Firefox")){
			    driver = new FirefoxDriver();
			    OpenBrowser(driver);
			}
			System.out.println("Before Suite Execution Ended : " + driver);
			System.out.println("*******************");  
		  }catch(Exception e){
			  //seleTestNG.getscreenshot(driver);
			  log.info("Unable to Cinfigure Driver Details ");
		  }
		return driver;
	}

	public void CleanlastExecution() {
		try{
			File logFile = new File("E:/appCONNECT_Workspace/Log");
			File SuccessScreenShot = new File(InputData.SuccessSnap);
			File FailureScreenShot = new File(InputData.FailureSnap);
			if(logFile.exists()){
				FileUtils.cleanDirectory(logFile); 	
			}
			if(SuccessScreenShot.exists()){
				FileUtils.cleanDirectory(SuccessScreenShot);
			}
			if(FailureScreenShot.exists()){
				FileUtils.cleanDirectory(FailureScreenShot);
			}
			
		}catch(Exception e){
			
		}
		
	}
	

}
