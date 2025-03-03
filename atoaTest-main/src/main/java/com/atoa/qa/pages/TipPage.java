package com.atoa.qa.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.atoa.qa.base.TestBase;

public class TipPage extends TestBase {
    
    @FindBy(xpath="//span[@class='title']")
    private WebElement addTipTitle;
    
    @FindBy(xpath = "//div[@class='tip-option-content']")
    private List<WebElement> tipOptions;
    
    @FindBy(id = "tip_input_amount") 
    private WebElement customTipInput;

    @FindBy(xpath = "//button[@id='select-bank-cta']")
    private WebElement proceedButton;
    
    @FindBy(xpath = "//p[text()='Custom']")
    private WebElement customButton;

    public TipPage() {
        PageFactory.initElements(driver, this);
    }
    
    public List<WebElement> getTipOptions() {
        return tipOptions; 
    }

    public void selectTipOption(int index) {
        if (index >= 0 && index < tipOptions.size()-1) {
            tipOptions.get(index).click();
        } else {
            System.out.println("Invalid index: " + index + ", available options: " + tipOptions.size());
        }
    }

    public void enterCustomTipAmount(String amount) {
    	customButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOf(customTipInput));
        
        customTipInput.clear();
        customTipInput.sendKeys(amount);
        proceedToNextPage(); // Submit the custom amount by clicking proceed
    }

    public String getTotalAmount() {
        return proceedButton.getText(); // Get the total amount displayed
    }

    public void deselectTipOption(int index) {
        if (index >= 0 && index < tipOptions.size()) {
            String xpath = String.format("(//div[@class='tip-option-content'])[%d]//img[contains(@class, 'close-icon')]", index + 1);
            try {
                WebElement deselectButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
                deselectButton.click(); // Click to deselect the option
            } catch (NoSuchElementException e) {
                System.out.println("Deselect button not found for index: " + index);
            }
        } else {
            System.out.println("Invalid index: " + index + ", available options: " + tipOptions.size());
        }
    }

    public void proceedToNextPage() {
    	
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(proceedButton));
        proceedButton.click(); // Click to navigate to the next page
    }

    public WebElement getProceedButton() {
        return proceedButton; // Added method to get the proceed button
    }
    
    
}