package br.com.meli;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import br.com.meli.conf.Property;
import br.com.meli.report.Report;
import io.restassured.RestAssured;

public class BaseTest
		implements BeforeAllCallback, BeforeTestExecutionCallback, AfterAllCallback, AfterTestExecutionCallback {

	public void beforeAll(ExtensionContext context) throws Exception {
		Property.loadProperties("prd");
		RestAssured.baseURI = Property.URL_API;

		if (Report.getExtentReports() == null) {

			Report.init(context);
		}
	}

	public void beforeTestExecution(ExtensionContext context) throws Exception {
		Report.createTest(context);

	}

	public void afterTestExecution(ExtensionContext context) throws Exception {
		if (!context.getExecutionException().isPresent()) {
			Report.extentTest.pass(context.getDisplayName() + " - passed");
		} else {
			Report.extentTest.fail(context.getExecutionException().get().getLocalizedMessage());
		}
	}

	public void afterAll(ExtensionContext context) throws Exception {
		Report.reports.flush();
	}
}
