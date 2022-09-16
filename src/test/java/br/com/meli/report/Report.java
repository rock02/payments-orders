package br.com.meli.report;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.extension.ExtensionContext;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Report {

	public static ExtentReports reports = null;
	public static ExtentTest extentTest;

	private static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();

	public static ExtentReports init(ExtensionContext context) {
		
		String filename = System.getProperty("user.dir") + "/test-output/" + context.getDisplayName() + "_Results.html";
        
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filename);
        
        reports = new ExtentReports();
        
        reports.attachReporter(htmlReporter);
        
        return reports;
	}
	
	public static ExtentReports getExtentReports() {
		return reports;
	}
	
	public static synchronized ExtentTest fetchTest() {
		return extentTestMap.get((int) (Thread.currentThread().getId()));
	}
	
	public static synchronized ExtentTest createTest(ExtensionContext context) {
		extentTest = reports.createTest(context.getTestClass().get().getSimpleName())
				.createNode(context.getTestMethod().get().getName());
		extentTest.log(Status.INFO, context.getDisplayName() + " - started");
		extentTestMap.put((int) (Thread.currentThread().getId()), extentTest);
		return extentTest;
	}
}
