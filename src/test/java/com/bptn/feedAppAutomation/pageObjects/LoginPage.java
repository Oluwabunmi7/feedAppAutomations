package com.bptn.feedAppAutomation.pageObjects;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

@Component
public class LoginPage extends BasePage {


	By txtUsername = By.id("username");
	By txtPassword = By.id("password");
	By xPathLoginBtn = By.xpath("//input[@type='submit' and @value='Login']");

	By xPathMessageBadge = By.xpath("//*[@id='messageBadge']");


	public void openLoginPage(String loginPageRoute) {
		this.driverManager.getDriver()
		                .get(this.provider.getFrontEndUrl() + loginPageRoute);
	}

	public void setUsername(String username) {
		this.driverManager.getDriver()
		                .findElement(this.txtUsername)
		                .sendKeys(username);
	}

	public void setPassword(String password) {
		this.driverManager.getDriver()
		                .findElement(this.txtPassword)
		                .sendKeys(password);
	}

	public void clickSubmit() {
		this.driverManager.getDriver()
		                .findElement(this.xPathLoginBtn)
		                .click();
	}

	public String getMessage() {
		
		return this.getMessage(this.xPathMessageBadge);
	}

}
