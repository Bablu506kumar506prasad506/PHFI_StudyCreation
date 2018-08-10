package com.phfi.StudyCreation.Scenarios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.phfi.StudyCreation.GlobalMethod.StudyCreationGlobalMethod;
import com.phfi.StudyCreation.GlobalMethod.StudyCreationWaitMethod;

import jxl.Sheet;
import jxl.Workbook;

//----Filling study creation form----//
public class NewStudyCreation extends StudyCreationGlobalMethod {

	public NewStudyCreation() {
		PageFactory.initElements(StudyCreationGlobalMethod.driver, this);
	}

	StudyCreationWaitMethod GWait = new StudyCreationWaitMethod(StudyCreationGlobalMethod.driver);
	Actions action = new Actions(StudyCreationGlobalMethod.driver);

	public void studycreation() throws Exception {
		FileInputStream fi = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/Study Creation.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet st = wb.getSheet("Create Study");

		String AssignProject = st.getCell(1, 2).getContents();
		String AssignPI = st.getCell(1, 3).getContents();
		String StudyTitle = st.getCell(1, 4).getContents();
		String StudyDescription = st.getCell(1, 5).getContents();
		String ReadmeTXTFile = st.getCell(1, 6).getContents();
		String StudyType = st.getCell(1, 7).getContents();
		String StudyCategory = st.getCell(1, 8).getContents();
		String Sponcer = st.getCell(1, 9).getContents();
		String StagingURL = st.getCell(1, 10).getContents();
		String ProductionURL = st.getCell(1, 11).getContents();
		String DateFormat = st.getCell(1, 12).getContents();
		String StudyMetaData = st.getCell(1, 13).getContents();
		String Keywords = st.getCell(1, 14).getContents();
		String ExpectedEndDate = st.getCell(1, 15).getContents();
		String eCRFOtherThanEnglish = st.getCell(1, 16).getContents();
//		Superadmin_Login();
		Pinvestigator_Login();
		WebElement displMNGSTDS = GWait.Wait_GetElementByXpath("//div[2]/div[1]/ul/li[1]/a", 120);
		displMNGSTDS.click();
		
		/*CreateProject();
		
		WebElement myDynamicElement = GWait.Wait_GetElementById("anchCreateStudy", 120);
		myDynamicElement.click();
		Thread.sleep(1500);
		Select Project = new Select(GWait.Wait_GetElementById("ddlStudyProject", 120));
		Project.selectByVisibleText(AssignProject);

		Select PI = new Select(GWait.Wait_GetElementById("ddlUsersList", 120));
		PI.selectByVisibleText(AssignPI);

		GWait.Wait_GetElementById("txtStudyName", 120).sendKeys(StudyTitle);
		GWait.Wait_GetElementById("txtDescription", 120).sendKeys(StudyDescription);
		GWait.Wait_GetElementById("fileReadMe", 120).sendKeys(ReadmeTXTFile);

		WebElement typeStudy = driver.findElement(By.cssSelector("#rbtnstudytype > tbody:nth-child(1)"));
		ArrayList<WebElement> Studyrows = (ArrayList<WebElement>) typeStudy.findElements(By.tagName("tr"));
		for (WebElement iterable_element : Studyrows) {
			ArrayList<WebElement> cells = (ArrayList<WebElement>) iterable_element.findElements(By.xpath("//td/label"));
			for (WebElement cell : cells) {
				System.out.println(cell.getText());
				if (cell.getText().equalsIgnoreCase(StudyType)) {
					cell.click();
					break;
				}
			}
		}
		GWait.Wait_GetElementById("txtCategoryName", 120).sendKeys(StudyCategory);
		GWait.Wait_GetElementById("txtClientName", 120).sendKeys(Sponcer);
		GWait.Wait_GetElementById("ctl00_ContentPlaceHolder1_ctl00_txtStagingUrl", 120).sendKeys(StagingURL);
		GWait.Wait_GetElementById("txtProductionUrl", 120).sendKeys(ProductionURL);
		Select DATEFRMT = new Select(GWait.Wait_GetElementById("ddlDateFormat", 120));
		DATEFRMT.selectByVisibleText(DateFormat);
		GWait.Wait_GetElementById("txtStudyMetaData", 120).sendKeys(StudyMetaData);
		GWait.Wait_GetElementById("txtKeyWords", 120).sendKeys(Keywords);
		GWait.Wait_GetElementById("txtExpectedEndDate", 120).sendKeys(ExpectedEndDate);
		// ---Select eCRF Other Than English Checkboxes---//
		String all = eCRFOtherThanEnglish;
		String[] ane = all.split(",");
		WebElement listLang = driver.findElement(By.cssSelector("#chkCultureLanguages > tbody:nth-child(1)"));
		ArrayList<WebElement> rows = (ArrayList<WebElement>) listLang.findElements(By.tagName("tr"));
		for (WebElement iterable_element : rows) {
			ArrayList<WebElement> cells = (ArrayList<WebElement>) iterable_element
					.findElements(By.xpath("//td/span/label"));
			for (WebElement cell : cells) {
				System.out.println(cell.getText());
				for (String Single : ane) {
					if (cell.getText().equalsIgnoreCase(Single)) {
						cell.click();
					}
				}
				
			}
			break;
		}

		// ----Click Submit button----//
		 GWait.Wait_GetElementById("btnSave", 120).click();*/
//		ConfigureSettingOldLink();
		scrollToBottom();
		//----Navigation----//
//		GWait.Wait_GetElementByXpath("//div[3]/div[2]/div/ul/li[4]", 120).click();
		ConfigureSettingNewLink();
		/*CreateVisit_Link();
		CreatePages_Link();
		CreateCodeList_Link();*/
		CreatePanel_Link();
		
		/*AssignPages();
		AssignPannels();*/
		

	}

}
