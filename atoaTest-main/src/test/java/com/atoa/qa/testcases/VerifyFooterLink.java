//footer Link

package com.atoa.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.atoa.qa.base.TestBase;
import com.atoa.qa.pages.QRCodePage;

public class VerifyFooterLink extends TestBase {
    QRCodePage qrCodePage;

    public VerifyFooterLink() {
        super();
    }

    @BeforeClass
    public void setUp() {
        initialization();
        qrCodePage = new QRCodePage();
    }

    @Test(priority = 1)
    public void verifyTermsPage() {
        checkPageUrl(qrCodePage::navigateToTerms, "https://paywithatoa.co.uk/terms/");
    }

    @Test(priority = 2)
    public void verifyPrivacyPolicyPage() {
        checkPageUrl(qrCodePage::navigateToPrivacyPolicy, "https://paywithatoa.co.uk/atoa-pay-privacy-policy/");
    }

    @Test(priority = 3)
    public void verifyHelpPage() {
        checkPageUrl(qrCodePage::navigateToHelp, "https://help.paywithatoa.co.uk/");
    }

    private void checkPageUrl(Runnable navigateMethod, String expectedUrl) {
        String originalHandle = driver.getWindowHandle();
        navigateMethod.run();

        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Page URL does not match!");

        driver.close();
        driver.switchTo().window(originalHandle);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}