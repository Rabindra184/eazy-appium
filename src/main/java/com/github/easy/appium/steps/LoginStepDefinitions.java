package com.github.easy.appium.steps;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;


public class LoginStepDefinitions extends StepDefinitions {

    private Scenario scenario;

    @Override
    @Before
    protected  void before(Scenario scenario) {
        setScenario (scenario);
    }

    @Given("the user is able to login to the app")
    public void the_user_is_able_to_login_to_the_app() {
        getLoginScreen().login();
    }
}
