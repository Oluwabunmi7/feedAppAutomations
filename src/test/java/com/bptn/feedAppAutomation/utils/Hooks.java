package com.bptn.feedAppAutomation.utils;

import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;

import com.bptn.feedAppAutomation.web.DriverManager;

import io.cucumber.java.After;

public class Hooks {

	
    @Autowired
    DriverManager driverManager;
    

	@After("@WebTest")
	public void screenshot(Scenario scenario) {
		byte[] screenshot = ((TakesScreenshot) this.driverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
		scenario.attach(screenshot, "image/png", scenario.getName() + " Screenshot");
	}

}
