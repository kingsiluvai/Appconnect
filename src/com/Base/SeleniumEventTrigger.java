package com.Base;

import java.rmi.server.UID;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.Resource.InputData;

public class SeleniumEventTrigger {
	Actions action = null;
	Logger log = null;
	static int genLength = 4;
	public static String currentData = ""; 
	public static String TestCaseName = "";
	public static int IteratorValue = InputData.Iterator;
	int WaitIterator = 1;
	String status = "Failure";
	
	
	@Test String DragAndDropBy(String value, String content, String searchBy,
			WebDriver driver) throws Exception {
	        WebElement From = null;
			  List<String> axis = null;
			  int XCoordinate = 0;
			  int YCoordinate = 0;
			  log= Logger.getLogger("automateappConnect");
			  List<String> contentList = Arrays.asList(value.split(","));
			  String dragElement = contentList.get(0);
			  System.out.println(dragElement);
			  String dropAxis= contentList.get(1);
			  System.out.println(dropAxis);
			  axis = Arrays.asList(dropAxis.split(":"));
			  Actions action = new Actions(driver);
			  try{
				  switch (searchBy) {
				  case "Id" : 
					    From = driver.findElement(By.id(dragElement));
					    XCoordinate = Integer.valueOf(axis.get(0));
					    YCoordinate= Integer.valueOf(axis.get(1));
					    action.dragAndDropBy(From, XCoordinate, YCoordinate).perform();
					    new Actions(driver).clickAndHold(From).moveByOffset(100,100).release().perform();
					    break;
				  case "Xpath" : 
					    From = driver.findElement(By.xpath(dragElement));
					    System.out.println();
					    XCoordinate = Integer.valueOf(axis.get(0));
					    YCoordinate= Integer.valueOf(axis.get(1));
					    Action DragAndDrop = action.dragAndDropBy(From, XCoordinate, YCoordinate).build();
					    DragAndDrop.perform();
					    break;
				  }
				  status = "Success";
			  }catch(Exception exception){
				  throw exception;
				}
			return status;
	}


		public String clearAndType(String value, String content, String searchBy,
			WebDriver driver) throws Exception {
			WebElement element =null;
			try{
				switch(searchBy){
				case "Id":
					 element =driver.findElement(By.id(value));
					 element.clear();
					 element.sendKeys(content);
					break;
				case "Xpath" : 
				    element =driver.findElement(By.xpath(value));
				    element.clear();
					element.sendKeys(content);
					break;
				}
				}catch(Exception exceptionxe){
					throw exceptionxe;
				}
			return status;
	}



		@Test(enabled=true)
		  public String type(String value,String content ,String searchBy,WebDriver driver) throws Exception{
				WebElement element =null;
				searchBy.trim();
				log= Logger.getLogger("automateappConnect");
				try{
					switch(searchBy){
					case "Id":
						 element =driver.findElement(By.id(value));
						element.sendKeys(content);
						break;
					case "Xpath" : 
					    element =driver.findElement(By.xpath(value));
						element.sendKeys(content);
						break;
					case "ClassName" :
						element= driver.findElement(By.className(value));
						element.sendKeys(content);
						break;
					case "Name" :
						element= driver.findElement(By.name(value));
						element.sendKeys(content);
						break;
					}
					status = "Success";
				}catch(Exception exception){
					throw exception;
				}
				return status;
			}
			
		
		@Test(enabled=true)
		 public String typeEnter(String value,String content ,String searchBy,WebDriver driver) throws Exception{
				WebElement element =null;
				searchBy.trim();
				log= Logger.getLogger("automateappConnect");
				try{
					switch(searchBy){
					case "Id":
							Thread.sleep(500);
							element =driver.findElement(By.id(value));
							element.sendKeys(content);
							element.sendKeys(Keys.ENTER);
					break;
					case "Xpath" : 
							Thread.sleep(500);
							   element =driver.findElement(By.xpath(value));
							element.sendKeys(content);
							element.sendKeys(Keys.ENTER);
					break;
					case "ClassName" :
							Thread.sleep(500);
							element= driver.findElement(By.className(value));
							element.sendKeys(content);
							element.sendKeys(Keys.ENTER);
					break;
					case "Name" :
							Thread.sleep(500);
							element= driver.findElement(By.name(value));
							element.sendKeys(content);
							element.sendKeys(Keys.ENTER);
					break;
				}
					status = "Success";
				}catch(Exception exception){
					throw exception;
				}

		return status;
		}
		
