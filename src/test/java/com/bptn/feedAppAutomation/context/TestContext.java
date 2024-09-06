package com.bptn.feedAppAutomation.context;

import org.springframework.stereotype.Component;

@Component
public class TestContext {

	String token;
		
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
