package com.bptn.feedAppAutomation.stepRestDefinitions;

import org.springframework.beans.factory.annotation.Autowired;

import com.bptn.feedAppAutomation.dao.UserDao;
import com.bptn.feedAppAutomation.restObjects.UserEndPoint;
import com.bptn.feedAppAutomation.jwt.JwtService;
import com.bptn.feedAppAutomation.provider.ResourceProvider;
import com.bptn.feedAppAutomation.context.TestContext;
import com.bptn.feedAppAutomation.dao.FeedDao;
import com.bptn.feedAppAutomation.restObjects.FeedEndPoint;
import com.bptn.feedAppAutomation.dao.FeedMetaDataDao;

import io.restassured.response.Response;

public class EndPointSteps {
	
	protected static final String AUTHORIZATION = "Authorization";
	protected static final String UNAUTHORIZED = "UNAUTHORIZED";

	@Autowired
	UserEndPoint userEndPoint;

	@Autowired
	UserDao userDao;

	Response response;
	
	@Autowired
	ResourceProvider resourceProvider;
	
	@Autowired
	JwtService jwtService;
	
	String jwtToken;
	
	@Autowired
	TestContext context;
	
	@Autowired
	FeedEndPoint feedEndPoint;

	@Autowired
	FeedDao feedDao;
	
	@Autowired
	FeedMetaDataDao feedMetaDataDao;
}