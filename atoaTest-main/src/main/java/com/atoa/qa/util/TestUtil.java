package com.atoa.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.Properties;


import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.atoa.qa.base.TestBase;

public class TestUtil extends TestBase {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;

	public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir") + "/src/main/java/com/atoa/qa/testdata/FreeTestData.xlsx";

	static Workbook book;
	static Sheet sheet;
	static JavascriptExecutor js;

	public void switchToFrame(String frameId) {
		driver.switchTo().frame(frameId);
	}

	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		// System.out.println(sheet.getLastRowNum() + "--------" +
		// sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				// System.out.println(data[i][k]);
			}
		}
		return data;
	}

	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}
	
	 public static void sendEmail() {
	        Properties props = new Properties();
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");

	        // Create a session with authentication
	        Session session = Session.getInstance(props, new Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication("shubhjeetrajput@gmail.com", "pyfo aezh fzka vell");
	            }
	        });

	        try {
	            // Create a default MimeMessage object
	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress("shubhjeetrajput@gmail.com"));
	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("sanjay.singh.aus84@gmail.com"));
	            message.setSubject("ATOA Smoke Test Report");

	            // Create a multipart message
	            Multipart multipart = new MimeMultipart();

	            // Create the message part
	            MimeBodyPart messageBodyPart = new MimeBodyPart();
	            messageBodyPart.setText("Test email with attachment");

	            // Add message part to multipart
	            multipart.addBodyPart(messageBodyPart);

	            // Create the attachment part
	            MimeBodyPart attachmentPart = new MimeBodyPart();
	            DataSource source = new FileDataSource(System.getProperty("user.dir")
	            		+ "/test-output/emailable-report.html"); // Provide the file path
	            attachmentPart.setDataHandler(new DataHandler(source));
	            attachmentPart.setFileName(source.getName());

	            // Add attachment part to multipart
	            multipart.addBodyPart(attachmentPart);

	            // Set the content of the email to be the multipart
	            message.setContent(multipart);

	            // Send the message
	            Transport.send(message);

	            System.out.println("Email sent successfully!");

	        } catch (MessagingException e) {
	            throw new RuntimeException(e);
	        }
	    }
	

}