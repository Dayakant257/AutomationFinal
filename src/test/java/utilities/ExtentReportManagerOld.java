package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DataSourceResolver;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.TestBaseClass;

public class ExtentReportManagerOld implements ITestListener{

	public ExtentSparkReporter extentSparkReporter;
	public ExtentReports extentReport;
	public ExtentTest extentTest;
	String reportName;
	
	public void onStart(ITestContext testContext) {
		/*SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date date = new Date();
		String currentDateTimeStamp =  df.format(date); */
		
		String currentDateTimeStamp = new SimpleDateFormat("yyyy.dd.HH.mm.ss").format(new Date());
		reportName = "Test-Report-"+currentDateTimeStamp+".html";
		extentSparkReporter = new ExtentSparkReporter(".\\reports\\"+reportName);
		
		extentSparkReporter.config().setDocumentTitle("Opencart Automation Test Report");
		extentSparkReporter.config().setReportName("Opencart functional testing");
		extentSparkReporter.config().setTheme(Theme.DARK);
		
		extentReport =  new ExtentReports();
		extentReport.attachReporter(extentSparkReporter);
		extentReport.setSystemInfo("Application", "Opencart");
		extentReport.setSystemInfo("Module", "Admin");
		extentReport.setSystemInfo("Sub Module", "Customers");
		extentReport.setSystemInfo("User Name", System.getProperty("user.name"));
		extentReport.setSystemInfo("Environment", "QA");
		
		//textContext.getCurrentXmlTest() returns the xml file being executed
		String os = testContext.getCurrentXmlTest().getParameter("os");
		extentReport.setSystemInfo("Operating System", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extentReport.setSystemInfo("Browser", browser);
		
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extentReport.setSystemInfo("Groups", includedGroups.toString());
		}
		/*
		else {
			extentReport.setSystemInfo("Groups","No groups specified in the xml test file" );
		}*/
	}
	
	public void OnTestSuccess(ITestResult testResult) {
		extentTest = extentReport.createTest(testResult.getTestClass().getName());
		extentTest.assignCategory(testResult.getMethod().getGroups());
		
		extentTest.log(Status.PASS, testResult.getName()+" got successfully executed");
	}
	
	public void onTestFailure(ITestResult testResult) {
		extentTest = extentReport.createTest(testResult.getTestClass().getName());
		extentTest.assignCategory(testResult.getMethod().getGroups());
		
		extentTest.log(Status.FAIL, testResult.getName()+" got Failed");
		extentTest.log(Status.INFO, testResult.getThrowable().getMessage());
		
		
		try {
		
			String imgPath = new TestBaseClass().captureScreen(testResult.getName());
			extentTest.addScreenCaptureFromPath(imgPath);
		}
		catch(IOException e1) {
			e1.printStackTrace();
		}
		
		
	}
	
	public void onTestSkipped(ITestResult testResult) {
		extentTest = extentReport.createTest(testResult.getClass().getName());
		extentTest.assignCategory(testResult.getMethod().getGroups());
		extentTest.log(Status.SKIP, testResult.getName()+" got skipped");
		extentTest.log(Status.INFO, testResult.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext testContext) {
		extentReport.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+reportName;
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		/*
		//Sending out report via email
		try {
			URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+reportName);
			ImageHtmlEmail email = new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("vickyshrm257@mailinator.com", "password"));
			email.setSSLOnConnect(true);
			email.setFrom("vickyshrm257@mailinator.com");
			email.setSubject("Automation Test Results");
			email.setMsg("Please find attached report for the test execution.");
			email.addTo("qadaya@mailinator.com");
			email.attach(url,"extent report", "Please check report...");
			email.send();
		}
		catch(Exception e) {
			e.printStackTrace();
		} */
	}
	
	
}
