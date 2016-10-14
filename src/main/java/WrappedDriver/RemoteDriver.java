package WrappedDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

//Selenium and Appium Wrapper
public class RemoteDriver {
    private RemoteDriver(){}

    private static RemoteWebDriver androidDriver;
    private static DesiredCapabilities capabilities;
    private static RemoteDriver instance;

    //Gets the instance of RemoteDriver class
    public static RemoteDriver getInstance(){

        if (instance == null) {
            synchronized (RemoteDriver.class) {
                    instance =  new RemoteDriver();
                }
        }
        return instance;
    }

    //Starts the appropriate driver
    public RemoteWebDriver startDriver(){

        // WebDriver instance is not created yet
        if (androidDriver == null){
            capabilities = setAndroidCapabilities();
            androidDriver = new RemoteWebDriver(URLBuilder.getRemoteServerUrl(ConfigProvider.getHubUrl(), ConfigProvider.getPort()), capabilities);
        }

        //Use existing driver
        return androidDriver;
    }

    //Closes the driver
    public void quit(){
        startDriver().quit();
    }

    //Sets Android desired capabilities for Appium
    private DesiredCapabilities setAndroidCapabilities() {
        File app = new File(ConfigProvider.getAppsDirectoryPath(), ConfigProvider.getAndroidApp());

        capabilities = DesiredCapabilities.android();
        capabilities.setCapability("appium-version", "1.0");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "5.1");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("appPackage", "im.getsocial.calc");
        capabilities.setCapability("appActivity", "im.getsocial.calc.MainActivity");
        capabilities.setCapability("app", app.getAbsolutePath());

        return capabilities;
    }

    //Clicks the element using JavaScript code
    public void jsClick(WebElement element){
        JavascriptExecutor jse = (JavascriptExecutor) androidDriver;
        jse.executeScript("arguments[0].click();", element);
    }

    //Find and wait for the elements
    public List<WebElement> findElements(By by) {
        waitForElement(by);
        return androidDriver.findElements(by);
    }

    //Find and wait for the element
    public WebElement findElement(By by) {
        waitForElement(by);
        return androidDriver.findElement(by);
    }

    //Waits for particular located webelement
    public void waitForElement(WebElement element){
        implicitWait(30);
        WebDriverWait wait = new WebDriverWait(startDriver(), ConfigProvider.getPageLoadTimeout());
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    //Waits for element using it's locator
    public void waitForElement (By selector){
        implicitWait(30);
        WebDriverWait wait = new WebDriverWait(startDriver(), ConfigProvider.getPageLoadTimeout());
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(selector));
    }

    public static void implicitWait(long timeToWaitInSeconds){
        instance.startDriver().manage().timeouts().implicitlyWait(timeToWaitInSeconds, TimeUnit.SECONDS);
    }

    //Waits the HTML document to be ready
    public static void waitForDocumentToBeReady(){
        try{
            ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return ((JavascriptExecutor)instance.startDriver())
                            .executeScript("return document.readyState").equals("complete");
                }
            };

            WebDriverWait wait = new WebDriverWait(instance.startDriver(), ConfigProvider.getPageLoadTimeout());
            wait.until(pageLoadCondition);}
        catch (Exception e) {
            implicitWait(ConfigProvider.getPageLoadTimeout());
        }
    }

    //Waits for AJAX execution
    public static void waitForAjax() {
        Boolean isJqueryUsed = (Boolean)((JavascriptExecutor)instance.startDriver())
                .executeScript("return (typeof(jQuery) != 'undefined')");
        if (isJqueryUsed){
            while (true){

                //JavaScript test to verify jQuery is active or not
                Boolean ajaxIsComplete = (Boolean)((JavascriptExecutor)instance.startDriver())
                        .executeScript("return jQuery.active ==0");
                if (ajaxIsComplete) break;
                try{
                    Thread.sleep(ConfigProvider.getThreadSleepTimeout());
                }
                catch (InterruptedException e){}
            }
        }
    }

}
