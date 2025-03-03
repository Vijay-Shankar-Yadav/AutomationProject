package com.atoa.qa.testcases;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.atoa.qa.base.TestBase;
import com.atoa.qa.pages.TipPage;
import com.atoa.qa.pages.QRCodePage;
import com.atoa.qa.pages.BankPage;

public class BankPageTest extends TestBase {
    private TipPage tipPage;
    private QRCodePage qrCodePage;
    private BankPage bankPage; 
    private static final String CURRENCY_SYMBOL = "£";
    private static final double[] TIP_VALUES = {1, 2, 5}; 
    private static final double INITIAL_AMOUNT_VALUE = 63.00;

    @BeforeClass
    public void setUp() {
        initialization();
        tipPage = new TipPage(); 
        qrCodePage = new QRCodePage();
        bankPage = new BankPage(); 
        qrCodePage.proceedToTipPage();
    }
    
    @Test()
    public void testTipRedirectionText() {
        List<WebElement> options = tipPage.getTipOptions();
        Assert.assertFalse(options.isEmpty(), "Tip options should not be empty.");
        
        for (int i = 0; i < options.size(); i++) {
            tipPage.selectTipOption(i);
            tipPage.proceedToNextPage();

            // Wait for the bank page to load
            new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(bankPage.getIncludesTipElement()));

            // Validate tip text
            double expectedTotal = INITIAL_AMOUNT_VALUE + TIP_VALUES[i];
            String expectedTipText = "Includes tip of " + CURRENCY_SYMBOL + 
                (TIP_VALUES[i] % 1 == 0 ? String.format("%.0f", TIP_VALUES[i]) : String.format("%.1f", TIP_VALUES[i]));
            String expectedTotalText = CURRENCY_SYMBOL + String.format("%.2f", expectedTotal);

            Assert.assertEquals(bankPage.getIncludesTipText(), expectedTipText);
            Assert.assertEquals(bankPage.getTotalText(), expectedTotalText);

            // Ensure bank options are available
            List<WebElement> bankOptions = bankPage.getBankOptions();
            Assert.assertFalse(bankOptions.isEmpty(), "No bank options available.");
            
            // Select the bank option
            bankPage.selectBank(0);
            bankPage.clickTermsCheckbox();

            // Click the button to proceed
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement goToAtoaButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='select-bank-cta']")));
            goToAtoaButton.click();
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
