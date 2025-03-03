# Test Case Documentation

This document outlines the key test cases for various pages such as **Footer Links**, **QRCode Page**, **Tip Page**, **Payment ID Page**, and **Bank Page** in the `AtoaTest` project. Below is a summary of the test cases, objectives, actions, and expected outcomes.

## Technologies Used

- **Programming Language**: Java
- **Test Framework**: TestNG
- **Build Tool**: Maven
- **IDE**: Eclipse
- **Web Driver**: Selenium
- **Version Control**: Git

## Running Tests in Eclipse

1. **Setup the Environment**:
   - Install Eclipse IDE for Java Developers.
   - Install Maven if not included in your Eclipse setup.

2. **Clone the Repository**:
   - Use the command:
     ```bash
     git clone <repository-url>
     ```
   - Open Eclipse and import the project using:
     - `File` > `Import` > `Maven` > `Existing Maven Projects`.

3. **Add Dependencies**:
   - Ensure your `pom.xml` file contains necessary dependencies for Selenium and TestNG.

4. **Run Tests**:
   - Right-click on the test class or package you want to run.
   - Select `Run As` > `TestNG Test`.
   - Check the console output for test results.

---

### 1. **VerifyFooterLink**
**Objective**: Verify the functionalities of the QR Code Page's Footer Links.

| **Action**                          | **Preconditions**          | **Expected Result**                                           | **Assertion**                                                                                                    | **Status** |
|-------------------------------------|----------------------------|--------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------|------------|
| Verify Navigation to Terms Page     | User is on the QR Code Page| The current URL should be "https://paywithatoa.co.uk/terms/". | `Assert.assertEquals(driver.getCurrentUrl(), "https://paywithatoa.co.uk/terms/", "Terms page URL does not match!");` | PASS       |
| Verify Navigation to Privacy Policy  | User is on the QR Code Page| The current URL should be "https://paywithatoa.co.uk/atoa-pay-privacy-policy/". | `Assert.assertEquals(driver.getCurrentUrl(), "https://paywithatoa.co.uk/atoa-pay-privacy-policy/", "Privacy Policy page URL does not match!");` | PASS       |
| Verify Navigation to Help Page      | User is on the QR Code Page| The current URL should be "https://help.paywithatoa.co.uk/".| `Assert.assertEquals(driver.getCurrentUrl(), "https://help.paywithatoa.co.uk/", "Help page URL does not match!");`  | PASS       |

---

### 2. **QRCodePageTest**
**Objective**: Verify the functionalities of the QR Code Page including content, logo, title, displayed amount, buttons, and footer links.

| **Action**                          | **Preconditions**          | **Expected Result**                                          | **Assertion**                                                                                               | **Status** |
|-------------------------------------|----------------------------|--------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------|------------|
| Verify Page Title                   | User is on the QR Code Page| The page title should be "Atoa Instant Bank Pay".             | `Assert.assertEquals(qrCodePage.getPageTitle(), "Atoa Instant Bank Pay", "Page title does not match!");`   | PASS       |
| Verify CRM Image Visibility         | User is on the QR Code Page| The CRM image should be visible.                              | `Assert.assertTrue(qrCodePage.isCRMImageVisible(), "CRM image is not visible.");`                           | PASS       |
| Verify Displayed Amount             | User is on the QR Code Page| The displayed amount should be "£63.00".                      | `Assert.assertEquals(qrCodePage.getDisplayedAmount(), "£63.00", "Displayed amount does not match!");`       | PASS       |
| Verify Displayed Note               | User is on the QR Code Page| The displayed note should be "Shubjeet payment".              | `Assert.assertEquals(qrCodePage.getDisplayedNote(), "Shubjeet payment", "Displayed note does not match!");` | PASS       |
| Verify Submit Button Visibility     | User is on the QR Code Page| The submit button should be visible.                          | `Assert.assertTrue(qrCodePage.isSubmitButtonVisible(), "Submit button is not visible.");`                   | PASS       |
| Verify "How to Pay" Button Visibility| User is on the QR Code Page| The "How to Pay" button should be visible.                    | `Assert.assertTrue(qrCodePage.isHowToPayButtonVisible(), "How to Pay button is not visible or clickable.");`| PASS       |
| Verify Terms Link Visibility        | User is on the QR Code Page| The Terms link should be visible.                             | `Assert.assertTrue(qrCodePage.isTermsLinkVisible(), "Terms link is not visible or clickable.");`             | PASS       |
| Verify Privacy Policy Link Visibility| User is on the QR Code Page| The Privacy Policy link should be visible.                    | `Assert.assertTrue(qrCodePage.isPrivacyPolicyLinkVisible(), "Privacy Policy link is not visible or clickable.");`| PASS       |
| Verify Help Link Visibility         | User is on the QR Code Page| The Help link should be visible.                              | `Assert.assertTrue(qrCodePage.isHelpLinkVisible(), "Help link is not visible or clickable.");`               | PASS       |