		  @Test(enabled=true)
		  public String buttonClick(String value,String content ,String searchBy,WebDriver driver) throws Exception {
				WebElement element = null;
				searchBy.trim();
				try{
					log= Logger.getLogger("automateappConnect");
					switch(searchBy){ 
					case "Id" :
							element =driver.findElement(By.id(value));
							Thread.sleep(2000);
							element.click();
							break;
					case "Xpath" :
							element =driver.findElement(By.xpath(value));
							Thread.sleep(2000);
							element.click();
							break;
					case  "ClassName" :
							element= driver.findElement(By.className(value));
							Thread.sleep(2000);
							element.click();
							break;
					case  "Name" :
							element= driver.findElement(By.name(value));
							Thread.sleep(2000);
							element.click();
							break;
					}
					status = "Success";
				}catch(Exception exception){
					throw exception;
				}
				return status;
			}
		  @Test(enabled=true)
		  public String doubleClick(String value,String content ,String searchBy,WebDriver driver) throws Exception{
				searchBy.trim();
				value.trim();
				Actions action = new Actions(driver);
				log= Logger.getLogger("automateappConnect");
				try{
					switch(searchBy){
					case "Xpath":
						action.moveToElement(driver.findElement(By.xpath(value))).doubleClick().build().perform();
						break;
					case "Id":
						action.moveToElement(driver.findElement(By.id(value))).doubleClick().build().perform();
						break;
					case "ClassName":
						action = new Actions(driver);
						action.moveToElement(driver.findElement(By.className(value))).doubleClick().build().perform();
						break;
					case "Name":
					    action = new Actions(driver);
						action.moveToElement(driver.findElement(By.name(value))).doubleClick().build().perform();
						break;
					}
					status = "Success";
				}catch(Exception exception){
					throw exception;
				}
				return status;
			}
		  
