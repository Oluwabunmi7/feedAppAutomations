package com.bptn.feedAppAutomation.pageObjects;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

@Component
public class ProfilePage extends BasePage {
	
	By txtHeadline = By.id("headline");
	By txtBio = By.id("bio");
	By txtCity = By.id("city");
	By txtCountry = By.id("country");

	By xPathSaveBtn = By.xpath("//input[@type='submit' and @value='Save']");
	
	public void setHeadline(String headline) {
	    this.driverManager.getDriver()
	                .findElement(this.txtHeadline)
		            .sendKeys(headline);
	}
	
	public void setBio(String bio) {
	    this.driverManager.getDriver()
	                .findElement(this.txtBio)
	                .sendKeys(bio);
	}
	
	public void setCity(String city) {
	    this.driverManager.getDriver()
	                .findElement(this.txtCity)
		            .sendKeys(city);
	}
	
	public void setCountry(String country) {
		this.driverManager.getDriver()
	                .findElement(this.txtCountry)
	                .sendKeys(country);
	}
	
	public void clickSave() {
		this.driverManager.getDriver()
	               .findElement(this.xPathSaveBtn)
	               .click();
	}
	
	

}