package com.phfi.StudyCreation.GlobalMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class StudyCreationGlobalMethod {

	public static WebDriver driver;
	
	public StudyCreationGlobalMethod() {
		PageFactory.initElements(driver, this);
	}
	
	static StudyCreationWaitMethod GWait = new StudyCreationWaitMethod(driver);
	Actions action	= new Actions(driver);
	
	int tr = 2;

	
	static JavascriptExecutor js = (JavascriptExecutor) driver;

	public static void LaunchBrowser(String browserName, String Url) {

		if (browserName.equals("firefox")) {
			System.setProperty("webdriver.firefox.driver",
					System.getProperty("user.dir") + "/src/main/resources/win/geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/src/main/resources/win/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("ie")) {
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "/src/main/resources/win/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}

		driver.manage().window().maximize();
		driver.get(Url);
	}

	public static void Superadmin_Login() throws Exception {
		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/DataFile.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("LoginData");

		String Username = r1.getCell(1, 1).getContents();
		String Password = r1.getCell(2, 1).getContents();

		driver.findElement(By.id("txtUserName")).sendKeys(Username);
		driver.findElement(By.id("txtPassword")).sendKeys(Password);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
	}

	public static void Pinvestigator_Login() throws Exception {
		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/DataFile.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet st = wb.getSheet("LoginData");

		String Username = st.getCell(1, 3).getContents();
		String Password = st.getCell(2, 3).getContents();

		driver.findElement(By.id("txtUserName")).sendKeys(Username);
		driver.findElement(By.id("txtPassword")).sendKeys(Password);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
	}

	public static void CreateProject() throws Exception {
		FileInputStream fi = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/Study Creation.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet st = wb.getSheet("Create Study");

		String ProjectCode = st.getCell(1, 0).getContents();
		String ProjectName = st.getCell(1, 1).getContents();

		driver.findElement(By.cssSelector("#ctl00_ContentPlaceHolder1_ctl00_pnlCreateStudy > div > div > a")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("txtProjectCode")).sendKeys(ProjectCode);
		driver.findElement(By.id("txtProjectName")).sendKeys(ProjectName);
		 driver.findElement(By.id("hypProject")).click();

	}

	public static void ConfigureSettingNewLink() throws Exception {
		Thread.sleep(4000);
		 driver.findElement(By.cssSelector("img[alt=\"Configure eCRF\"]")).click();

		/*Thread.sleep(1500);
		//----by passing the study number----//
		driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(15) > a:nth-child(4)")).click();*/
		/*Thread.sleep(2000);
		driver.findElement(
				By.cssSelector("#Configurestudy > div > div > div.modal-body > div > div > span:nth-child(2) > label"))
				.click();
		driver.findElement(By.id("btnconfiggo")).click();*/
	}

	public static void ConfigureSettingOldLink() throws Exception {
		driver.findElement(By.cssSelector("img[alt=\"Configure eCRF\"]")).click();
		Thread.sleep(2000);
		driver.findElement(
				By.cssSelector("#Configurestudy > div > div > div.modal-body > div > div > span:nth-child(1) > label"))
				.click();
		driver.findElement(By.id("btnconfiggo")).click();
	}

	public static void CreateVisit_Link() throws Exception, IOException {
		driver.findElement(By.cssSelector("#navbar-collapse-1 > ul > li:nth-child(1) > a")).click();
		driver.findElement(By.id("createvisit")).click();

		FileInputStream fi = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/Study Creation.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r = wb.getSheet("Visits");
		int noOfRows = r.getRows();
		int VisitCount = noOfRows - 1;
		System.out.println("No of Visit is: "+VisitCount);
		
		for (int i = 1; i <= VisitCount; i++) {
			FileInputStream fii = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/resources/Study Creation.xls");
			Workbook wb1 = Workbook.getWorkbook(fii);
			Sheet r2 = wb1.getSheet("Visits");

			String VisitName = r2.getCell(0, i).getContents();
			String VisitDesc = r2.getCell(1, i).getContents();
			String Annotation = r2.getCell(2, i).getContents();
			String ParentVisit = r2.getCell(3, i).getContents();
			String IsGlobal = r2.getCell(4, i).getContents();
			String IsRecurring = r2.getCell(5, i).getContents();
			String IsPreScreening = r2.getCell(6, i).getContents();
			// System.out.println("Prescreening "+IsPreScreening);

			driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_txtVisitName")).clear();
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_txtVisitName")).sendKeys(VisitName);
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_txtVisitDescription")).clear();
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_txtVisitDescription")).sendKeys(VisitDesc);
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_txtVisitAnnotation")).clear();
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_txtVisitAnnotation")).sendKeys(Annotation);
			if (ParentVisit.equals("")) {

			} else {
				new Select(driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_ddlVisits")))
						.selectByVisibleText(ParentVisit);

			}

			if (IsGlobal.equalsIgnoreCase("yes")) {
				driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_chkIsGlobal")).click();
			} else {

			}

			if (IsRecurring.equalsIgnoreCase("yes")) {
				driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_chkIsRecurring")).click();
			} else {
			}
			if (IsPreScreening.equalsIgnoreCase("yes")) {
				driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_chkIsPrescreeingVisit")).click();
			} else {
			}
			Thread.sleep(2000);
			 driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_btnSave")).click();
			Thread.sleep(2000);

		}
		Thread.sleep(2000);
		WebElement Submit_BTN = driver.findElement(By.xpath("//div[@id='creatvisitModal']/div/div/div[3]/button"));
		Submit_BTN.click();
		Thread.sleep(2000);

	}

	public static void CreatePages_Link() throws Exception, IOException {
		driver.findElement(By.cssSelector("#navbar-collapse-1 > ul > li:nth-child(1) > a")).click();
		driver.findElement(By.id("createpage")).click();

		FileInputStream fi = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/Study Creation.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r2 = wb.getSheet("Pages");
		Thread.sleep(3000);

		int noOfRows = r2.getRows();
		int PageCount = noOfRows - 1;
		System.out.println("No of page count is: "+PageCount);
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_txtPageName")).clear();
		for (int i = 1; i <= PageCount; i++) {

			String PageName = r2.getCell(0, i).getContents();
			String PageDesc = r2.getCell(1, i).getContents();
			String MenuDisp = r2.getCell(2, i).getContents();
			String Annotation = r2.getCell(3, i).getContents();
			String PageInstruction = r2.getCell(4, i).getContents();
			String InvVerif = r2.getCell(5, i).getContents();
			String IsGlobal = r2.getCell(4, i).getContents();

			driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_txtPageName")).clear();
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_txtPageName")).sendKeys(PageName);
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_txtPageDesc")).clear();
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_txtPageDesc")).sendKeys(PageDesc);
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_txtMenuDisplayName")).clear();
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_txtMenuDisplayName")).sendKeys(MenuDisp);
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_txtPageAnnotation")).clear();
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_txtPageAnnotation")).sendKeys(Annotation);
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_txtPageInstructions")).clear();
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_txtPageInstructions")).sendKeys(PageInstruction);

			if (InvVerif.equalsIgnoreCase("yes")) {
				driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_chkIsVerificationRequired")).click();
			} else {
			}
			if (IsGlobal.equalsIgnoreCase("yes")) {
				driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_chkIsGlobal")).click();
			}
			Thread.sleep(2000);
			 driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_btnPageSave")).click();

		}
		Thread.sleep(2000);
		WebElement Submit_BTN = driver.findElement(By.xpath("//div[@id='creatpageModal']/div/div/div[3]/button"));
		Submit_BTN.click();
		Thread.sleep(2000);
	}

	public void AssignPages() throws Exception {

		FileInputStream fi = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/Study Creation.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r7 = wb.getSheet("Visits");

		int VisitCount = r7.getRows() - 1;

		for (int i = 1; i <= VisitCount; i++) {

			String VisitName = r7.getCell(0, i).getContents();
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("td > a > img")).click();
			String a = "img[alt=\"Expand <div class='navigation-heading'>";
			String d = "</div>\"]";
			String p = a + VisitName + d;
			Thread.sleep(1000);
			if (i == 1) {

			} else {
				driver.findElement(By.cssSelector(p)).click();
			}
			String Addpage = "Pagedialog_" + i;

			// Click on Add page link
			Thread.sleep(1000);
			driver.findElement(By.id(Addpage)).click();

			Thread.sleep(500);
			driver.switchTo().frame(0);
			Thread.sleep(500);

			FileInputStream fii1 = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/resources/Study Creation.xls");
			Workbook wb11 = Workbook.getWorkbook(fii1);
			Sheet r81 = wb11.getSheet("Assign Pages");

			int noOfRows = r81.getRows();
			int PageCount = noOfRows - 1;
			// System.out.println(PageCount);
			Thread.sleep(500);
			for (int j = 1; j <= PageCount; j++) {
				Thread.sleep(500);
				String PageAssn = r81.getCell(i - 1, j).getContents();

				if (PageAssn.equals("")) {
					// System.out.println("No Assign pages");
				} else {
					Thread.sleep(500);
					driver.findElement(By.linkText(PageAssn)).click();
				}
			}
			driver.findElement(By.id("selectpages")).click();
			Thread.sleep(500);
		}
	}

	public void CreateCodeList_Link() throws Exception {

		Thread.sleep(1500);
		driver.findElement(By.cssSelector("#navbar-collapse-1 > ul > li:nth-child(2) > a")).click();
		Thread.sleep(500);
		driver.findElement(By.linkText("Create / Modify CodeLists")).click();
		Thread.sleep(3000);

		FileInputStream fi = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/Study Creation.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r3 = wb.getSheet("Code List");
		int Count = r3.getRows();
		System.out.println("No of Code Lists "+Count);

		for (int i = 1; i <= Count - 1; i++) {
			FileInputStream fii = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/resources/Study Creation.xls");
			Workbook wb1 = Workbook.getWorkbook(fii);
			Sheet r4 = wb1.getSheet("Code List");

			String CodeListName = r4.getCell(0, i).getContents();
			String AnnotationCode = r4.getCell(1, i).getContents();
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_txtCodelistName")).clear();
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_txtCodelistName")).sendKeys(CodeListName);
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_txtAnnotation")).clear();
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_txtAnnotation")).sendKeys(AnnotationCode);
			String CLValue = r4.getCell(2, i).getContents();
			String CLText = r4.getCell(3, i).getContents();
			String DisplaycodelistText = r4.getCell(4, i).getContents();
			String[] L1 = CLValue.split(",");
			String[] L2 = CLText.split(",");
			int Cnt = L1.length;
			System.out.println(CLText + " Hello " + DisplaycodelistText);
			if (DisplaycodelistText.equalsIgnoreCase("no")) {
				driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_cbDisplayCodelistValueText")).click();
			}
			for (int j = 1; j <= Cnt; j++) {

				driver.findElement(
						By.id("ctl00_ContentPlaceHolder1_ctl00_gvCodeListResponceValue_ctl03_txtCodeListResponceValue"))
						.clear();
				driver.findElement(
						By.id("ctl00_ContentPlaceHolder1_ctl00_gvCodeListResponceValue_ctl03_txtCodeListResponceValue"))
						.sendKeys(L1[j - 1]);
				driver.findElement(
						By.id("ctl00_ContentPlaceHolder1_ctl00_gvCodeListResponceValue_ctl03_txtCodeListResponceText"))
						.clear();
				driver.findElement(
						By.id("ctl00_ContentPlaceHolder1_ctl00_gvCodeListResponceValue_ctl03_txtCodeListResponceText"))
						.sendKeys(L2[j - 1]);
				driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_gvCodeListResponceValue_ctl03_btnAdd"))
						.click();

				scrollToBottom();
			}
			Thread.sleep(2000);
			driver.findElement(By.id("btnCodelistSave")).click();
			Thread.sleep(2000);
		}
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_btnGoBack")).click();
		Thread.sleep(3000);
	}

	public static void scrollToBottom() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(
				"window.scrollTo(0, Math.max(document.documentElement.scrollHeight, document.body.scrollHeight, document.documentElement.clientHeight));");

	}

	public static void scrollToElement(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public static void CreatePanel_Link() throws Exception {

		FileInputStream fii = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/Study Creation.xls");
		Workbook wb1 = Workbook.getWorkbook(fii);
		Sheet r4 = wb1.getSheet("Panels");
		FileInputStream fii1 = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/Study Creation.xls");
		Workbook wb11 = Workbook.getWorkbook(fii1);
		Sheet r5 = wb11.getSheet("Controls");

		int Count1 = r5.getRows();

		int PanelCount = r4.getRows() - 1;
		// System.out.println(PanelCount);
		System.out.println("No of panel is: "+PanelCount);
		for (int i = 1; i <= PanelCount; i++) {
			Thread.sleep(1500);
			driver.findElement(By.cssSelector("#navbar-collapse-1 > ul > li:nth-child(3) > a")).click();
			driver.findElement(By.linkText("Create Panel")).click();

			String PanelName = r4.getCell(0, i).getContents();
			String AnnotationCode = r4.getCell(1, i).getContents();
			String PanelHeading = r4.getCell(2, i).getContents();
			String PanelLayout = r4.getCell(3, i).getContents();
			String PanelType = r4.getCell(4, i).getContents();
			String SF = r4.getCell(5, i).getContents();
			String Term = r4.getCell(6, i).getContents();
			String AE = r4.getCell(7, i).getContents();
			String Concom = r4.getCell(8, i).getContents();
			String Columns = r4.getCell(9, i).getContents();
			String Rows = r4.getCell(10, i).getContents();
			String RepColumn = r4.getCell(11, i).getContents();
			String ColWidth = r4.getCell(12, i).getContents();
			// System.out.println("Panel Layout "+PanelLayout.toLowerCase());
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_txtPnlName")).clear();
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_txtPnlName")).sendKeys(PanelName);
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_txtPnlAnnotation")).clear();
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_txtPnlAnnotation")).sendKeys(AnnotationCode);
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_txtPnlHeading")).clear();
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_txtPnlHeading")).sendKeys(PanelHeading);
			((JavascriptExecutor) driver)
					.executeScript("window.scrollTo(0,-Math.max(document.documentElement.scrollHeight,"
							+ "document.body.scrollHeight,document.documentElement.clientHeight));");
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,350)", "");
			// System.out.println("Satisfied1");

			if (PanelLayout.equalsIgnoreCase("vertical")) {
				scrollToBottom();
				driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_rdbPnlLayout_0")).click();
				Thread.sleep(1000);
				new Select(driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_drpColums")))
						.selectByVisibleText(Columns);
				new Select(driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_drpRows")))
						.selectByVisibleText(Rows);
				driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_txtColumnWidths")).sendKeys(ColWidth);
				((JavascriptExecutor) driver)
						.executeScript("window.scrollTo(0,-Math.max(document.documentElement.scrollHeight,"
								+ "document.body.scrollHeight,document.documentElement.clientHeight));");
				((JavascriptExecutor) driver).executeScript("window.scrollBy(0,350)", "");

				// System.out.println("Satisfied");
				// System.out.println("Panel Type "+PanelType);
				scrollToBottom();
				if (PanelType.equalsIgnoreCase("lab")) {
					// System.out.println("Lab Satisfied");
					driver.findElement(By.id("ctl18_rdbPnlType_1")).click();
					// Thread.sleep(2000);
				} else {
					// Thread.sleep(2000);
					// System.out.println("Not Satisfied");
				}
				if (SF.equalsIgnoreCase("yes")) {
					driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_chkIsScreenfailure")).click();
				} else {

				}
				if (Term.equalsIgnoreCase("yes")) {
					driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_chkIsTermination")).click();
				} else {

				}
				if (AE.equalsIgnoreCase("yes")) {
					driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_chkIsAdverseEvent")).click();
				} else {

				}
				Thread.sleep(2000);
				if (Concom.equalsIgnoreCase("yes")) {
					driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_chkIsConcomitant")).click();
				} else {

				}
				for (int j = 1; j <= Count1 - 7; j++) {
					String AC = r5.getCell(1, 6 + j).getContents();
					String Control = r5.getCell(2, 6 + j).getContents();
					String Position = r5.getCell(3, 6 + j).getContents();
					String MergeCount = r5.getCell(4, 6 + j).getContents();
					Position = Position.replace(",", "");
					Position = "vtTableLayouttd" + Position;
					// System.out.println(Position);

					if (AC.equalsIgnoreCase(AnnotationCode.toLowerCase())) {

						if (Control.equalsIgnoreCase("colmerger") || Control.equalsIgnoreCase("rowmerger")) {

							int m = Integer.parseInt(Columns);
							int hh = Integer.parseInt(Rows);
							int y = 15 - m;
							String JSE = "window.scrollBy(0," + y + ")";
							// String Neg = "window.scrollBy(0,-"+y+")";
							// ((JavascriptExecutor) driver).executeScript(Neg,
							// "");
							if (hh > 12) {
								// System.out.println("Merge Satisfied");
								((JavascriptExecutor) driver).executeScript(JSE, "");
							} else {
								// System.out.println("Merge Not Satisfied");
							}

							Thread.sleep(500);
							WebElement draggable = driver.findElement(By.id(Control));
							WebElement droppable = driver.findElement(By.id(Position));
							new Actions(driver).dragAndDrop(draggable, droppable).build().perform();
							Alert alert = driver.switchTo().alert();
							alert.sendKeys(MergeCount);
							alert.accept();
							Thread.sleep(1000);

						} else {
							int m = Integer.parseInt(Columns);
							int hh = Integer.parseInt(Rows);
							int y = 15 - m;
							String JSE = "window.scrollBy(0," + y + ")";
							// String Neg = "window.scrollBy(0,-"+y+")";
							// ((JavascriptExecutor) driver).executeScript(Neg,
							// "");
							if (hh > 12) {
								// System.out.println("Satisfied");
								((JavascriptExecutor) driver).executeScript(JSE, "");
							} else {
								// System.out.println("Not Satisfied");
							}
							Thread.sleep(500);
							WebElement draggable = GWait.Wait_GetElementById(Control);
							WebElement droppable = GWait.Wait_GetElementById(Position);
							new Actions(driver).dragAndDrop(draggable, droppable).build().perform();
							Thread.sleep(500);

						}
					}
				}
				for (int j = 1; j <= Count1 - 7; j++) {
					String AC = r5.getCell(1, 6 + j).getContents();
					String Control = r5.getCell(2, 6 + j).getContents();
					String Position = r5.getCell(3, 6 + j).getContents();
					String MergeCount = r5.getCell(4, 6 + j).getContents();
					Position = Position.replace(",", "");
					Position = "vtTableLayouttd" + Position;
					// System.out.println(Position);

					if (AC.equalsIgnoreCase(AnnotationCode.toLowerCase())) {
						{

							if (Control.equalsIgnoreCase("rowmerger")) {

							} else {
								if (Control.equalsIgnoreCase("colmerger")) {

								} else {
									String Css = "#" + Position
											+ " > div.ctrl_placeholder > div.ctrl_toolbar > span > a.ctrlVrtEdit > img";
									// System.out.println(Css);
									Thread.sleep(2000);
									scrollToBottom();
									GWait.Wait_GetElementByCSS(Css).click();

									if (Control.equalsIgnoreCase("label")) {
										String LabelText = r5.getCell(5, 6 + j).getContents();
										String DisplayLabelText = r5.getCell(6, 6 + j).getContents();
										String Align = r5.getCell(7, 6 + j).getContents();
										String IsBold = r5.getCell(8, 6 + j).getContents();
										String Attachments = r5.getCell(9, 6 + j).getContents();
										String Notes = r5.getCell(10, 6 + j).getContents();
										String QRS = r5.getCell(11, 6 + j).getContents();
										String AuditLog = r5.getCell(12, 6 + j).getContents();
										Thread.sleep(1500);
										driver.findElement(By.id("txtLabelText")).clear();
										driver.findElement(By.id("txtLabelText")).sendKeys(LabelText);
										if (DisplayLabelText.equalsIgnoreCase("yes")) {
											driver.findElement(By.id("lblChkDisplay")).click();
										} else {
										}
										if (Align.equalsIgnoreCase("")) {
										} else {
											new Select(driver.findElement(By.id("sellblAlign")))
													.selectByVisibleText(Align);
										}
										if (IsBold.equalsIgnoreCase("yes")) {
											driver.findElement(By.id("chklblIsBold")).click();
										} else {

										}
										if (Attachments.equalsIgnoreCase("yes")) {
											driver.findElement(By.id("chklblHasAttachments")).click();
										} else {

										}
										if (Notes.equalsIgnoreCase("yes")) {
											driver.findElement(By.id("chklblHasNotes")).click();
										} else {

										}
										if (QRS.equalsIgnoreCase("yes")) {
											driver.findElement(By.id("chklblHasQRS")).click();
										} else {

										}
										/*
										 * if(AuditLog.equalsIgnoreCase("yes"))
										 * { driver.findElement(By.id(
										 * "chklblHasAuditLog")).click(); } else
										 * {
										 * 
										 * }
										 */

										/*
										 * File scrFile11 =
										 * ((TakesScreenshot)driver).
										 * getScreenshotAs(OutputType.FILE);
										 * String k11=
										 * "D:\\Projects\\Clinion\\Demo Study\\Screenshots\\Demo\\"+"
										 * Label - "+j+".png";
										 * FileUtils.copyFile(scrFile11, new
										 * File(k11));
										 */

										GWait.Wait_GetElementById("btnSavelabel").click();
									}

									else if (Control.equalsIgnoreCase("textbox")) {
										Thread.sleep(500);
										String LabelText = r5.getCell(5, 6 + j).getContents();
										String DisplayLabelText = r5.getCell(6, 6 + j).getContents();
										String Annotation = r5.getCell(7, 6 + j).getContents();
										String HelpText = r5.getCell(8, 6 + j).getContents();
										String DataType = r5.getCell(9, 6 + j).getContents();
										String MaxLength = r5.getCell(10, 6 + j).getContents();
										String Multiline = r5.getCell(11, 6 + j).getContents();
										String Mandatory = r5.getCell(12, 6 + j).getContents();
										String Attachment = r5.getCell(13, 6 + j).getContents();
										String Notes = r5.getCell(14, 6 + j).getContents();
										String QRS = r5.getCell(15, 6 + j).getContents();
										String Auditlog = r5.getCell(16, 6 + j).getContents();
										String MultiInput = r5.getCell(17, 6 + j).getContents();
										String NoOfMultiBx = r5.getCell(18, 6 + j).getContents();
										String MultiIPSep = r5.getCell(19, 6 + j).getContents();
										String MultiBxMaxLeng = r5.getCell(20, 6 + j).getContents();
										String ScreenFail = r5.getCell(21, 6 + j).getContents();
										String ScreenFailValue = r5.getCell(22, 6 + j).getContents();
										String Dropped = r5.getCell(23, 6 + j).getContents();
										String DroppedValue = r5.getCell(24, 6 + j).getContents();
										String AE1 = r5.getCell(25, 6 + j).getContents();
										String Concomitant = r5.getCell(26, 6 + j).getContents();
										Thread.sleep(1500);
										driver.findElement(By.id("txtTextboxLabel")).clear();
										driver.findElement(By.id("txtTextboxLabel")).sendKeys(LabelText);

										if (DisplayLabelText.equalsIgnoreCase("yes")) {
											driver.findElement(By.id("chkTxtDisplay")).click();
										} else {

										}
										driver.findElement(By.id("txtTextboxAnnotation")).clear();
										driver.findElement(By.id("txtTextboxAnnotation")).sendKeys(Annotation);
										new Select(driver.findElement(By.id("txtTextboxDataType")))
												.selectByVisibleText(DataType);
										if (HelpText != "") {
											driver.findElement(By.id("txtTextboxHelpText")).clear();
											driver.findElement(By.id("txtTextboxHelpText")).sendKeys(HelpText);

										} else {

										}
										
										if (MaxLength != "") {
											driver.findElement(By.id("txtTextboxMaxlen")).clear();
											driver.findElement(By.id("txtTextboxMaxlen")).sendKeys(MaxLength);
										}
										JavascriptExecutor js = (JavascriptExecutor) driver;
										js.executeScript("javascript:window.scrollBy(250,350)");
										if (Multiline.equalsIgnoreCase("yes")) {
											driver.findElement(By.id("chktxtIsMultiLine")).click();
										} else {

										}
										if (Mandatory.equalsIgnoreCase("yes")) {
											driver.findElement(By.id("chktextboxMandatory")).click();
										} else {

										}
										if (Attachment.equalsIgnoreCase("no")) {
											driver.findElement(By.id("chktxtHasAttachments")).click();
										} else {

										}
										if (Notes.equalsIgnoreCase("no")) {
											driver.findElement(By.id("chktxtHasNotes")).click();
										} else {

										}
										if (QRS.equalsIgnoreCase("no")) {
											driver.findElement(By.id("chktxtHasQRS")).click();
										} else {

										}
										if (Auditlog.equalsIgnoreCase("no")) {
											driver.findElement(By.id("chktxtHasAuditLog")).click();
										} else {

										}
										if (MultiInput.equalsIgnoreCase("yes")) {
											driver.findElement(By.id("chktxtHasMultiInputOption")).click();
										} else {

										}
										if (NoOfMultiBx != "") {
											driver.findElement(By.id("txtNoofMultinputBoxes")).clear();
											driver.findElement(By.id("txtNoofMultinputBoxes")).sendKeys(NoOfMultiBx);
										} else {
											// System.out.println("No of Muliti
											// input null");
										}
										if (MultiIPSep != "") {
											driver.findElement(By.id("txtmultiinputseparator")).clear();
											driver.findElement(By.id("txtmultiinputseparator")).sendKeys(MultiIPSep);
										} else {
											// System.out.println("Muliti Sep
											// null");
										}
										if (MultiBxMaxLeng != "") {
											driver.findElement(By.id("txtmultiboxesmaxlength")).clear();
											driver.findElement(By.id("txtmultiboxesmaxlength"))
													.sendKeys(MultiBxMaxLeng);
										} else {

										}
										if (ScreenFail.equalsIgnoreCase("yes")) {
											driver.findElement(By.id("chktxtIsScreenFailureItem")).click();
										} else {

										}
										if (ScreenFailValue != "") {
											driver.findElement(By.id("txtScreenFailureItemValue")).clear();
											driver.findElement(By.id("txtScreenFailureItemValue"))
													.sendKeys(ScreenFailValue);
										} else {

										}
										if (Dropped.equalsIgnoreCase("yes")) {
											driver.findElement(By.id("chkIsDroppedItem")).click();
										} else {

										}
										if (DroppedValue != "") {
											driver.findElement(By.id("txtDroppedItemValue")).clear();
											driver.findElement(By.id("txtDroppedItemValue")).sendKeys(DroppedValue);
										} else {

										}
										if (AE1.equalsIgnoreCase("yes")) {
											driver.findElement(By.id("chkIsAETerm")).click();
										} else {

										}
										if (Concomitant.equalsIgnoreCase("yes")) {
											driver.findElement(By.id("chkIsConcomitantTerm")).click();
										} else {

										}

										GWait.Wait_GetElementById("btnSavetextbox").click();

									} else if (Control.equalsIgnoreCase("radio")) {

										Thread.sleep(500);
										String LabelText = r5.getCell(5, 6 + j).getContents();
										String DisplayLabelText = r5.getCell(6, 6 + j).getContents();
										String Annotation = r5.getCell(7, 6 + j).getContents();
										String HelpText = r5.getCell(8, 6 + j).getContents();
										String RepeatCols = r5.getCell(9, 6 + j).getContents();
										String RepeadDirection = r5.getCell(10, 6 + j).getContents();
										String CodeList = r5.getCell(11, 6 + j).getContents();
										String OtherCodeList = r5.getCell(12, 6 + j).getContents();
										String Mandatory = r5.getCell(13, 6 + j).getContents();
										String Attachment = r5.getCell(14, 6 + j).getContents();
										String Notes = r5.getCell(15, 6 + j).getContents();
										String QRS = r5.getCell(16, 6 + j).getContents();
										String AuditLog = r5.getCell(17, 6 + j).getContents();
										String IsScreenFailure = r5.getCell(18, 6 + j).getContents();
										String ScreenFailureItem = r5.getCell(19, 6 + j).getContents();
										String IsDropped = r5.getCell(20, 6 + j).getContents();
										String DroppedValue = r5.getCell(21, 6 + j).getContents();
										String IsSAE = r5.getCell(22, 6 + j).getContents();
										String SAEValue = r5.getCell(23, 6 + j).getContents();

										Thread.sleep(1500);
										driver.findElement(By.id("radioLabel")).clear();
										driver.findElement(By.id("radioLabel")).sendKeys(LabelText);
										driver.findElement(By.id("txtrdbAnnotationCode")).clear();
										driver.findElement(By.id("txtrdbAnnotationCode")).sendKeys(Annotation);
										Thread.sleep(1000);
										driver.findElement(By.id("radioTxtRepeatColoumns")).clear();
										driver.findElement(By.id("radioTxtRepeatColoumns")).sendKeys(RepeatCols);
										new Select(driver.findElement(By.id("radioCodelist")))
												.selectByVisibleText(CodeList);
										// Thread.sleep(2000);
										scrollToBottom();
										if (DisplayLabelText.equalsIgnoreCase("yes")) {
											driver.findElement(By.id("radioChkDisplay")).click();
										} else {

										}
										Thread.sleep(1000);
										if (HelpText != "") {
											driver.findElement(By.id("radioHelptext")).clear();
											driver.findElement(By.id("radioHelptext")).sendKeys(HelpText);
										} else {

										}
										if (RepeadDirection.equalsIgnoreCase("H")) {
											driver.findElement(By.id("radioRbtnHorizontal")).click();
										} else {

										}
										if (OtherCodeList != "") {
											driver.findElement(By.id("txtrdbOtherCodeValue")).clear();
											driver.findElement(By.id("txtrdbOtherCodeValue")).sendKeys(OtherCodeList);
										} else {

										}

										if (Mandatory.equalsIgnoreCase("yes")) {
											driver.findElement(By.id("chkradioMandatory")).click();
										} else {

										}
										if (Attachment.equalsIgnoreCase("no")) {
											driver.findElement(By.id("chkrdbHasAttachments")).click();
										} else {

										}
										if (Notes.equalsIgnoreCase("no")) {
											driver.findElement(By.id("chkrdbHasNotes")).click();
										} else {

										}
										if (QRS.equalsIgnoreCase("no")) {
											driver.findElement(By.id("chkrdbHasQRS")).click();
										} else {

										}
										if (AuditLog.equalsIgnoreCase("no")) {
											driver.findElement(By.id("chkrdbHasAuditLog")).click();
										} else {

										}
										if (IsScreenFailure.equalsIgnoreCase("yes")) {
											driver.findElement(By.id("chkrdbIsScreenFailureItem")).click();
										} else {

										}
										if (ScreenFailureItem != "") {
											driver.findElement(By.id("txtrdbScreenFailureItemvalue")).clear();
											driver.findElement(By.id("txtrdbScreenFailureItemvalue"))
													.sendKeys(ScreenFailureItem);
										} else {

										}
										if (IsDropped.equalsIgnoreCase("yes")) {
											scrollToBottom();
											driver.findElement(By.id("chkrdbIsDroppedItem")).click();
										} else {

										}
										if (DroppedValue != "") {
											driver.findElement(By.id("txtrdbDroppedItemvalue")).clear();
											driver.findElement(By.id("txtrdbDroppedItemvalue")).sendKeys(DroppedValue);
										} else {

										}
										if (IsSAE.equalsIgnoreCase("yes")) {
											driver.findElement(By.id("chkrdbSAE")).click();
										} else {

										}
										if (SAEValue != "") {
											
											driver.findElement(By.id("txtrdbSAEItemValue")).clear();
											driver.findElement(By.id("txtrdbSAEItemValue")).sendKeys(SAEValue);
										} else {

										}

										/*
										 * File scrFile11 =
										 * ((TakesScreenshot)driver).
										 * getScreenshotAs(OutputType.FILE);
										 * String k11=
										 * "D:\\Projects\\Clinion\\Demo Study\\Screenshots\\Demo\\"+"
										 * Radio - "+j+".png";
										 * FileUtils.copyFile(scrFile11, new
										 * File(k11));
										 */
										WebElement el = GWait.Wait_GetElementById("btnSaveradio");
										scrollToElement(el);
										el.click();
										

									} else if (Control.equalsIgnoreCase("checkbox")) {
										Thread.sleep(500);
										String LabelText = r5.getCell(5, 6 + j).getContents();
										String DisplayLabelText = r5.getCell(6, 6 + j).getContents();
										String Annotation = r5.getCell(7, 6 + j).getContents();
										String HelpText = r5.getCell(8, 6 + j).getContents();
										String RepeatCols = r5.getCell(9, 6 + j).getContents();
										String RepeadDirection = r5.getCell(10, 6 + j).getContents();
										String CodeList = r5.getCell(11, 6 + j).getContents();
										String OtherCodeList = r5.getCell(12, 6 + j).getContents();
										String Attachment = r5.getCell(13, 6 + j).getContents();
										String Notes = r5.getCell(14, 6 + j).getContents();
										String QRS = r5.getCell(15, 6 + j).getContents();
										String AuditLog = r5.getCell(16, 6 + j).getContents();
										String IsScreenFailure = r5.getCell(17, 6 + j).getContents();
										String ScreenFailureItem = r5.getCell(18, 6 + j).getContents();
										String IsDropped = r5.getCell(19, 6 + j).getContents();
										String DroppedValue = r5.getCell(20, 6 + j).getContents();
										String IsSAE = r5.getCell(21, 6 + j).getContents();
										String SAEValue = r5.getCell(22, 6 + j).getContents();
										Thread.sleep(1500);
										driver.findElement(By.id("checkboxLabel")).clear();
										driver.findElement(By.id("checkboxLabel")).sendKeys(LabelText);
										driver.findElement(By.id("txtchkAnnotationCode")).clear();
										driver.findElement(By.id("txtchkAnnotationCode")).sendKeys(Annotation);
										Thread.sleep(1000);
										driver.findElement(By.id("checkboxTxtRepeatColumns")).clear();
										driver.findElement(By.id("checkboxTxtRepeatColumns")).sendKeys(RepeatCols);
										new Select(driver.findElement(By.id("checkboxCodeList")))
												.selectByVisibleText(CodeList);
										// Thread.sleep(2000);

										if (DisplayLabelText.equalsIgnoreCase("yes")) {
											driver.findElement(By.id("checkboxChkDisplay")).click();
										} else {

										}
										if (HelpText != "") {
											driver.findElement(By.id("checkboxHelptext")).clear();
											driver.findElement(By.id("checkboxHelptext")).sendKeys(HelpText);
										} else {

										}
										if (RepeadDirection.equalsIgnoreCase("H")) {
											driver.findElement(By.id("chkRbtnHorizontal")).click();
										} else {

										}
										if (OtherCodeList != "") {
											driver.findElement(By.id("txtchkOtherCodeValue")).clear();
											driver.findElement(By.id("txtchkOtherCodeValue")).sendKeys(OtherCodeList);
										} else {

										}

										if (Attachment.equalsIgnoreCase("no")) {
											driver.findElement(By.id("chkcboxHasAttachments")).click();
										} else {

										}
										if (Notes.equalsIgnoreCase("no")) {
											driver.findElement(By.id("chkcboxHasNotes")).click();
										} else {

										}
										if (QRS.equalsIgnoreCase("no")) {
											driver.findElement(By.id("chkcboxHasQRS")).click();
										} else {

										}
										if (AuditLog.equalsIgnoreCase("no")) {
											driver.findElement(By.id("chkcboxHasAuditLog")).click();
										} else {

										}
										if (IsScreenFailure.equalsIgnoreCase("yes")) {
											driver.findElement(By.id("chkcboxIsScreenfailureItem")).click();
										} else {

										}
										if (ScreenFailureItem != "") {
											driver.findElement(By.id("txtchkScreenFailureItemvalue")).clear();
											driver.findElement(By.id("txtchkScreenFailureItemvalue"))
													.sendKeys(ScreenFailureItem);
										} else {

										}
										if (IsDropped.equalsIgnoreCase("yes")) {
											driver.findElement(By.id("chkcboxisDroppedItem")).click();
										} else {

										}
										if (DroppedValue != "") {
											driver.findElement(By.id("txtchkDroppedItemValue")).clear();
											driver.findElement(By.id("txtchkDroppedItemValue")).sendKeys(DroppedValue);
										} else {

										}
										if (IsSAE.equalsIgnoreCase("yes")) {
											driver.findElement(By.id("chkchkSAE")).click();
										} else {

										}
										if (SAEValue != "") {
											driver.findElement(By.id("txtchkSAEItemValue")).clear();
											driver.findElement(By.id("txtchkSAEItemValue")).sendKeys(SAEValue);
										} else {

										}

										/*
										 * File scrFile11 =
										 * ((TakesScreenshot)driver).
										 * getScreenshotAs(OutputType.FILE);
										 * String k11=
										 * "D:\\Projects\\Clinion\\Demo Study\\Screenshots\\Demo\\"+"
										 * CheckBox - "+j+".png";
										 * FileUtils.copyFile(scrFile11, new
										 * File(k11));
										 */
										scrollToBottom();
										GWait.Wait_GetElementById("btnSavecheckbox").click();

									} else if (Control.equalsIgnoreCase("dropdown")) {
										Thread.sleep(500);
										String LabelText = r5.getCell(5, 6 + j).getContents();
										String DisplayLabelText = r5.getCell(6, 6 + j).getContents();
										String Annotation = r5.getCell(7, 6 + j).getContents();
										String HelpText = r5.getCell(8, 6 + j).getContents();
										String CodeList = r5.getCell(9, 6 + j).getContents();
										String OtherCodeList = r5.getCell(10, 6 + j).getContents();
										String Mandatory = r5.getCell(11, 6 + j).getContents();
										String Attachment = r5.getCell(12, 6 + j).getContents();
										String Notes = r5.getCell(13, 6 + j).getContents();
										String QRS = r5.getCell(14, 6 + j).getContents();
										String AuditLog = r5.getCell(15, 6 + j).getContents();
										String IsScreenFailure = r5.getCell(16, 6 + j).getContents();
										String ScreenFailureItem = r5.getCell(17, 6 + j).getContents();
										String IsDropped = r5.getCell(18, 6 + j).getContents();
										String DroppedValue = r5.getCell(19, 6 + j).getContents();
										Thread.sleep(1500);
										driver.findElement(By.id("drpDownLabel")).clear();
										driver.findElement(By.id("drpDownLabel")).sendKeys(LabelText);
										driver.findElement(By.id("txtdrpAnnotationCode")).clear();
										driver.findElement(By.id("txtdrpAnnotationCode")).sendKeys(Annotation);
										Thread.sleep(1000);
										new Select(driver.findElement(By.id("drpDownCodelist")))
												.selectByVisibleText(CodeList);
										// Thread.sleep(2000);
										if (DisplayLabelText.equalsIgnoreCase("yes")) {
											driver.findElement(By.id("drpChkDisplay")).click();
										} else {

										}
										if (HelpText != "") {
											driver.findElement(By.id("drpDownHelpText")).clear();
											driver.findElement(By.id("drpDownHelpText")).sendKeys(HelpText);
										} else {

										}
										if (OtherCodeList != "") {
											driver.findElement(By.id("txtdrpOtherCodeValue")).clear();
											driver.findElement(By.id("txtdrpOtherCodeValue")).sendKeys(OtherCodeList);
										} else {

										}

										JavascriptExecutor js = (JavascriptExecutor) driver;
										js.executeScript("javascript:window.scrollBy(250,350)");

										if (Mandatory.equalsIgnoreCase("yes")) {
											driver.findElement(By.id("chkdrpDownMandatory")).click();
										} else {

										}
										if (Attachment.equalsIgnoreCase("no")) {
											driver.findElement(By.id("chkdrpHasAttachments")).click();
										} else {

										}
										if (Notes.equalsIgnoreCase("no")) {
											driver.findElement(By.id("chkdrpHasNotes")).click();
										} else {

										}
										if (QRS.equalsIgnoreCase("no")) {
											driver.findElement(By.id("chkdrpHasQRS")).click();
										} else {

										}
										if (AuditLog.equalsIgnoreCase("no")) {
											driver.findElement(By.id("chkdrpHasAuditLog")).click();
										} else {

										}
										if (IsScreenFailure.equalsIgnoreCase("yes")) {
											driver.findElement(By.id("chkdrpIsScreenFailureItem")).click();
										} else {

										}
										if (ScreenFailureItem != "") {
											driver.findElement(By.id("txtdrpScreenFailureItemValue")).clear();
											driver.findElement(By.id("txtdrpScreenFailureItemValue"))
													.sendKeys(ScreenFailureItem);
										} else {

										}
										if (IsDropped.equalsIgnoreCase("yes")) {
											driver.findElement(By.id("chkdrpIsDroppedItem")).click();
										} else {

										}
										if (DroppedValue != "") {
											driver.findElement(By.id("txtdrpDroppedItemValue")).clear();
											driver.findElement(By.id("txtdrpDroppedItemValue")).sendKeys(DroppedValue);
										} else {

										}

										/*
										 * File scrFile11 =
										 * ((TakesScreenshot)driver).
										 * getScreenshotAs(OutputType.FILE);
										 * String k11=
										 * "D:\\Projects\\Clinion\\Demo Study\\Screenshots\\Demo\\"+"
										 * DropDown - "+j+".png";
										 * FileUtils.copyFile(scrFile11, new
										 * File(k11));
										 */
										scrollToBottom();
										GWait.Wait_GetElementById("btnSavedropdown").click();

									}

									else if (Control.equalsIgnoreCase("upload")) {
										/*
										 * 
										 * Thread.sleep(500); String Uploadfile
										 * = r5.getCell(5,6+j).getContents();
										 * String Height =
										 * r5.getCell(6,6+j).getContents();
										 * String Width =
										 * r5.getCell(7,6+j).getContents();
										 * String HelpText =
										 * r5.getCell(8,6+j).getContents();
										 * 
										 * 
										 * //driver.findElement(By.id(
										 * "txtFileUpload")).sendKeys(Uploadfile
										 * ); WebElement
										 * browser=driver.findElement(By.id(
										 * "txtFileUpload")); browser.click();
										 * driver.switchTo().window(
										 * "File Upload"); WebElement
										 * el=driver.findElement(By.name(
										 * "fileName"));
										 * el.sendKeys(Uploadfile);
										 * 
										 * driver.findElement(By.xpath(
										 * "//td[2]/input")).click();
										 * Thread.sleep(2000); StringSelection
										 * abcd = new StringSelection(
										 * "C:\\Users\\Public\\Pictures\\Sample Pictures\\Penguins.jpg"
										 * ); Toolkit.getDefaultToolkit().
										 * getSystemClipboard().setContents(
										 * abcd, null); Robot robot = new
										 * Robot();
										 * 
										 * robot.keyPress(KeyEvent.VK_ENTER);
										 * robot.keyRelease(KeyEvent.VK_ENTER);
										 * robot.keyPress(KeyEvent.VK_CONTROL);
										 * robot.keyPress(KeyEvent.VK_V);
										 * robot.keyRelease(KeyEvent.VK_V);
										 * robot.keyRelease(KeyEvent.VK_CONTROL)
										 * ; robot.keyPress(KeyEvent.VK_ENTER);
										 * robot.keyRelease(KeyEvent.VK_ENTER);
										 * 
										 * //Runtime.getRuntime().exec(
										 * Uploadfile);
										 * 
										 * 
										 * 
										 * 
										 * driver.findElement(By.id(
										 * "txtUploadImageHeight")).clear();
										 * driver.findElement(By.id(
										 * "txtUploadImageHeight")).sendKeys(
										 * Height); driver.findElement(By.id(
										 * "txtUploadImageWidth")).clear();
										 * driver.findElement(By.id(
										 * "txtUploadImageWidth")).sendKeys(
										 * Width);
										 * 
										 * 
										 * 
										 * if(HelpText!="") {
										 * driver.findElement(By.id(
										 * "txtUploadHelpText")).clear();
										 * driver.findElement(By.id(
										 * "txtUploadHelpText")).sendKeys(Height
										 * ); } else {
										 * 
										 * }
										 * 
										 * driver.findElement(By.id(
										 * "btnSavetextbox")).click();
										 * 
										 * 
										 */}
								}
							}
						}

					}

				}
				Thread.sleep(2000);
				GWait.Wait_GetElementById("btnPnlSave").click();
				scrollToBottom();
				Thread.sleep(5000);
				WebElement goback = GWait.Wait_GetElementByCSS("#ctl00_ContentPlaceHolder1_ctl00_btnGoBack");
				scrollToElement(goback);
				goback.click();

			} else {
				driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_rdbPnlLayout_1")).click();
				Thread.sleep(1000);
				new Select(driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_drpvrtColumns")))
						.selectByVisibleText(RepColumn);
				driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_txtColumnWidths")).sendKeys(ColWidth);

				if (PanelType.equalsIgnoreCase("lab")) {
					// System.out.println("Lab Satisfied");
					driver.findElement(By.id("ctl18_rdbPnlType_1")).click();
				} else {
					// System.out.println("Not Satisfied");
				}
				if (SF.equalsIgnoreCase("yes")) {
					driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_chkIsScreenfailure")).click();
				} else {

				}
				if (Term.equalsIgnoreCase("yes")) {
					driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_chkIsTermination")).click();
				} else {

				}
				if (AE.equalsIgnoreCase("yes")) {
					driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_chkIsAdverseEvent")).click();
				} else {

				}
				Thread.sleep(2000);
				if (Concom.equalsIgnoreCase("yes")) {
					driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_chkIsConcomitant")).click();
				} else {

				}
				for (int j = 1; j <= Count1 - 7; j++) {
					String AC = r5.getCell(1, 6 + j).getContents();
					String Control = r5.getCell(2, 6 + j).getContents();
					String Position = r5.getCell(3, 6 + j).getContents();
					Position = Position.replace(",", "");
					Position = "vtrepeatTableLayouttd" + Position;
					// System.out.println(Position);
					if (AC.equalsIgnoreCase(AnnotationCode.toLowerCase())) {
						int y = 20;
						String JSE = "window.scrollBy(0," + y + ")";
						// String Neg = "window.scrollBy(0,-"+y+")";
						// ((JavascriptExecutor) driver).executeScript(Neg, "");
						((JavascriptExecutor) driver).executeScript(JSE, "");
						Thread.sleep(500);
						WebElement draggable = driver.findElement(By.id(Control));
						WebElement droppable = driver.findElement(By.id(Position));
						new Actions(driver).dragAndDrop(draggable, droppable).build().perform();
						Thread.sleep(500);

					}
				}
				// Thread.sleep(20000);
				for (int j = 1; j <= Count1 - 7; j++) {
					scrollToBottom();
					String AC = r5.getCell(1, 6 + j).getContents();
					String Control = r5.getCell(2, 6 + j).getContents();
					String Position = r5.getCell(3, 6 + j).getContents();
					Position = Position.replace(",", "");
					Position = "vtrepeatTableLayouttd" + Position;
					// System.out.println(Position);

					if (AC.equalsIgnoreCase(AnnotationCode.toLowerCase())) {
						if (Control.equalsIgnoreCase("rowmerger")) {

						} else {
							if (Control.equalsIgnoreCase("rowmerger")) {

							} else {

								String Css = "#" + Position
										+ " > div.ctrl_placeholder > div.ctrl_toolbar > span > a.ctrlVrtRptEdit > img";
								// System.out.println(Css);
								Thread.sleep(1500);
								scrollToBottom();
								driver.findElement(By.cssSelector(Css)).click();

								if (Control.equalsIgnoreCase("label")) {
									scrollToBottom();
									String LabelText = r5.getCell(5, 6 + j).getContents();
									String DisplayLabelText = r5.getCell(6, 6 + j).getContents();
									String Align = r5.getCell(7, 6 + j).getContents();
									String IsBold = r5.getCell(8, 6 + j).getContents();
									String Attachments = r5.getCell(9, 6 + j).getContents();
									String Notes = r5.getCell(10, 6 + j).getContents();
									String QRS = r5.getCell(11, 6 + j).getContents();
									String AuditLog = r5.getCell(12, 6 + j).getContents();
									Thread.sleep(1500);
									driver.findElement(By.id("txtLabelText")).clear();
									driver.findElement(By.id("txtLabelText")).sendKeys(LabelText);
									if (DisplayLabelText.equalsIgnoreCase("yes")) {
										driver.findElement(By.id("lblChkDisplay")).click();
									} else {
									}
									if (Align.equalsIgnoreCase("")) {
									} else {
										new Select(driver.findElement(By.id("sellblAlign"))).selectByVisibleText(Align);
									}
									if (IsBold.equalsIgnoreCase("yes")) {
										driver.findElement(By.id("chklblIsBold")).click();
									} else {

									}
									if (Attachments.equalsIgnoreCase("yes")) {
										driver.findElement(By.id("chklblHasAttachments")).click();
									} else {

									}
									if (Notes.equalsIgnoreCase("yes")) {
										driver.findElement(By.id("chklblHasNotes")).click();
									} else {

									}
									if (QRS.equalsIgnoreCase("yes")) {
										driver.findElement(By.id("chklblHasQRS")).click();
									} else {

									}
									/*
									 * if(AuditLog.equalsIgnoreCase("yes")) {
									 * driver.findElement(By.id(
									 * "chklblHasAuditLog")).click(); } else {
									 * 
									 * }
									 */

									/*
									 * File scrFile11 =
									 * ((TakesScreenshot)driver).getScreenshotAs
									 * (OutputType.FILE); String k11=
									 * "D:\\Projects\\Clinion\\Demo Study\\Screenshots\\Demo\\"+"
									 * Label - "+j+".png";
									 * FileUtils.copyFile(scrFile11, new
									 * File(k11));
									 */

									driver.findElement(By.id("btnSavelabel")).click();

								}

								else if (Control.equalsIgnoreCase("textbox")) {
									Thread.sleep(500);
									String LabelText = r5.getCell(5, 6 + j).getContents();
									String DisplayLabelText = r5.getCell(6, 6 + j).getContents();
									String Annotation = r5.getCell(7, 6 + j).getContents();
									String HelpText = r5.getCell(8, 6 + j).getContents();
									String DataType = r5.getCell(9, 6 + j).getContents();
									String MaxLength = r5.getCell(10, 6 + j).getContents();
									String Multiline = r5.getCell(11, 6 + j).getContents();
									String Mandatory = r5.getCell(12, 6 + j).getContents();
									String Attachment = r5.getCell(13, 6 + j).getContents();
									String Notes = r5.getCell(14, 6 + j).getContents();
									String QRS = r5.getCell(15, 6 + j).getContents();
									String Auditlog = r5.getCell(16, 6 + j).getContents();
									String MultiInput = r5.getCell(17, 6 + j).getContents();
									String NoOfMultiBx = r5.getCell(18, 6 + j).getContents();
									String MultiIPSep = r5.getCell(19, 6 + j).getContents();
									String MultiBxMaxLeng = r5.getCell(20, 6 + j).getContents();
									String ScreenFail = r5.getCell(21, 6 + j).getContents();
									String ScreenFailValue = r5.getCell(22, 6 + j).getContents();
									String Dropped = r5.getCell(23, 6 + j).getContents();
									String DroppedValue = r5.getCell(24, 6 + j).getContents();
									String AE1 = r5.getCell(25, 6 + j).getContents();
									String Concomitant = r5.getCell(26, 6 + j).getContents();
									Thread.sleep(1500);
									driver.findElement(By.id("txtTextboxLabel")).clear();
									driver.findElement(By.id("txtTextboxLabel")).sendKeys(LabelText);

									if (DisplayLabelText.equalsIgnoreCase("yes")) {
										driver.findElement(By.id("chkTxtDisplay")).click();
									} else {

									}
									driver.findElement(By.id("txtTextboxAnnotation")).clear();
									driver.findElement(By.id("txtTextboxAnnotation")).sendKeys(Annotation);
									new Select(driver.findElement(By.id("txtTextboxDataType")))
											.selectByVisibleText(DataType);
									if (HelpText != "") {
										driver.findElement(By.id("txtTextboxHelpText")).clear();
										driver.findElement(By.id("txtTextboxHelpText")).sendKeys(HelpText);
									} else {

									}
									if (MaxLength != "") {
										driver.findElement(By.id("txtTextboxMaxlen")).clear();
										driver.findElement(By.id("txtTextboxMaxlen")).sendKeys(MaxLength);
									}
									if (Multiline.equalsIgnoreCase("yes")) {
										driver.findElement(By.id("chktxtIsMultiLine")).click();
									} else {

									}
									if (Mandatory.equalsIgnoreCase("yes")) {
										driver.findElement(By.id("chktextboxMandatory")).click();
									} else {

									}
									if (Attachment.equalsIgnoreCase("no")) {
										driver.findElement(By.id("chktxtHasAttachments")).click();
									} else {

									}
									if (Notes.equalsIgnoreCase("no")) {
										driver.findElement(By.id("chktxtHasNotes")).click();
									} else {

									}
									if (QRS.equalsIgnoreCase("no")) {
										driver.findElement(By.id("chktxtHasQRS")).click();
									} else {

									}
									if (Auditlog.equalsIgnoreCase("no")) {
										driver.findElement(By.id("chktxtHasAuditLog")).click();
									} else {

									}
									if (MultiInput.equalsIgnoreCase("yes")) {
										driver.findElement(By.id("chktxtHasMultiInputOption")).click();
									} else {

									}
									if (NoOfMultiBx != "") {
										driver.findElement(By.id("txtNoofMultinputBoxes")).clear();
										driver.findElement(By.id("txtNoofMultinputBoxes")).sendKeys(NoOfMultiBx);
									} else {
										// System.out.println("No of Muliti
										// input null");
									}
									if (MultiIPSep != "") {
										driver.findElement(By.id("txtmultiinputseparator")).clear();
										driver.findElement(By.id("txtmultiinputseparator")).sendKeys(MultiIPSep);
									} else {
										// System.out.println("Muliti Sep
										// null");
									}
									if (MultiBxMaxLeng != "") {
										driver.findElement(By.id("txtmultiboxesmaxlength")).clear();
										driver.findElement(By.id("txtmultiboxesmaxlength")).sendKeys(MultiBxMaxLeng);
									} else {

									}
									if (ScreenFail.equalsIgnoreCase("yes")) {
										driver.findElement(By.id("chktxtIsScreenFailureItem")).click();
									} else {

									}
									if (ScreenFailValue != "") {
										driver.findElement(By.id("txtScreenFailureItemValue")).clear();
										driver.findElement(By.id("txtScreenFailureItemValue"))
												.sendKeys(ScreenFailValue);
									} else {

									}
									if (Dropped.equalsIgnoreCase("yes")) {
										driver.findElement(By.id("chkIsDroppedItem")).click();
									} else {

									}
									if (DroppedValue != "") {
										driver.findElement(By.id("txtDroppedItemValue")).clear();
										driver.findElement(By.id("txtDroppedItemValue")).sendKeys(DroppedValue);
									} else {

									}
									if (AE1.equalsIgnoreCase("yes")) {
										driver.findElement(By.id("chkIsAETerm")).click();
									} else {

									}
									if (Concomitant.equalsIgnoreCase("yes")) {
										driver.findElement(By.id("chkIsConcomitantTerm")).click();
									} else {

									}

									/*
									 * File scrFile11 =
									 * ((TakesScreenshot)driver).getScreenshotAs
									 * (OutputType.FILE); String k11=
									 * "D:\\Projects\\Clinion\\Demo Study\\Screenshots\\Demo\\"+"
									 * TextBox - "+j+".png";
									 * FileUtils.copyFile(scrFile11, new
									 * File(k11));
									 */

									driver.findElement(By.id("btnSavetextbox")).click();

								} else if (Control.equalsIgnoreCase("radio")) {
									Thread.sleep(500);
									String LabelText = r5.getCell(5, 6 + j).getContents();
									String DisplayLabelText = r5.getCell(6, 6 + j).getContents();
									String Annotation = r5.getCell(7, 6 + j).getContents();
									String HelpText = r5.getCell(8, 6 + j).getContents();
									String RepeatCols = r5.getCell(9, 6 + j).getContents();
									String RepeadDirection = r5.getCell(10, 6 + j).getContents();
									String CodeList = r5.getCell(11, 6 + j).getContents();
									String OtherCodeList = r5.getCell(12, 6 + j).getContents();
									String Mandatory = r5.getCell(13, 6 + j).getContents();
									String Attachment = r5.getCell(14, 6 + j).getContents();
									String Notes = r5.getCell(15, 6 + j).getContents();
									String QRS = r5.getCell(16, 6 + j).getContents();
									String AuditLog = r5.getCell(17, 6 + j).getContents();
									String IsScreenFailure = r5.getCell(18, 6 + j).getContents();
									String ScreenFailureItem = r5.getCell(19, 6 + j).getContents();
									String IsDropped = r5.getCell(20, 6 + j).getContents();
									String DroppedValue = r5.getCell(21, 6 + j).getContents();
									String IsSAE = r5.getCell(22, 6 + j).getContents();
									String SAEValue = r5.getCell(23, 6 + j).getContents();

									Thread.sleep(1500);
									driver.findElement(By.id("radioLabel")).clear();
									driver.findElement(By.id("radioLabel")).sendKeys(LabelText);
									driver.findElement(By.id("txtrdbAnnotationCode")).clear();
									driver.findElement(By.id("txtrdbAnnotationCode")).sendKeys(Annotation);
									Thread.sleep(1000);
									driver.findElement(By.id("radioTxtRepeatColoumns")).clear();
									driver.findElement(By.id("radioTxtRepeatColoumns")).sendKeys(RepeatCols);
									new Select(driver.findElement(By.id("radioCodelist")))
											.selectByVisibleText(CodeList);
									// Thread.sleep(2000);

									if (DisplayLabelText.equalsIgnoreCase("yes")) {
										driver.findElement(By.id("radioChkDisplay")).click();
									} else {

									}
									if (HelpText != "") {
										driver.findElement(By.id("radioHelptext")).clear();
										driver.findElement(By.id("radioHelptext")).sendKeys(HelpText);
									} else {

									}
									if (RepeadDirection.equalsIgnoreCase("H")) {
										driver.findElement(By.id("radioRbtnHorizontal")).click();
									} else {

									}
									if (OtherCodeList != "") {
										driver.findElement(By.id("txtrdbOtherCodeValue")).clear();
										driver.findElement(By.id("txtrdbOtherCodeValue")).sendKeys(OtherCodeList);
									} else {

									}
									if (Mandatory.equalsIgnoreCase("yes")) {
										driver.findElement(By.id("chkradioMandatory")).click();
									} else {

									}
									if (Attachment.equalsIgnoreCase("no")) {
										driver.findElement(By.id("chkrdbHasAttachments")).click();
									} else {

									}
									if (Notes.equalsIgnoreCase("no")) {
										driver.findElement(By.id("chkrdbHasNotes")).click();
									} else {

									}
									if (QRS.equalsIgnoreCase("no")) {
										driver.findElement(By.id("chkrdbHasQRS")).click();
									} else {

									}
									if (AuditLog.equalsIgnoreCase("no")) {
										driver.findElement(By.id("chkrdbHasAuditLog")).click();
									} else {
									}
									if (IsScreenFailure.equalsIgnoreCase("yes")) {
										driver.findElement(By.id("chkrdbIsScreenFailureItem")).click();
									} else {

									}
									if (ScreenFailureItem != "") {
										driver.findElement(By.id("txtrdbScreenFailureItemvalue")).clear();
										driver.findElement(By.id("txtrdbScreenFailureItemvalue"))
												.sendKeys(ScreenFailureItem);
									} else {

									}
									if (IsDropped.equalsIgnoreCase("yes")) {
										driver.findElement(By.id("chkrdbIsDroppedItem")).click();
									} else {

									}
									if (DroppedValue != "") {
										driver.findElement(By.id("txtrdbDroppedItemvalue")).clear();
										driver.findElement(By.id("txtrdbDroppedItemvalue")).sendKeys(DroppedValue);
									} else {

									}
									if (IsSAE.equalsIgnoreCase("yes")) {
										driver.findElement(By.id("chkrdbSAE")).click();
									} else {

									}
									if (SAEValue != "") {
										driver.findElement(By.id("txtrdbSAEItemValue")).clear();
										driver.findElement(By.id("txtrdbSAEItemValue")).sendKeys(SAEValue);
									} else {

									}

									/*
									 * File scrFile11 =
									 * ((TakesScreenshot)driver).getScreenshotAs
									 * (OutputType.FILE); String k11=
									 * "D:\\Projects\\Clinion\\Demo Study\\Screenshots\\Demo\\"+"
									 * Radio - "+j+".png";
									 * FileUtils.copyFile(scrFile11, new
									 * File(k11));
									 */

									driver.findElement(By.id("btnSaveradio")).click();

								} else if (Control.equalsIgnoreCase("checkbox")) {
									Thread.sleep(500);
									String LabelText = r5.getCell(5, 6 + j).getContents();
									String DisplayLabelText = r5.getCell(6, 6 + j).getContents();
									String Annotation = r5.getCell(7, 6 + j).getContents();
									String HelpText = r5.getCell(8, 6 + j).getContents();
									String RepeatCols = r5.getCell(9, 6 + j).getContents();
									String RepeadDirection = r5.getCell(10, 6 + j).getContents();
									String CodeList = r5.getCell(11, 6 + j).getContents();
									String OtherCodeList = r5.getCell(12, 6 + j).getContents();
									String Attachment = r5.getCell(13, 6 + j).getContents();
									String Notes = r5.getCell(14, 6 + j).getContents();
									String QRS = r5.getCell(15, 6 + j).getContents();
									String AuditLog = r5.getCell(16, 6 + j).getContents();
									String IsScreenFailure = r5.getCell(17, 6 + j).getContents();
									String ScreenFailureItem = r5.getCell(18, 6 + j).getContents();
									String IsDropped = r5.getCell(19, 6 + j).getContents();
									String DroppedValue = r5.getCell(20, 6 + j).getContents();
									String IsSAE = r5.getCell(21, 6 + j).getContents();
									String SAEValue = r5.getCell(22, 6 + j).getContents();
									Thread.sleep(1500);
									driver.findElement(By.id("checkboxLabel")).clear();
									driver.findElement(By.id("checkboxLabel")).sendKeys(LabelText);
									driver.findElement(By.id("txtchkAnnotationCode")).clear();
									driver.findElement(By.id("txtchkAnnotationCode")).sendKeys(Annotation);
									Thread.sleep(1000);
									driver.findElement(By.id("checkboxTxtRepeatColumns")).clear();
									driver.findElement(By.id("checkboxTxtRepeatColumns")).sendKeys(RepeatCols);
									new Select(driver.findElement(By.id("checkboxCodeList")))
											.selectByVisibleText(CodeList);
									// Thread.sleep(2000);

									if (DisplayLabelText.equalsIgnoreCase("yes")) {
										driver.findElement(By.id("checkboxChkDisplay")).click();
									} else {

									}
									if (HelpText != "") {
										driver.findElement(By.id("checkboxHelptext")).clear();
										driver.findElement(By.id("checkboxHelptext")).sendKeys(HelpText);
									} else {

									}
									if (RepeadDirection.equalsIgnoreCase("H")) {
										driver.findElement(By.id("chkRbtnHorizontal")).click();
									} else {

									}
									if (OtherCodeList != "") {
										driver.findElement(By.id("txtchkOtherCodeValue")).clear();
										driver.findElement(By.id("txtchkOtherCodeValue")).sendKeys(OtherCodeList);
									} else {

									}

									if (Attachment.equalsIgnoreCase("no")) {
										driver.findElement(By.id("chkcboxHasAttachments")).click();
									} else {

									}
									if (Notes.equalsIgnoreCase("no")) {
										driver.findElement(By.id("chkcboxHasNotes")).click();
									} else {

									}
									if (QRS.equalsIgnoreCase("no")) {
										driver.findElement(By.id("chkcboxHasQRS")).click();
									} else {

									}
									if (AuditLog.equalsIgnoreCase("no")) {
										driver.findElement(By.id("chkcboxHasAuditLog")).click();
									} else {

									}
									if (IsScreenFailure.equalsIgnoreCase("yes")) {
										driver.findElement(By.id("chkcboxIsScreenfailureItem")).click();
									} else {

									}
									if (ScreenFailureItem != "") {
										driver.findElement(By.id("txtchkScreenFailureItemvalue")).clear();
										driver.findElement(By.id("txtchkScreenFailureItemvalue"))
												.sendKeys(ScreenFailureItem);
									} else {

									}
									if (IsDropped.equalsIgnoreCase("yes")) {
										driver.findElement(By.id("chkcboxisDroppedItem")).click();
									} else {

									}
									if (DroppedValue != "") {
										driver.findElement(By.id("txtchkDroppedItemValue")).clear();
										driver.findElement(By.id("txtchkDroppedItemValue")).sendKeys(DroppedValue);
									} else {

									}
									if (IsSAE.equalsIgnoreCase("yes")) {
										driver.findElement(By.id("chkchkSAE")).click();
									} else {

									}
									if (SAEValue != "") {
										driver.findElement(By.id("txtchkSAEItemValue")).clear();
										driver.findElement(By.id("txtchkSAEItemValue")).sendKeys(SAEValue);
									} else {

									}

									/*
									 * File scrFile11 =
									 * ((TakesScreenshot)driver).getScreenshotAs
									 * (OutputType.FILE); String k11=
									 * "D:\\Projects\\Clinion\\Demo Study\\Screenshots\\Demo\\"+"
									 * CheckBox - "+j+".png";
									 * FileUtils.copyFile(scrFile11, new
									 * File(k11));
									 */

									driver.findElement(By.id("btnSavecheckbox")).click();

								} else if (Control.equalsIgnoreCase("dropdown")) {
									Thread.sleep(500);
									String LabelText = r5.getCell(5, 6 + j).getContents();
									String DisplayLabelText = r5.getCell(6, 6 + j).getContents();
									String Annotation = r5.getCell(7, 6 + j).getContents();
									String HelpText = r5.getCell(8, 6 + j).getContents();
									String CodeList = r5.getCell(9, 6 + j).getContents();
									String OtherCodeList = r5.getCell(10, 6 + j).getContents();
									String Mandatory = r5.getCell(11, 6 + j).getContents();
									String Attachment = r5.getCell(12, 6 + j).getContents();
									String Notes = r5.getCell(13, 6 + j).getContents();
									String QRS = r5.getCell(14, 6 + j).getContents();
									String AuditLog = r5.getCell(15, 6 + j).getContents();
									String IsScreenFailure = r5.getCell(16, 6 + j).getContents();
									String ScreenFailureItem = r5.getCell(17, 6 + j).getContents();
									String IsDropped = r5.getCell(18, 6 + j).getContents();
									String DroppedValue = r5.getCell(19, 6 + j).getContents();
									Thread.sleep(1500);
									driver.findElement(By.id("drpDownLabel")).clear();
									driver.findElement(By.id("drpDownLabel")).sendKeys(LabelText);
									driver.findElement(By.id("txtdrpAnnotationCode")).clear();
									driver.findElement(By.id("txtdrpAnnotationCode")).sendKeys(Annotation);
									Thread.sleep(1000);
									new Select(driver.findElement(By.id("drpDownCodelist")))
											.selectByVisibleText(CodeList);
									Thread.sleep(1000);
									if (DisplayLabelText.equalsIgnoreCase("yes")) {
										driver.findElement(By.id("drpChkDisplay")).click();
									} else {

									}
									if (HelpText != "") {
										driver.findElement(By.id("drpDownHelpText")).clear();
										driver.findElement(By.id("drpDownHelpText")).sendKeys(HelpText);
									} else {

									}
									if (OtherCodeList != "") {
										driver.findElement(By.id("txtdrpOtherCodeValue")).clear();
										driver.findElement(By.id("txtdrpOtherCodeValue")).sendKeys(OtherCodeList);
									} else {

									}
									if (Mandatory.equalsIgnoreCase("yes")) {
										driver.findElement(By.id("chkdrpDownMandatory")).click();
									} else {

									}
									if (Attachment.equalsIgnoreCase("no")) {
										driver.findElement(By.id("chkdrpHasAttachments")).click();
									} else {

									}
									if (Notes.equalsIgnoreCase("no")) {
										driver.findElement(By.id("chkdrpHasNotes")).click();
									} else {

									}
									if (QRS.equalsIgnoreCase("no")) {
										driver.findElement(By.id("chkdrpHasQRS")).click();
									} else {

									}
									if (AuditLog.equalsIgnoreCase("no")) {
										driver.findElement(By.id("chkdrpHasAuditLog")).click();
									} else {

									}
									if (IsScreenFailure.equalsIgnoreCase("yes")) {
										driver.findElement(By.id("chkdrpIsScreenFailureItem")).click();
									} else {

									}
									if (ScreenFailureItem != "") {
										driver.findElement(By.id("txtdrpScreenFailureItemValue")).clear();
										driver.findElement(By.id("txtdrpScreenFailureItemValue"))
												.sendKeys(ScreenFailureItem);
									} else {

									}
									if (IsDropped.equalsIgnoreCase("yes")) {
										driver.findElement(By.id("chkdrpIsDroppedItem")).click();
									} else {

									}
									if (DroppedValue != "") {
										driver.findElement(By.id("txtdrpDroppedItemValue")).clear();
										driver.findElement(By.id("txtdrpDroppedItemValue")).sendKeys(DroppedValue);
									} else {

									}

									/*
									 * File scrFile11 =
									 * ((TakesScreenshot)driver).getScreenshotAs
									 * (OutputType.FILE); String k11=
									 * "D:\\Projects\\Clinion\\Demo Study\\Screenshots\\Demo\\"+"
									 * DropDown - "+j+".png";
									 * FileUtils.copyFile(scrFile11, new
									 * File(k11));
									 */
									driver.findElement(By.id("btnSavedropdown")).click();

								}

							}
						}
					}

				}
				Thread.sleep(2000);
				 driver.findElement(By.id("btnPnlSave")).click();
				Thread.sleep(2000);
				driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_btnGoBack")).click();

			}
			 System.out.println("Panel Layout end");
		}

	}

	public void AssignPannels()throws Exception
	{
		FileInputStream fi = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/Study Creation.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r7 = wb.getSheet("Pages");

		int PageCount = r7.getRows() - 1;

		for (int i = 1; i <= PageCount; i++) {

			Thread.sleep(1000);
			GWait.Wait_GetElementByCSS("td > a > img").click();
			Thread.sleep(1000);
			GWait.Wait_GetElementByCSS("a.managepageclick > span > b").click();
			Thread.sleep(2000);
			driver.switchTo().frame(0);

			int j = i + 1;
			if (j<=9) {
				String Page = "gvPages_ctl0" + j + "_lnkManagePanels";
				GWait.Wait_GetElementById(Page).click();
				GWait.Wait_GetElementById("imgAddPanel").click();
			} else if(j>=10) {
				String Page = "gvPages_ctl" + j + "_lnkManagePanels";
				GWait.Wait_GetElementById(Page).click();
				GWait.Wait_GetElementById("imgAddPanel").click();
			}
			

			FileInputStream fi1 = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/resources/Study Creation.xls");
			Workbook wb1 = Workbook.getWorkbook(fi1);
			Sheet r71 = wb1.getSheet("Assign Panels");
			int AssCount = r71.getRows() - 1;
			System.out.println(AssCount);
			
			for (int k = 1; k <= AssCount; k++) {
				
				String Pnl = r71.getCell(i - 1, k).getContents();
				// System.out.println("Panel is "+Pnl);
				if (Pnl.equals("")) {
					// System.out.println("Pnel empty");
				} else {
					WebElement PanelData = GWait.Wait_GetElementByXpath("//div/div/div/div[4]/div[2]/table/tbody/tr["+tr+"]/td[2]");
					System.out.println(PanelData.getText());
					System.out.println("Application Data: "+Pnl);
					if (Pnl.equals(PanelData.getText())) {
						Thread.sleep(2000);
						//ctl18_grdPanels_ctl02_chkPanelCheckbox
						String a = "ctl18_grdPanels_ctl0";
						String a1 = "ctl18_grdPanels_ctl";
						String b = "_chkPanelCheckbox";
						String c = a+tr+b;
						String d = a1+tr+b; 
						System.out.println("Test xpath"+c);
						if (j<=9) {
							GWait.Wait_GetElementById(c, 120).click();
						} else if (j>=10) {
							GWait.Wait_GetElementById(d, 120).click();
						}
					}
				}
				tr++;
			}
			GWait.Wait_GetElementById("selectpanels").click();

		}
		Thread.sleep(4000);
	}
	
}
