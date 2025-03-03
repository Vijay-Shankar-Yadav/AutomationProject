

package com.atoa.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.atoa.qa.base.TestBase;
import com.atoa.qa.pages.QRCodePage;

public class QRCodePageTest extends TestBase {
    QRCodePage qrCodePage;

    public QRCodePageTest() {
        super();
    }

    @BeforeClass
    public void setUp() {
        initialization();
        qrCodePage = new QRCodePage();
    }

    @Test(priority = 1)
    public void verifyPageTitle() {
        String expectedTitle = "Atoa Instant Bank Pay"; // Replace with actual title
        Assert.assertEquals(qrCodePage.getPageTitle(), expectedTitle, "Page title does not match!");
    }

    @Test(priority = 2)
    public void verifyCRMImageVisibility() {
        Assert.assertTrue(qrCodePage.isCRMImageVisible(), "CRM image is not visible.");
    }

    @Test(priority = 3)
    public void verifyDisplayedAmount() {
        String expectedAmount = "£63.00"; // Replace with expected amount
        Assert.assertEquals(qrCodePage.getDisplayedAmount(), expectedAmount, "Displayed amount does not match!");
    }

    @Test(priority = 4)
    public void verifyDisplayedNote() {
        String expectedNote = "Shubjeet payment"; // Replace with actual note
        Assert.assertEquals(qrCodePage.getDisplayedNote(), expectedNote, "Displayed note does not match!");
    }

    @Test(priority = 5)
    public void verifySubmitButtonVisibility() {
        Assert.assertTrue(qrCodePage.isSubmitButtonVisible(), "Submit button is not visible.");
    }

    @Test(priority = 6)
    public void verifyHowToPayButtonVisibility() {
        Assert.assertTrue(qrCodePage.isHowToPayButtonVisible(), "How to Pay button is not visible or clickable.");
    }

    @Test(priority = 7)
    public void verifyTermsLinkVisibility() {
        Assert.assertTrue(qrCodePage.isTermsLinkVisible(), "Terms link is not visible or clickable.");
    }

    @Test(priority = 8)
    public void verifyPrivacyPolicyLinkVisibility() {
        Assert.assertTrue(qrCodePage.isPrivacyPolicyLinkVisible(), "Privacy Policy link is not visible or clickable.");
    }

    @Test(priority = 9)
    public void verifyHelpLinkVisibility() {
        Assert.assertTrue(qrCodePage.isHelpLinkVisible(), "Help link is not visible or clickable.");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}