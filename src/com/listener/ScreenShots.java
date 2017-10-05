package com.listener;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.Resource.InputData;

public class ScreenShots {

	public void OnTestSuccess(String fileName, String sheetName,
			int testCaseNo, WebDriver driver) throws Exception {
		try {
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			File dir = new File(InputData.SuccessSnap+"\\"+fileName);
			 dir.mkdir();
	        FileUtils.copyFile(scrFile, new File(dir+"\\"+sheetName+"_"+fileName+"_"+testCaseNo+"-Success.png"));
	        System.out.println("Screenshot Taken for Success");
		} catch (Exception exception) {
			throw exception;
		}

	}

	public void OnTestFailure(String fileName, String sheetName,
			int testCaseNo,WebDriver driver) throws Exception {
		try {
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FilenameUtils.removeExtension(fileName);
			File dir = new File(InputData.FailureSnap+"\\"+fileName);
			 dir.mkdir();
	        FileUtils.copyFile(scrFile, new File(dir+"\\"+sheetName+"_"+fileName+"_"+testCaseNo+"-Failure.png"));
	        System.out.println("Screenshot Taken for Failure");
		} catch (Exception exception) {
			throw exception;
		}

	}
	
	public void TakeScreenShots(WebDriver driver) throws Exception {
		try {
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			File dir = new File(InputData.ManualSnap);
			 dir.mkdir();
	        FileUtils.copyFile(scrFile, new File(dir+"ManualSnap.png"));

		} catch (Exception exception) {
			throw exception;
		}

	}

}
