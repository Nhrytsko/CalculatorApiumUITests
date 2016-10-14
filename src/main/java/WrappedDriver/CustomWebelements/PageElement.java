package WrappedDriver.CustomWebelements;

import WrappedDriver.RemoteDriver;
import org.openqa.selenium.*;

import java.util.List;

public class PageElement implements WebElement{

    WebElement element;
    List<WebElement> webElementList;

    public PageElement(SearchCriteria criteria, String searchText){
        By search = SelectorConverter.getSearchCriteria(criteria, searchText);

       this.element = findElement(search);
    }

    public void click() {
        if (this.element != null){
            this.element.click();
        }
        this.element.click();
    }

    public void jsClick(){
        RemoteDriver.getInstance().jsClick(this.element);
    }

    public void submit() {
        this.element.submit();
    }

    public void sendKeys(CharSequence... charSequences) {
        this.element.sendKeys(charSequences);
    }

    public void clear() {
        this.element.clear();
    }

    public String getTagName() {
        return this.element.getTagName();
    }

    public String getAttribute(String s) {
        return this.element.getAttribute(s);
    }

    public boolean isSelected() {
        return this.element.isSelected();
    }

    public boolean isEnabled() {
        return this.element.isEnabled();
    }

    public String getText() {
        return this.element.getText();
    }

    public List<WebElement> findElements(By by) {
        RemoteDriver.getInstance().waitForDocumentToBeReady();
        RemoteDriver.waitForAjax();
        webElementList = RemoteDriver.getInstance().findElements(by);

        return webElementList;
    }

    public WebElement findElement(By by) {
        RemoteDriver.getInstance().waitForDocumentToBeReady();
        RemoteDriver.waitForAjax();
        element = RemoteDriver.getInstance().findElement(by);

        RemoteDriver.getInstance().waitForElement(element);
        return element;
    }

    public boolean isDisplayed() {
        return this.element.isDisplayed();
    }

    public Point getLocation() {
        return this.element.getLocation();
    }

    public Dimension getSize() {
        return this.element.getSize();
    }

    public String getCssValue(String s) {
        return this.element.getCssValue(s);
    }

    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return this.element.getScreenshotAs(outputType);
    }
}
