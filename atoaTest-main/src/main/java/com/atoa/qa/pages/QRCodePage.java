package com.atoa.qa.pages;

import java.time.Duration;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.atoa.qa.base.TestBase;

public class QRCodePage extends TestBase {

    @FindBy(xpath = "//*[text()='£63.00']")
    private WebElement amountElement;

    @FindBy(xpath = "//*[text()='Shubjeet payment']")
    private WebElement noteElement;

    @FindBy(xpath = "//button[@class='btn continue-btn']")
    private WebElement submitButton;

    @FindBy(xpath = "//p[text()='How to pay with Atoa']")
    private WebElement howToPayButton;

    @FindBy(xpath = "//img[contains(@src,'/assets/full-logo.ca846e88.svg')]")
    private WebElement atoaLogo;

    @FindBy(xpath = "//*[text()=' Terms ']")
    private WebElement termsLink;

    @FindBy(xpath = "//*[text()=' Privacy Policy ']")
    private WebElement privacyPolicyLink;

    @FindBy(xpath = "//*[text()='Help']")
    private WebElement helpLink;

    public QRCodePage() {
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isCRMImageVisible() {
        return isVisible(atoaLogo);
    }

    public String getDisplayedAmount() {
        return amountElement.getText();
    }

    public String getDisplayedNote() {
        return noteElement.getText();
    }

    public boolean isSubmitButtonVisible() {
        return isClickable(submitButton);
    }

    public void proceedToTipPage() {
        submitButton.click();
    }

    public boolean isHowToPayButtonVisible() {
        return isClickable(howToPayButton);
    }

    public boolean isTermsLinkVisible() {
        return isClickable(termsLink);
    }

    public boolean isPrivacyPolicyLinkVisible() {
        return isClickable(privacyPolicyLink);
    }

    public boolean isHelpLinkVisible() {
        return isClickable(helpLink);
    }

    private boolean isClickable(WebElement element) {
        return isVisible(element) && isElementClickable(element);
    }

    private boolean isVisible(WebElement element) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isElementClickable(WebElement element) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void navigateToTerms() {
        clickAndSwitchToNewTab(termsLink);
    }

    public void navigateToPrivacyPolicy() {
        clickAndSwitchToNewTab(privacyPolicyLink);
    }

    public void navigateToHelp() {
        clickAndSwitchToNewTab(helpLink);
    }

    private void clickAndSwitchToNewTab(WebElement link) {
        link.click();
        switchToNewTab();
    }

    private void switchToNewTab() {
        String originalHandle = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    public boolean verifyPageTitle(String expectedTitle) {
        return driver.getTitle().equals(expectedTitle);
    }
}
