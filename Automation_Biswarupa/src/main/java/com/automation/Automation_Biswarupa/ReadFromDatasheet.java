package com.automation.Automation_Biswarupa;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class ReadFromDatasheet {

	private static Connection conDatasheet;
	private static Recordset rsDatasheet;
	private static String locator;
	private static String locatorType;
	private static String srNO;
	private static String action;
	private static String testCaseType;
	private static String description;
	private static String pageName;
	private static String dataValue;
	private static String applicationName;

	public static void GetDataFromSheet() {
		Fillo fillo = new Fillo();
		try {
			conDatasheet = fillo
					.getConnection(Run_Framework.getHomeDir() + "/Datasheets/" + Run_Framework.getDataSheetName());
			rsDatasheet = conDatasheet.executeQuery("select * from Sheet1 where RunStatus = 'Y'");

			while (rsDatasheet.next()) {
				srNO = rsDatasheet.getField("slNo");
				locator = rsDatasheet.getField("locator");
				locatorType = rsDatasheet.getField("locatorType");
				action = rsDatasheet.getField("action");
				testCaseType=  rsDatasheet.getField("testCaseType");
				description=  rsDatasheet.getField("description");
				pageName=  rsDatasheet.getField("pageName");
				dataValue= rsDatasheet.getField("dataValue");
				applicationName =rsDatasheet.getField("applicationName");
				/////Validate
				Validator.Validate(action,locator,locatorType);
			}
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConDatasheet() {
		return conDatasheet;
	}

	public static void setConDatasheet(Connection conDatasheet) {
		ReadFromDatasheet.conDatasheet = conDatasheet;
	}

	public static Recordset getRsDatasheet() {
		return rsDatasheet;
	}

	public static void setRsDatasheet(Recordset rsDatasheet) {
		ReadFromDatasheet.rsDatasheet = rsDatasheet;
	}

	public static String getLocator() {
		return locator;
	}

	public static void setLocator(String locator) {
		ReadFromDatasheet.locator = locator;
	}

	public static String getLocatorType() {
		return locatorType;
	}

	public static void setLocatorType(String locatorType) {
		ReadFromDatasheet.locatorType = locatorType;
	}

	public static String getSrNO() {
		return srNO;
	}

	public static void setSrNO(String srNO) {
		ReadFromDatasheet.srNO = srNO;
	}

	public static String getAction() {
		return action;
	}

	public static void setAction(String action) {
		ReadFromDatasheet.action = action;
	}

	public static String getTestCaseType() {
		return testCaseType;
	}

	public static void setTestCaseType(String testCaseType) {
		ReadFromDatasheet.testCaseType = testCaseType;
	}

	public static String getDescription() {
		return description;
	}

	public static void setDescription(String description) {
		ReadFromDatasheet.description = description;
	}

	public static String getPageName() {
		return pageName;
	}

	public static void setPageName(String pageName) {
		ReadFromDatasheet.pageName = pageName;
	}

	public static String getDataValue() {
		return dataValue;
	}

	public static void setDataValue(String dataValue) {
		ReadFromDatasheet.dataValue = dataValue;
	}

	public static String getApplicationName() {
		return applicationName;
	}

	public static void setApplicationName(String applicationName) {
		ReadFromDatasheet.applicationName = applicationName;
	}

}