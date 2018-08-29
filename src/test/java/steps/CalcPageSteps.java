package steps;

import base.BaseStep;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import pages.CalcPage;

public class CalcPageSteps extends BaseStep {
    @Given("^user open calculator$")
    public void userOpensCalc() throws Throwable {
        Assert.assertTrue("Application is not open", CalcPage.isOpen());
    }


    @When("^user tap (.*) button (.*) times$")
    public void userTapButtonTimes(String button, int count) throws Throwable {
        switch (button) {
            case "ADD":
                CalcPage.btnAdd.tap(count);
                break;
            case "SUBTRACT":
                CalcPage.btnSubtract.tap(count);
                break;
            case "SQUARE ROOT":
                CalcPage.btnSqrt.tap(count);
                break;
            case "DIVIDE by 2":
                CalcPage.btnDivide.tap(count);
                break;
            case "MULTIPLY by 2":
                CalcPage.btnMultiply.tap(count);
                break;
            case "POWER by 2":
                CalcPage.btnPower.tap(count);
        }
    }

    @Then("^the result should be (.*)$")
    public void theResultShouldBe(String expectedResult) throws Throwable {
        Assert.assertEquals("Wrong result", expectedResult, CalcPage.getResult());
    }

}