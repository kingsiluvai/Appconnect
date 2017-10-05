package com.Base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SeleniumAsserts {
	String status = "";

	public String isDisplayed(String value, String content, String searchBy,
			WebDriver driver) throws Exception
			{
		String[] searchBySeperator = searchBy.split(":");
		String findElementBy = searchBySeperator[0];
		String CaseType = searchBySeperator[1];
		try{
			if(CaseType.equals("Negative")&&findElementBy.equals("Xpath")){
				driver.findElement(By.xpath(value)).isDisplayed();
				status = "Failure";
			}else if(CaseType.equals("Negative")&&findElementBy.equals("Id")){
				driver.findElement(By.id(value)).isDisplayed();
				status="Failure";
				
			}else {
				if(CaseType.equals("Positive")&&findElementBy.equals("Xpath")){
				driver.findElement(By.xpath(value)).isDisplayed();
				status="Success";
				}else if(CaseType.equals("Positive")&&findElementBy.equals("id")){
					driver.findElement(By.id(value)).isDisplayed();
				status="Success";
			}
			}
		}catch(Exception exception){
			if(CaseType.equals("Negative")){
				status = "Success";
			}else{
				status = "Failure";
			}
		}
		return status;
	}

	
	
	
	public String isSelected(String value, String content, String searchBy,
			WebDriver driver) throws Exception
			{
		String[] searchBySeperator = searchBy.split(":");
		String findElementBy = searchBySeperator[0];
		String CaseType = searchBySeperator[1];
		try{
			if(CaseType.equals("Negative")&&findElementBy.equals("Xpath")){
				driver.findElement(By.xpath(value)).isSelected();
				status="Failure";
			}else if(CaseType.equals("Negative")&&findElementBy.equals("Id")){
				driver.findElement(By.xpath(value)).isSelected();
				status="Failure";
			}else {
				if(CaseType.equals("Positive")&&findElementBy.equals("Xpath")){
				driver.findElement(By.xpath(value)).isSelected();;
				status="Success";
				}else if(CaseType.equals("Positive")&&findElementBy.equals("Xpath")){
					driver.findElement(By.xpath(value)).isSelected();
					status="Success";
			}
			}
		}catch(Exception exception){
			if(CaseType.equals("Negative")){
				status = "Success";
			}else{
				status = "Failure";
			}
		}
		return status;
	}
	
	public String isEnabled(String value, String content, String searchBy,
			WebDriver driver) throws Exception
			{
		String[] searchBySeperator = searchBy.split(":");
		String findElementBy = searchBySeperator[0];
		String CaseType = searchBySeperator[1];
		try{
			if(CaseType.equals("Negative")&&findElementBy.equals("Xpath")){
				driver.findElement(By.xpath(value)).isEnabled();
				status="Failure";
			}else if(CaseType.equals("Negative")&&findElementBy.equals("Id")){
				driver.findElement(By.xpath(value)).isEnabled();
				status="Failure";
			}else {
				if(CaseType.equals("Positive")&&findElementBy.equals("Xpath")){
				driver.findElement(By.xpath(value)).isEnabled();;
				status="Success";
				}else if(CaseType.equals("Positive")&&findElementBy.equals("Xpath")){
					driver.findElement(By.xpath(value)).isEnabled();
					status="Success";
			}
			}
		}catch(Exception exception){
			if(CaseType.equals("Negative")){
				status = "Success";
			}else{
				status = "Failure";
			}
		}
		return status;
	}
	
	public String isTitleEquals(String value, String content, String searchBy,
			WebDriver driver) throws Exception
			{
		try{
			String expectedTitle = driver.getTitle();
			if (content.contentEquals(expectedTitle)){
			            status = "Success";

			        } else {
			            status = "Failure";
			        }
		}catch(Exception exception){
			
		}
		return status;
	}
	
	
}
