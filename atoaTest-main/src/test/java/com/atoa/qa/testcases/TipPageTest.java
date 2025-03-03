

package com.atoa.qa.testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import com.atoa.qa.base.TestBase;
import com.atoa.qa.pages.TipPage; 
import com.atoa.qa.pages.QRCodePage;

public class TipPageTest extends TestBase {
    TipPage tipPage;
    QRCodePage qrCodePage;

    public TipPageTest() {
        super();
    }

    @BeforeClass
    public void setUp() {
        initialization();
        tipPage = new TipPage(); 
        qrCodePage = new QRCodePage();
        
        // Go to the tip page
        qrCodePage.proceedToTipPage();
    }
    
    @Test(priority = 1)
    public void testTipFunctionality() {
        String initialAmount = "£63";
        double initialAmountValue = Double.parseDouble(initialAmount.replace("£", "").trim());

        // Define tip values
        double[] tips = {1, 2, 5};

        // Loop through each tip option
        for (int i = 0; i < tips.length; i++) {
            tipPage.selectTipOption(i); // Select the tip
            double expectedTotal = initialAmountValue + tips[i]; // Calculate expected total
            
            // Wait and check the submit button text
            new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(tipPage.getProceedButton())); // Wait for the proceed button to be visible

            Assert.assertEquals(tipPage.getTotalAmount(), "Proceed with £" + String.format("%.2f", expectedTotal), 
                "Expected total should be £" + String.format("%.2f", expectedTotal));
            
            // Deselect the tip option
            tipPage.deselectTipOption(i);
        }

        // Check for the custom tip option
        if (tipPage.getTipOptions().size() > 3) { 
            tipPage.selectTipOption(3); // Select custom tip
            tipPage.enterCustomTipAmount("3"); // Enter £3
            double customExpectedTotal = initialAmountValue + 3; // Custom total
            
            // Wait and verify the button text for the custom tip
            new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(tipPage.getProceedButton())); // Wait for the proceed button to be visible
            
            Assert.assertEquals(tipPage.getTotalAmount(), "Proceed with £" + String.format("%.2f", customExpectedTotal), 
                "Expected total should be £" + String.format("%.2f", customExpectedTotal));
            
            // Deselect the custom tip option
            tipPage.deselectTipOption(3);
        } else {
            System.out.println("Custom tip option is not available.");
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}