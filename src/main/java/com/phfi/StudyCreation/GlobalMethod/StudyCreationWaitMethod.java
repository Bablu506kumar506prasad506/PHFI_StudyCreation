package com.phfi.StudyCreation.GlobalMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class StudyCreationWaitMethod {

	public static WebDriver driver;

	public StudyCreationWaitMethod(WebDriver driver) {
		StudyCreationWaitMethod.driver = driver;
	}

	public void LoadGif() {

		By loadingImage = By.className("loaderGif");
		WebDriverWait driverWait = new WebDriverWait(driver, 120);
		driverWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingImage));

	}

	public void flyInOut() {

		By loadingImage = By.className("toast-success toast ng-trigger ng-trigger-flyInOut");
		WebDriverWait driverWait = new WebDriverWait(driver, 240);
		driverWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingImage));

	}

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public WebElement Wait_GetElementByXpath(final String elementXPath1, Integer... Timeinsec) {
		Integer time = Timeinsec.length > 0 ? Timeinsec[0] : 180;
		Wait wait = new FluentWait(driver).withTimeout(time, TimeUnit.SECONDS).pollingEvery(10, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		WebElement elementXpath = (WebElement) wait.until(new Function() {
			public Object apply(Object arg0) {
				// System.out.println("test"+elementXPath1);
				return driver.findElement(By.xpath(elementXPath1));
			}
		});
		// LoadGif();
		// blockUI();
		flyInOut();
		return elementXpath;
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public WebElement Wait_GetElementById(final String elementID, Integer... Timeinsec) {
		Integer time = Timeinsec.length > 0 ? Timeinsec[0] : 180;
		Wait wait = new FluentWait(driver).withTimeout(time, TimeUnit.SECONDS).pollingEvery(10, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		WebElement elementIDs = (WebElement) wait.until(new Function() {
			public Object apply(Object arg0) {
				// System.out.println("test" + elementID);
				return driver.findElement(By.id(elementID));
			}
		});
		flyInOut();
		return elementIDs;
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public WebElement Wait_GetElementByName(final String elementName) {
		Wait wait = new FluentWait(driver).withTimeout(30, TimeUnit.SECONDS).pollingEvery(2, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		WebElement elementNames = (WebElement) wait.until(new Function() {
			public Object apply(Object arg0) {
				// System.out.println("test" + elementName);
				return driver.findElement(By.name(elementName));
			}
		});
		flyInOut();
		return elementNames;

	}

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public WebElement Wait_GetElementByClassName(final String elementClassName) {
		Wait wait = new FluentWait(driver).withTimeout(30, TimeUnit.SECONDS).pollingEvery(2, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		WebElement elementCN = (WebElement) wait.until(new Function() {
			public Object apply(Object arg0) {
				// System.out.println("test" + elementClassName);
				return driver.findElement(By.className(elementClassName));
			}
		});
		flyInOut();
		return elementCN;
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public WebElement Wait_GetElementByCSS(final String elementCSS) {
		Wait wait = new FluentWait(driver).withTimeout(120, TimeUnit.SECONDS).pollingEvery(2, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		WebElement elementcss = (WebElement) wait.until(new Function() {
			public Object apply(Object arg0) {
				// System.out.println("test" + elementCSS);
				return driver.findElement(By.cssSelector(elementCSS));
			}
		});
		flyInOut();
		return elementcss;
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public WebElement Wait_GetElementByLinkText(final String elementLinkText) {
		Wait wait = new FluentWait(driver).withTimeout(60, TimeUnit.SECONDS).pollingEvery(2, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		WebElement elementLT = (WebElement) wait.until(new Function() {
			public Object apply(Object arg0) {
				// System.out.println("test" + elementLinkText);
				return driver.findElement(By.linkText(elementLinkText));
			}
		});
		flyInOut();
		return elementLT;
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public WebElement Wait_GetElementByTagName(final String elementTagName) {
		Wait wait = new FluentWait(driver).withTimeout(30, TimeUnit.SECONDS).pollingEvery(2, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		WebElement elementTN = (WebElement) wait.until(new Function() {
			public Object apply(Object arg0) {
				// System.out.println("test" + elementTagName);
				return driver.findElement(By.tagName(elementTagName));
			}
		});
		flyInOut();
		return elementTN;
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public WebElement Wait_GetElementByPartialLT(final String elementPartialLT) {
		Wait wait = new FluentWait(driver).withTimeout(30, TimeUnit.SECONDS).pollingEvery(2, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		WebElement elementPLT = (WebElement) wait.until(new Function() {
			public Object apply(Object arg0) {
				// System.out.println("test" + elementPartialLT);
				return driver.findElement(By.partialLinkText(elementPartialLT));
			}
		});
		flyInOut();
		return elementPLT;
	}

	public void blockUI() {

		By loadingImage = By.className("blockUI blockOverlay");
		WebDriverWait driverWait = new WebDriverWait(driver, 200);
		driverWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingImage));

	}
}