		  @Test
		  public String WaitUntilVisible(String value,String content ,String searchBy,WebDriver driver) throws Exception 
		  {
			  WebDriverWait wait = null;
			  log= Logger.getLogger("automateappConnect");
			  try{
				  switch (searchBy) {
				  case "Xpath" : 
					   wait = new WebDriverWait(driver, 15);
					   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(value)));
					   driver.findElement(By.xpath(value)).click();
					   break;
				  case "Id" : 
					   wait = new WebDriverWait(driver, 15);
					   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(value)));
					   driver.findElement(By.xpath(value)).click();
				  }  
				  status = "Success";
			  }catch(Exception exception){
				  WaitIterator++;
				  if(WaitIterator<=3){
					  System.out.println("Wait until visible extended for more than 15 sec ");
					  log.info("Wait until visible extended for more than 15 sec ");
					  WaitUntilVisible(value,content ,searchBy,driver);
				  }else{
					  throw exception;
				  }
				}
			return status;
		  }
		  
		  @Test
		  public String SelectBox (String value,String content ,String searchBy,WebDriver driver) throws Exception 
		  {
			  log= Logger.getLogger("automateappConnect");
			  Select dropdown = null;
			  try{
				  switch (searchBy) {
				  case "Id" : 
					   dropdown = new Select(driver.findElement(By.id(value)));
					  dropdown.selectByVisibleText(content);
					  break;
				  case "Xpath" : 
					   dropdown = new Select(driver.findElement(By.xpath(value)));
					  dropdown.selectByVisibleText(content);
					  break; 
				  }  
				  status = "Success";
			  }catch(Exception exception){
				  throw exception;
				}
			return status;
			  
			  
		  }
		  
		  @Test
		  public String CheckBox (String value,String content ,String searchBy,WebDriver driver) throws Exception 
		  {
			  log= Logger.getLogger("automateappConnect");
			  try{
				  switch (searchBy) {
				  case "Id" : 
					  if ( !driver.findElement(By.id(value)).isSelected() )
					  {
					       driver.findElement(By.id(value)).click();
					  }
					  break;
				  case "Xpath" : 
					  if ( !driver.findElement(By.xpath(value)).isSelected() )
					  {
					       driver.findElement(By.xpath(value)).click();
					  }
					  break;
				  }  
				  status = "Success";
			  }catch(Exception exception){
				  throw exception;
				}
			return status;
			  
			  
		  }
		  
		  @Test
		  public String DragAndDrop (String value,String content ,String searchBy,WebDriver driver) throws Exception 
		  {
			  WebElement From = null;
			  WebElement To = null;
			  log= Logger.getLogger("automateappConnect");
			  List<String> contentList = Arrays.asList(value.split(","));
			  String dragElement = contentList.get(0);
			  String dropElement = contentList.get(1);
			  Actions action = new Actions(driver);
			  try{
				  switch (searchBy) {
				  case "Id" : 
					   From = driver.findElement(By.id(dragElement));
					    To = driver.findElement(By.id(dropElement));
					    action.dragAndDrop(From, To).build().perform();
					    break;
				  case "Xpath" : 
					    From = driver.findElement(By.xpath(dragElement));
					    To = driver.findElement(By.xpath(dropElement));
					    action.dragAndDrop(From, To).build().perform();
					    break;
				  }
				  status = "Success";
			  }catch(Exception exception){
				  throw exception;
				}
			return status;
			  
			  
		  }
		 
		
		  @Test
		  public String MouseOver(String value,String content ,String searchBy,WebDriver driver) throws Exception 
		  {
			  log= Logger.getLogger("automateappConnect");
			  WebElement element = null;
			  try{
				  Actions action = new Actions(driver);
				  switch (searchBy) {
				  case "Id" : 
					  element =driver.findElement(By.id(value));
					  action.moveToElement(element).build().perform();
					  element.click();
					    break;
				  case "Xpath" : 
					  element =driver.findElement(By.xpath(value));
					  action.moveToElement(element).build().perform();
					  element.click();
					    break;
				  }
				  status = "Success";
			  }catch(Exception exception){
				  throw exception;
				}
			return status;
			  
			  
		  }
		  
		  
		  @Test(enabled=true)
		  public String fileUpload(String value,String content ,String searchBy,WebDriver driver) throws Exception{
		 log= Logger.getLogger("automateappConnect");
		 try{
			 String Autoexe = content;
			 //driver.findElement(By.xpath(value));
			 Runtime.getRuntime().exec(Autoexe);
			 Thread.sleep(5000);
			 status = "Success";
		 }catch(Exception exception){
		 throw exception;
		 }
		 return status;
		 

		 }
		  
		public String Wait(int time) throws Exception{
			try{
				Thread.sleep(time);
				status = "Success";
			}catch(Exception exception){
				throw exception;
			}
			return status;
		}
		  
		public String UIDandTYPE(String value, String content, String searchBy, WebDriver driver){
			
			 UID userId = new UID();
			 String UID = userId.toString();
			 CharSequence UIDNumber = UID.subSequence(10, 20);
			 try {
				 String UIDandConntent =content+UIDNumber; 
				type(value, UIDandConntent, searchBy, driver);
				currentData = UIDandConntent;
				status = "Success";
			} catch (Exception exception) {
				
				exception.printStackTrace();
			}
			return status;
			 
		}
		 
		 
		

		public String TypeCurrentData(String value, String currentData, String searchBy, WebDriver driver) throws Exception {
			
			try{
				System.out.println("Type the Previous data");
				 type(value, currentData, searchBy,driver);
				 System.out.println("Type the Previous data entered Successfully");
				 currentData="";
				 System.out.println("currrent data set to null");
				 status = "Success";
			}catch(Exception exception){
				exception.printStackTrace();
			}
			return status;
		}


		public String scroll(String value, String content, String searchBy,
				WebDriver driver) {
			try{
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("DoubleClickOnRow_24")));
				Thread.sleep(500);
				status = "Success";
			}catch(Exception exception){
				exception.printStackTrace();
			}
			return status;
			
		}


		void alert(String value, String content, String searchBy,
				WebDriver driver) {
			try{
				if(!driver.switchTo().alert().equals(null)){
					driver.switchTo().alert().accept();	
			}	
			}catch(Exception exception){
				exception.printStackTrace();
				throw exception;
			}
		}


		void popup(String value, String content, String searchBy,
				WebDriver driver) {
			
			
		}
		
		public String viewAndClick(String value,String content ,String searchBy,WebDriver driver) {
				WebElement element=null;
			try {
				JavascriptExecutor je = (JavascriptExecutor) driver;
				element = driver.findElement(By.xpath(value));
				je.executeScript("arguments[0].scrollIntoView(true);",element);
				System.out.println(element.getText());
				element.click();
				status = "Success";
		    }catch (WebDriverException wde) {
		        scrollToElement(element,driver);
		        element.click();
		    }
			return status;
		}
		    
		    private String scrollToElement(WebElement el,WebDriver driver) {
		        if (driver instanceof JavascriptExecutor) {
		            ((JavascriptExecutor) driver)
		                .executeScript("arguments[0].scrollIntoView(true);", el);
		            status = "Success";
		        }
				return status;
		    }


			public String IterateAndType(String value, String content,
					String searchBy, WebDriver driver) {
				int appendValue = IteratorValue;
				try{
					content = content+appendValue;
					content.toString();
					type(value, content, searchBy, driver);
					IteratorValue++;
					status = "Success";
				}catch(Exception exceptionxe){
					
				}
				return status;
				
			}
			
			public String IterateAndTypeSame(String value, String content,
					String searchBy, WebDriver driver) {
				int appendValue = IteratorValue; 
				try{
					appendValue--;
					content = content+appendValue;
					content.toString();
					type(value, content, searchBy, driver);
					//System.out.println(IteratorValue);
					status = "Success";
				}catch(Exception exception){
					
				}
				return status;
			}

			public String ClickAndType(String value, String content,
					String searchBy, WebDriver driver) {
				try{
					 WebElement element = null;
					 Actions actions = null;
						switch (searchBy) {
						  case "Id" : 
							  element =driver.findElement(By.id(value));
							  actions = new Actions(driver);
							  actions.moveToElement(element);
							  actions.click();
							  actions.sendKeys(content);
							  actions.build().perform();
						  case "Xpath" : 
							  element =driver.findElement(By.xpath(value));
							  actions = new Actions(driver);
							  actions.moveToElement(element);
							  actions.click();
							  actions.sendKeys(content);
							  actions.build().perform();
						}
						status = "Success";
				}catch(Exception exception){
					throw exception;
				}
				return status;
				
			}
			
			
			@Test(enabled=true)
			 public String ClickEnter(String value,String content ,String searchBy,WebDriver driver) throws Exception{
					WebElement element =null;
					searchBy.trim();
					log= Logger.getLogger("automateappConnect");
					try{
						switch(searchBy){
						case "Id":
								element =driver.findElement(By.id(value));
								element.sendKeys(Keys.ENTER);
						break;
						case "Xpath" : 
								   element =driver.findElement(By.xpath(value));
								element.sendKeys(Keys.ENTER);
						break;
						case "ClassName" :
								element= driver.findElement(By.className(value));
								element.sendKeys(Keys.ENTER);
						break;
						case "Name" :
								element= driver.findElement(By.name(value));
								element.sendKeys(Keys.ENTER);
						break;
					}
						status = "Success";
					}catch(Exception exception){
						throw exception;
					}

			return status;
			}
}
