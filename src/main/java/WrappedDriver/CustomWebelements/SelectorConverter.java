package WrappedDriver.CustomWebelements;

import org.openqa.selenium.By;

public class SelectorConverter {

    public static By getSearchCriteria(SearchCriteria criteria, String searchedText){
        switch (criteria){
            case CssSelector: return By.cssSelector(searchedText);
            case Id: return By.id(searchedText);
            case LinkedText: return By.linkText(searchedText);
            case Name: return By.name(searchedText);
            case PartialLinkedText: return By.partialLinkText(searchedText);
            case TagName: return By.tagName(searchedText);
            case Xpath: return By.xpath(searchedText);
            default: return By.xpath(searchedText);
        }
    }
}
