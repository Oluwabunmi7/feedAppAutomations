package com.bptn.feedAppAutomation.pageObjects;

import java.util.UUID;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class FeedPage extends BasePage {
	
	By txtContent = By.id("content");

	By xPathSendBtn = By.xpath("//button[text()='Send']");
	By xPathMessageBadge = By.xpath("//*[@id='messageBadge']");

	String testId;
	
	public void setFeedContent(String content){
	    String contentWithTestId = content + "(Test Id: " + this.testId + ")";
	    this.driverManager.getDriver()
	                .findElement(this.txtContent)
	                .sendKeys(contentWithTestId);
	}
	
	public void clickSend() {
	    this.driverManager.getDriver()
	                .findElement(this.xPathSendBtn)
	                .click();
	}
	
	public String getMessage() {
		return this.getMessage(this.xPathMessageBadge);
	}
	
	public void navigateToMyFeeds(String myFeedsTitle){
	    this.driverManager.getDriver()
	                .findElement(By.linkText(myFeedsTitle))
		            .click();
	}
	
	public String getFeedContent(){
		return this.getMessage(By.xpath("//*[contains(text(), 'Test Id: " + this.testId + "')]"));
	}
	
	@PostConstruct
	private void postConstruct() {
	 	this.testId = UUID.randomUUID().toString();
	}

}