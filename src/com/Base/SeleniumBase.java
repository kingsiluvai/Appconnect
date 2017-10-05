package com.Base;


import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.listener.ScreenShots;

public class SeleniumBase {
	Actions action = null;
	Logger log = null;
	public static String currentData = "";
	public static String TestCaseName = "";
	private String searchBy = null;
	private String value = null;
	private String content = null;
	private String type = null;
	private String eventCount = null;
	private String description = null;
	private SeleniumEventTrigger eventTrigger = null;
	private SeleniumAsserts assertion = null;
	ScreenShots snap = null;
	private String status = "Failure";

	public String ExecuteTestNG(String[][] data, WebDriver driver)
			throws Exception {

		log = Logger.getLogger("automateappConnect");
		try {
			for (String[] a : data) {
				eventTrigger = new SeleniumEventTrigger();
				assertion = new SeleniumAsserts();
				eventCount = a[0].trim();
				description = a[1].trim();
				searchBy = a[2].trim();
				type = a[3].trim();
				value = a[4].trim();
				content = a[5].trim();
				System.out.println(eventCount+"  : "+description);
				log.info(eventCount+"  : "+description);
				switch (type) {
				case "Click":
					status = eventTrigger.buttonClick(value, content, searchBy, driver);
					break;
				case "Type":
					status = eventTrigger.type(value, content, searchBy, driver);
					break;
				case "Click:Type":
					status = eventTrigger.ClickAndType(value, content, searchBy, driver);
					break;
				case "CheckBox":
					status = eventTrigger.CheckBox(value, content, searchBy, driver);
					break;
				case "DoubleClick":
					status = eventTrigger.doubleClick(value, content, searchBy, driver);
					status = "Success";
					break;
				case "WaitUntilVisible":
					status = eventTrigger.WaitUntilVisible(value, content, searchBy,
							driver);
					break;
				case "SelectBox":
					status = eventTrigger.SelectBox(value, content, searchBy, driver);
					break;
				case "MouseOver":
					status = eventTrigger.MouseOver(value, content, searchBy, driver);
					break;
				case "Upload":
					status = eventTrigger.fileUpload(value, content, searchBy, driver);
					break;
				case "Wait":
					int WaitValue = Integer.parseInt(content);
					status = eventTrigger.Wait(WaitValue);
					break;
				case "DragAndDrop":
					status = eventTrigger.DragAndDrop(value, content, searchBy, driver);
					break;
				case "Type:Enter":
					status = eventTrigger.typeEnter(value, content, searchBy, driver);
					break;
				case "Popup":
					eventTrigger.popup(value, content, searchBy, driver);
					status = "Success";
					break;
				case "Alert":
					eventTrigger.alert(value, content, searchBy, driver);
					status = "Success"; 
					break;
				case "Scroll":
					status = eventTrigger.scroll(value, content, searchBy, driver);
					break;
				case "UID:TYPE":
					status = eventTrigger.UIDandTYPE(value, content, searchBy, driver);
					break;
				case "TypeCurrentSavedData":
					status = eventTrigger.TypeCurrentData(value, currentData, searchBy,
							driver);
					break;
				case "ScreenShot":
					snap.TakeScreenShots(driver);
					status = "Success";
					break;
				case "View:Click":
					status = eventTrigger.viewAndClick(value, content, searchBy, driver);
					break;
				case "Clear:Type":
					status = eventTrigger.clearAndType(value, content, searchBy, driver);
					break;
				case "DragAndDrop:XY":
					status = eventTrigger.DragAndDropBy(value, content, searchBy, driver);
					break;
				case "Iterate:Type":
					status = eventTrigger.IterateAndType(value, content, searchBy, driver);
					break;
				case "Iterate:TypeSame":
					status = eventTrigger.IterateAndTypeSame(value, content, searchBy, driver);
					break;
				case "isDisplayed":
					status = assertion.isDisplayed(value, content, searchBy, driver);
					break;
				case "isEnabled":
					status = assertion.isEnabled(value, content, searchBy, driver);
					break;
				case "isSelected":
					status = assertion.isSelected(value, content, searchBy, driver);
					break;
				case "isTiltleEqual":
					status = assertion.isTitleEquals(value, content, searchBy, driver);
					break;
				case "AcceptAlert":
					driver.switchTo().alert().accept();
					status = "Success";
					break;
				case "DismissAlert":
					driver.switchTo().alert().dismiss();
					status = "Success";
					break;
				case "CloseAlert":
					( ( JavascriptExecutor ) driver )
		            .executeScript( "window.onbeforeunload = function(e){};" );
					break;
				case "Click:Enter" :
					status = eventTrigger.ClickEnter(value, content, searchBy, driver);
					status = "Success";
					break;
				}
			}
		} catch (Exception exception) {
			log.info(eventCount + " [Completed with -" + searchBy+ "]");
			System.out.println("TestCase Failed with Exception :\n" +exception);
			status = "Failure";
		}
		return status;
	}

}
