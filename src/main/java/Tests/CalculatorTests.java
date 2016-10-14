package Tests;

import PageObjects.CalculatorMainPage;
import WrappedDriver.RemoteDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.net.MalformedURLException;

//Class for testing the Calculator page
public class CalculatorTests {

    RemoteDriver driver;
    CalculatorMainPage calculatorMainPage;

    //Executed before tests are run
    @BeforeClass
    public void setUp() throws MalformedURLException {

        RemoteDriver.getInstance().startDriver();
        driver = RemoteDriver.getInstance();

        calculatorMainPage = new CalculatorMainPage(driver);
    }

    //Executed after tests are run
    @AfterClass()
    public void tearDown() {
        driver.quit();
    }

    //Generates data for test for Division
    @DataProvider(name = "Division")

    public static Object[][] valuesForDivision() {

        return new Object[][] { { "2", "1" }, { "0", "2" }};

    }

    //Generates data for test for Subtraction
    @DataProvider(name = "Subtraction")

    public static Object[][] valuesForSubtraction() {

        return new Object[][] { { "1", "0" }, { "0", "1" }};

    }

    //Verifies division between two numbers and getting the correct result
    @Test(dataProvider = "Division")
    public void Division_UserReceivesCorrectResult(String argument1, String argument2) {

        String actualResult = calculatorMainPage
                .enterFirstArgument(argument1)
                .enterSecondArgument(argument2)
                .tapDivisionButton()
                .getResult();

        Integer expectedResult = Integer.parseInt(argument1) / Integer.parseInt(argument2);
        Assert.assertTrue(actualResult.equals(expectedResult.toString()));
    }

    //Verifies subtraction between two numbers and getting the correct result
    @Test(dataProvider = "Subtraction")
    public void Subtraction_UserReceivesCorrectResult(String argument1, String argument2) {

        String actualResult = calculatorMainPage
                .enterFirstArgument(argument1)
                .enterSecondArgument(argument2)
                .tapSubtractionButton()
                .getResult();

        Integer expectedResult = Integer.parseInt(argument1) - Integer.parseInt(argument2);
        Assert.assertTrue(actualResult.equals(expectedResult.toString()));
    }
}