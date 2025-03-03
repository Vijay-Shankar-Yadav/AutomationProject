package com.atoa.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.atoa.qa.base.TestBase;
import com.atoa.qa.pages.PaymentIdPage;
import com.atoa.qa.pages.BankPage;
import com.atoa.qa.pages.QRCodePage;
import com.atoa.qa.pages.TipPage;

import java.time.Duration;

public class PaymentIdPageTest extends TestBase {
    private TipPage tipPage;
    private QRCodePage qrCodePage;
    private BankPage bankPage;
    private PaymentIdPage paymentIdPage;
    
  //  SoftAssert soft=new SoftAssert();

    @BeforeClass
    public void setUp() {
        initialization();
        tipPage = new TipPage();
        qrCodePage = new QRCodePage();
        bankPage = new BankPage();
        paymentIdPage = new PaymentIdPage();
        qrCodePage.proceedToTipPage();
        tipPage.enterCustomTipAmount("4");
        bankPage.selectBankAndProceed();
    }

    @Test()
    public void testPaymentIdPage() {
        // Assuming the previous flow has already navigated to this page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("paymentIdempotencyId"))); // Adjusted ID
        } catch (TimeoutException e) {
            Assert.fail("Payment Idempotency ID element not found after waiting.");
        }

        // Validate the PaymentId page details
        System.out.println(paymentIdPage.getPaymentIdempotencyId());
        System.out.println(paymentIdPage.getApplicationUserId());
        System.out.println(paymentIdPage.getInstitution());
        System.out.println(paymentIdPage.getUserUuid());
        System.out.println(paymentIdPage.getMerchantId());
        System.out.println(paymentIdPage.getEnv());
        System.out.println(paymentIdPage.getPaymentStatus());
        System.out.println(paymentIdPage.getIsoCode());
        
       // soft.assertAll();

        // Click the submit button
        paymentIdPage.clickSubmit();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

