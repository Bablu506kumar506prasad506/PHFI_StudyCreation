package com.phfi.StudyCreation.Execution;

import java.io.FileInputStream;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.phfi.StudyCreation.GlobalMethod.StudyCreationGlobalMethod;
import com.phfi.StudyCreation.Scenarios.NewStudyCreation;

import jxl.Sheet;
import jxl.Workbook;

public class StudyCreationExecution {
	
	@BeforeMethod
	public void beforeMethod() throws Exception, Exception {

		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/DataFile.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("Login");

		String URL = r1.getCell(1, 0).getContents();
		String firefoxBrowser = r1.getCell(1, 2).getContents();
		StudyCreationGlobalMethod.LaunchBrowser(firefoxBrowser, URL);
	}
	
	@Test(priority = 0)
	public static void exctractData() throws Exception {
		NewStudyCreation newcreatstdy = new NewStudyCreation();
		newcreatstdy.studycreation();
	}
	
	/*@AfterMethod
	public static void close() {
		StudyCreationGlobalMethod.driver.close();
	}*/

}