---

### 3. **TipPageTest**
**Objective**: Validate Tip Selection and Custom Tip Entry.

| **Action**                          | **Preconditions**          | **Expected Result**                                                                                                 | **Assertion**                                                                                                                                       | **Status** |
|-------------------------------------|----------------------------|---------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------|------------|
| Navigate to the Tip Page            | User is moving from QR Code page | The user should be successfully redirected to the Tip Page.                                                       | No assertion needed.                                                                                                                                 | PASS       |
| Select the Tip Option               | User is on the Tip Page    | The tip option should be highlighted or marked as selected.                                                         | `Assert.assertEquals(tipPage.getTotalAmount(), "Proceed with £" + String.format("%.2f", expectedTotal), "Expected total should be £" + String.format("%.2f", expectedTotal));` | PASS       |
| Deselect the Tip Option             | User is on the Tip Page    | The tip option should no longer be highlighted.                                                                     | No specific assertion needed.                                                                                                                         | PASS       |
| Check for Custom Tip Option         | User is on the Tip Page    | If the custom tip option is available, the test proceeds; otherwise, it logs a message.                             | Not applicable here.                                                                                                                                 | PASS       |
| Select Custom Tip Option            | User is on the Tip Page    | The custom tip input field becomes visible or active.                                                               | No specific assertion here.                                                                                                                           | PASS       |
| Enter Custom Tip Amount (e.g., £3) | User is on the Tip Page    | The custom tip amount should be accepted in the input field.                                                        | `Assert.assertEquals(tipPage.getTotalAmount(), "Proceed with £" + String.format("%.2f", customExpectedTotal), "Expected total should be £" + String.format("%.2f", customExpectedTotal));` | PASS       |
| Deselect Custom Tip Option          | User is on the Tip Page    | The custom tip option should no longer be selected.                                                                 | No specific assertion needed.                                                                                                                         | PASS       |

---

### 4. **PaymentIdPageTest**
**Objective**: Verify the details on the Payment ID page.

| **Action**                          | **Preconditions**           | **Expected Result**                                                   | **Assertion**                                                                                          | **Status** |
|-------------------------------------|-----------------------------|-----------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------|------------|
| Verify Payment Idempotency ID       | User is on the Payment ID page | Payment Idempotency ID should be visible and valid.                  | `Assert.assertNotNull(paymentIdPage.getPaymentIdempotencyId(), "Payment Idempotency ID is not valid.");`                                      | PASS       |
| Verify Application User ID          | User is on the Payment ID page | Application User ID should be displayed.                              | `Assert.assertNotNull(paymentIdPage.getApplicationUserId(), "Application User ID is not valid.");`                                             | PASS       |
| Verify Merchant ID                  | User is on the Payment ID page | Merchant ID should be displayed.                                      | `Assert.assertNotNull(paymentIdPage.getMerchantId(), "Merchant ID is not valid.");`                                                               | PASS       |
| Click Submit Button                 | User is on the Payment ID page | Submit button should be clickable and should submit successfully.     | `Assert.assertTrue(paymentIdPage.isSubmitButtonClickable(), "Submit button is not clickable.");`                                                  | PASS       |

---

### 5. **BankPageTest**
**Objective**: Validate the functionality of the Bank page.

| **Action**                          | **Preconditions**           | **Expected Result**                                                                     | **Assertion**                                                                                         | **Status** |
|-------------------------------------|-----------------------------|-----------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------|------------|
| Select Tip Option                   | User is on the Tip page     | The selected tip should reflect in the total amount on the Bank page.                  | `Assert.assertEquals(bankPage.getIncludesTipText(), expectedTipText, "Tip text does not match!");`                                           | PASS       |
| Select Bank Option                  | User is on the Bank page    | Bank options should be visible.                                                         | `Assert.assertFalse(bankPage.getBankOptions().isEmpty(), "No bank options available.");`                                                         | PASS       |
| Verify Total Amount with Tip        | User is on the Bank page    | Total amount should include the tip and match the expected value.                       | `Assert.assertEquals(bankPage.getTotalText(), expectedTotalText, "Total amount does not match!");`                                             | PASS       |
| Click Terms Checkbox                | User is on the Bank page    | The user should be able to select the Terms checkbox.                                   | `Assert.assertTrue(bankPage.isTermsCheckboxSelected(), "Terms checkbox is not selected.");`                                                       | PASS       |
| Proceed to Payment Submission       | User is on the Bank page    | The user should be able to submit the payment.                                         | `Assert.assertTrue(bankPage.isProceedButtonEnabled(), "Proceed button is not enabled.");`                                                        | PASS       |

---

### Conclusion

These test cases cover a broad range of functionalities, including navigation links, visibility of elements, and interactions across various pages in the application.
