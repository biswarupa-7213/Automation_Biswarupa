package com.automation.Automation_Biswarupa;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class Run_Framework {

	private static String homeDir;
	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		Run_Framework.driver = driver;
	}

	protected static WebDriver driver;
	private static Connection con;
	private static Recordset rsController;
	private static String srNo;
	private static String runStatus;
	private static String dataSheetName;
	private static String browserName;
	private static String applicatioName;
	private static long maxExplicitWait;
	private static long maxImplicityWait;
	private static double tstartTime;
	private static String startTime;

	public static void main(String[] args) {
		GetDataFromController();
	}

	private static void GetDataFromController() {
		startTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		System.out.println("inside controller");
		homeDir = System.getProperty("user.dir");
		Fillo fillo = new Fillo();
		try {
			con = fillo.getConnection(homeDir + "/Controller.xlsx");
			rsController = con.executeQuery("select * from Controller where RunStatus = 'Y'");
			while(rsController.next()) {
				srNo = rsController.getField("SLNo");
				runStatus = rsController.getField("RunStatus");
				applicatioName = rsController.getField("Application_Name");
				dataSheetName = rsController.getField("Datasheet_Name");
				browserName = rsController.getField("Browser");
				maxExplicitWait = Long.parseLong(rsController.getField("Max_Explicit_Wait"));
				maxImplicityWait = Long.parseLong(rsController.getField("Max_Implicit_Wait"));
				System.out.println("applicatioName: " + applicatioName);
				System.out.println("dataSheetName: " + dataSheetName);
				////Result
				SaveResult.CreateResultFileAndFolder(applicatioName);
				BrowserConfiguration.configureBrowser();
				////Get data from datasheet
				ReadFromDatasheet.GetDataFromSheet();
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}

	public static double getStartTime() {
		return tstartTime;
	}

	public static void setStartTime(double tstartTime) {
		Run_Framework.tstartTime = tstartTime;
	}

	public static String getHomeDir() {
		return homeDir;
	}

	public static void setHomeDir(String homeDir) {
		Run_Framework.homeDir = homeDir;
	}

	public static Connection getCon() {
		return con;
	}

	public static void setCon(Connection con) {
		Run_Framework.con = con;
	}

	public static Recordset getRsController() {
		return rsController;
	}

	public static void setRsController(Recordset rsController) {
		Run_Framework.rsController = rsController;
	}

	public static String getSrNo() {
		return srNo;
	}

	public static void setSrNo(String srNo) {
		Run_Framework.srNo = srNo;
	}

	public static String getRunStatus() {
		return runStatus;
	}

	public static void setRunStatus(String runStatus) {
		Run_Framework.runStatus = runStatus;
	}

	public static String getDataSheetName() {
		return dataSheetName;
	}

	public static void setDataSheetName(String dataSheetName) {
		Run_Framework.dataSheetName = dataSheetName;
	}

	public static String getBrowserName() {
		return browserName;
	}

	public static void setBrowserName(String browserName) {
		Run_Framework.browserName = browserName;
	}

	public static String getApplicatioName() {
		return applicatioName;
	}

	public static void setApplicatioName(String applicatioName) {
		Run_Framework.applicatioName = applicatioName;
	}

	public static long getMaxExplicitWait() {
		return maxExplicitWait;
	}

	public static void setMaxExplicitWait(long maxExplicitWait) {
		Run_Framework.maxExplicitWait = maxExplicitWait;
	}

	public static long getMaxImplicityWait() {
		return maxImplicityWait;
	}

	public static void setMaxImplicityWait(long maxImplicityWait) {
		Run_Framework.maxImplicityWait = maxImplicityWait;
	}
	public static String getosname() {
		String osnamedump = System.getProperty("os.name");
		String osname = "Ubuntu";
		if (osnamedump.toUpperCase().contains("WIN")) {
			osname = "Windows";
		}
		return osname;

	}

	public static String getosarch() {
		String amddummp = System.getProperty("os.arch");
		String amdtype = "32";
		if (amddummp.toUpperCase().contains("64")) {
			amdtype = "64";
		}
		return amdtype;

	}


}