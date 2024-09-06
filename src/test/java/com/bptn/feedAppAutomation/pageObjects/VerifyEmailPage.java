package com.bptn.feedAppAutomation.pageObjects;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

@Component
public class VerifyEmailPage extends BasePage {
	
	By xPathMessage = By.xpath("//*[@id=\"root\"]/div/div/div/div/div/p");
	
	public void openVerifyEmailPage(String verifyEmailPageRoute) {
		this.driverManager.getDriver()
			                .get(this.provider.getFrontEndUrl() + verifyEmailPageRoute);
	}
	
	public String getMessage() {
		return this.getMessage(this.xPathMessage);
	}

}