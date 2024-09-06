package com.bptn.feedAppAutomation.restObjects;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.DataTable;

@Component
public class UserEndPoint extends EndPoint {

	public void createLoginRequest(String username, String password) throws JsonProcessingException {

		List<List<String>> data = Arrays.asList(Arrays.asList("username", username),Arrays.asList("password", password));

		this.createRequestWithData(DataTable.create(data, this.tableConverter));
	}
}
