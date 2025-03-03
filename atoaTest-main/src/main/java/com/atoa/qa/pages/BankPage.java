

package com.atoa.qa.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.atoa.qa.base.TestBase;

public class BankPage extends TestBase {

    @FindBy(xpath = "//p[@class='tip-txt']")
    private WebElement includesTipText;

    @FindBy(xpath = "//div[@class='amount-text']")
    private WebElement totalText;

    @FindBy(xpath = "//label[@class='bank-item']")
    private List<WebElement> bankOptions;

    @FindBy(xpath = "//div[@class='b-input']")
    private WebElement termsOfServiceCheckbox;

    @FindBy(xpath = "//button[@id='select-bank-cta']")
    private WebElement goToAtoaButton;

    public BankPage() {
        PageFactory.initElements(driver, this);
    }

    public String getIncludesTipText() {
        return includesTipText.getText();
    }

    public String getTotalText() {
        return totalText.getText();
    }

    public WebElement getIncludesTipElement() {
        return includesTipText;
    }

    public List<WebElement> getBankOptions() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfAllElements(bankOptions));
        return bankOptions;
    }

    public WebElement getTermsOfServiceCheckbox() {
        return termsOfServiceCheckbox;
    }

    public WebElement getGoToAtoaButton() {
        return goToAtoaButton;
    }

    public void selectBank(int index) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfAllElements(bankOptions));

        if (index >= 0 && index < bankOptions.size()) {
            bankOptions.get(index).click();
            // Short wait after selecting the bank
            try {
                Thread.sleep(1000); // Adjust as needed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            throw new IndexOutOfBoundsException("Invalid index: " + index + ", available options: " + bankOptions.size());
        }
    }

    public void clickTermsCheckbox() {
        // Wait for the checkbox to be present and visible
        new WebDriverWait(driver, Duration.ofSeconds(20))
            .until(ExpectedConditions.visibilityOf(termsOfServiceCheckbox));

        // Scroll to the checkbox
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", termsOfServiceCheckbox);

        // Log the state of the checkbox
        System.out.println("Checkbox displayed: " + termsOfServiceCheckbox.isDisplayed());
        System.out.println("Checkbox enabled: " + termsOfServiceCheckbox.isEnabled());

        // Click the checkbox
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(termsOfServiceCheckbox)).click();
        } catch (TimeoutException e) {
            System.err.println("Error clicking terms checkbox: " + e.getMessage());
            // Attempt to click using JavaScript if normal click fails
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", termsOfServiceCheckbox);
        }
    }

    public void clickGoToAtoa() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(goToAtoaButton)).click();
    }
    public void selectBankAndProceed() {
    	selectBank(0);
    	clickTermsCheckbox();
    	clickGoToAtoa();
    	
    }
}