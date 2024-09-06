package com.bptn.feedAppAutomation;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {

	@Given("an example scenario")
	public void an_example_scenario() {

		System.out.println("an example scenario");
	}

	@When("all step definitions are implemented")
	public void all_step_definitions_are_implemented() {

		System.out.println("all step definitions are implemented");
	}

	@Then("the scenario passes")
	public void the_scenario_passes() {

		System.out.println("the scenario passes");
	}
}