

package com.atoa.qa.pages;

import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.atoa.qa.base.TestBase;

public class PaymentIdPage extends TestBase {

    @FindBy(xpath="//input[@id='paymentIdempotencyId']")
    private WebElement paymentIdempotencyId;

    @FindBy(xpath="//input[@id='applicationUserId']")
    private WebElement applicationUserId;

    @FindBy(xpath="//input[@id='institution']")
    private WebElement institution;

    @FindBy(xpath="//input[@id='user-uuid']")
    private WebElement userUuid;

    @FindBy(xpath="//input[@id='merchantId']")
    private WebElement merchantId;

    @FindBy(xpath="//input[@id='env']")
    private WebElement env;

    @FindBy(xpath="//select[@id='status']")
    private WebElement paymentStatus;

    @FindBy(xpath="//select[@id='isocode']")
    private WebElement isoCode;

    @FindBy(css = "button[type='submit']")
    private WebElement submitButton;

    public PaymentIdPage() {
        PageFactory.initElements(driver, this);
    }

    public String getPaymentIdempotencyId() {
        waitForVisibility(paymentIdempotencyId);
        return paymentIdempotencyId.getAttribute("value");
    }

    public String getApplicationUserId() {
        waitForVisibility(applicationUserId);
        return applicationUserId.getAttribute("value");
    }

    public String getInstitution() {
        waitForVisibility(institution);
        return institution.getAttribute("value");
    }

    public String getUserUuid() {
        waitForVisibility(userUuid);
        return userUuid.getAttribute("value");
    }

    public String getMerchantId() {
        waitForVisibility(merchantId);
        return merchantId.getAttribute("value");
    }

    public String getEnv() {
        waitForVisibility(env);
        return env.getAttribute("value");
    }

    public String getPaymentStatus() {
        waitForVisibility(paymentStatus);
        return paymentStatus.getAttribute("value");
    }

    public String getIsoCode() {
        waitForVisibility(isoCode);
        return isoCode.getAttribute("value");
    }

    public void clickSubmit() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    private void waitForVisibility(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOf(element));
    }
}
