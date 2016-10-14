package PageObjects;

import WrappedDriver.RemoteDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

//Page object model for Calculator Main Page
public class CalculatorMainPage {

    private RemoteDriver remoteDriver;
    private WebElement firstArgumentInput;
    private WebElement secondArgumentInput;
    private WebElement subtractionButton;
    private WebElement divisionButton;
    private WebElement resultInput;

    public CalculatorMainPage(RemoteDriver driver){
        remoteDriver = driver;

        firstArgumentInput = remoteDriver.findElement(By.id("arg1"));
        secondArgumentInput = remoteDriver.findElement(By.id("arg2"));
        subtractionButton = remoteDriver.findElement(By.id("subtraction"));
        divisionButton = remoteDriver.findElement(By.id("division"));
        resultInput = remoteDriver.findElement(By.id("result"));
    }

    //Enters the first argument to the input
    public CalculatorMainPage enterFirstArgument(String argument){
        firstArgumentInput.sendKeys(argument);
        return  this;
    }

    //Enters the second argument to the input
    public CalculatorMainPage enterSecondArgument(String argument){
        secondArgumentInput.sendKeys(argument);
        return this;
    }

    //Taps the subtraction button
    public CalculatorMainPage tapSubtractionButton(){
        subtractionButton.click();
        return this;
    }

    //Taps the division button
    public CalculatorMainPage tapDivisionButton(){
        divisionButton.click();
        return  this;
    }

    //Gets the result
    public String getResult(){
        return resultInput.getText();
    }

}
