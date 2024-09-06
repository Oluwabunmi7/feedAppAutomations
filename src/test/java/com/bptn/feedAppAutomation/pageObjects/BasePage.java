package com.bptn.feedAppAutomation.pageObjects;

import java.time.Duration;

import java.io.File;
import java.net.URL;
import java.net.URI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bptn.feedAppAutomation.provider.ResourceProvider;
import com.bptn.feedAppAutomation.web.DriverManager;

public class BasePage {

	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	DriverManager driverManager;

	@Autowired
	ResourceProvider provider;
	
	By xPathCropBtn = By.xpath("//button[text()='Crop']");
	By xPathFileUpload = By.xpath("//input[@type='file']");
	By classCropperModal = By.className("ReactModal__Content");

	public String getMessage(By xPathMessage) {
		try {
			WebDriverWait wait = new WebDriverWait(this.driverManager.getDriver(), Duration.ofSeconds(10));
			WebElement messageBadgeElement = wait.until(ExpectedConditions.presenceOfElementLocated(xPathMessage));

			Thread.sleep(2000);

			return messageBadgeElement.getText();
		} catch (Exception ex) {
			this.logger.error(ex.getMessage(), ex);
		}

		return null;
	}

	public String getPageRoute() {
		try {
			Thread.sleep(2000);
			return new URI(this.driverManager.getDriver().getCurrentUrl()).getPath();

		} catch (Exception ex) {
			this.logger.error(ex.getMessage(), ex);
		}

		return null;
	}
	
	public void openImageSelector(String imagePath) {

		WebDriverWait wait = new WebDriverWait(this.driverManager.getDriver(), Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(this.xPathFileUpload));

		URL resourceUrl = BasePage.class.getResource(imagePath);

		if (resourceUrl != null) {
			String filePath = new File(resourceUrl.getFile()).getAbsolutePath();
				
			this.driverManager.getDriver()
				            .findElement(this.xPathFileUpload)
				            .sendKeys(filePath);
		}
		else {
			throw new RuntimeException("Image not Found :" + imagePath);
		}
	}
	
	public boolean validateCropperModal(){
	    WebDriverWait wait = new WebDriverWait(this.driverManager.getDriver(), Duration.ofSeconds(10));
	    WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(this.classCropperModal));

	    return element.isDisplayed();
	}
	
	public void clickCrop() {
	    this.driverManager.getDriver()
	                .findElement(this.xPathCropBtn)
	                .click();
	}

}
