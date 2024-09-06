package com.bptn.feedAppAutomation.pageObjects;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

@Component
public class RegisterPage extends BasePage {

	By txtFirstName = By.id("firstName");
	By txtLastName = By.id("lastName");
	By txtUsername = By.id("username");
	By txtEmail = By.id("emailId");
	By txtPassword = By.id("password");
	By txtPhone = By.id("phone");

	By xPathRegisterBtn = By.xpath("//input[@type='submit' and @value='Register']");
	By xPathMessageBadge = By.xpath("//*[@id='messageBadge']");

	public void openRegisterPage(String registerPageRoute) {
		// This step is moved from RegisterPageSteps.java. You have to remove this line
		// from RegisterPageSteps class.
		this.driverManager.getDriver().get(this.provider.getFrontEndUrl() + registerPageRoute);
	}

	public void setFirstName(String firstName) {
		// This step is moved from RegisterPageSteps.java, you have to remove this line
		// from RegisterPageSteps.
		this.driverManager.getDriver().findElement(this.txtFirstName).sendKeys(firstName);
	}

	public void setLastName(String lastName) {
		// This step is moved from RegisterPageSteps.java, you have to remove this line
		// from RegisterPageSteps.
		this.driverManager.getDriver().findElement(this.txtLastName).sendKeys(lastName);
	}

	public void setUsername(String username) {
		// This step is moved from RegisterPageSteps.java, you have to remove this line
		// from RegisterPageSteps.
		this.driverManager.getDriver().findElement(this.txtUsername).sendKeys(username);
	}

	public void setEmail(String email) {
		// This step is moved from RegisterPageSteps.java, you have to remove this line
		// from RegisterPageSteps.
		this.driverManager.getDriver().findElement(this.txtEmail).sendKeys(email);
	}

	public void setPassword(String password) {
		// This step is moved from RegisterPageSteps.java, you have to remove this line
		// from RegisterPageSteps.
		this.driverManager.getDriver().findElement(this.txtPassword).sendKeys(password);
	}

	public void setPhone(String phone) {
		// This step is moved from RegisterPageSteps.java, you have to remove this line
		// from RegisterPageSteps.
		this.driverManager.getDriver().findElement(this.txtPhone).sendKeys(phone);
	}
	
	public void clickSubmit() {
	    //This step is moved from RegisterPageSteps.java, you have to remove this line from RegisterPageSteps.
	    this.driverManager.getDriver()
	                     .findElement(this.xPathRegisterBtn)
	                     .click();
	}
	
	public String getMessage() {
	    //This step is moved from RegisterPageSteps.java. You have to remove this line from RegisterPageSteps.
	    return this.getMessage(this.xPathMessageBadge);
	}
}