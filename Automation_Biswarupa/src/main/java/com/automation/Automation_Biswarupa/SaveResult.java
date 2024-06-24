package com.automation.Automation_Biswarupa;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;

import ru.yandex.qatools.ashot.AShot;

public class SaveResult {

	private static String logsPath;
	private static String screenshotPath;
	private static String logFilePath;
	private static File csvfile;
	private static String screenshotFileLocation;
	private static String screenshotFileLocation1;

	public static void CreateResultFileAndFolder(String applicatioName) {
		Date now = new Date();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		String monthName = new SimpleDateFormat("MMM").format(now);
		int monthday = Calendar.getInstance().get(Calendar.DATE);
		System.out.println("inside the CreateResultFileAndFolder");
		logsPath = Run_Framework.getHomeDir() + "/Reports/Web/ExcelReport_" + monthday + " " + monthName + " "
				+ year + "/" + applicatioName + "/";
		screenshotPath = Run_Framework.getHomeDir() + "/Reports/Web/ExcelReport_" + monthday + " " + monthName
				+ " " + year + "/" + applicatioName + "/Screenshots/";
		System.out.println("logs path :- " + logsPath);
		System.out.println("Scrennshot path :- " + screenshotPath);
		new File(logsPath).mkdirs();
		new File(screenshotPath).mkdirs();
		logFilePath = logsPath + applicatioName + ".csv";
		csvfile = new File(logFilePath);
		if (!csvfile.exists()) {
			try {
					System.out.println("----------------- Writting Header in CSV --------------------");
					csvfile.createNewFile();
					FileWriter fileWritter = new FileWriter(csvfile, true);
					BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
					bufferWritter.write(
							"Scenario Description, Scenario ID, Testcase Description,Testcase ID, TestCase Type, Actual Result, Expected Result, Status, Screenshot,Step_Description,Step No, Date, Time_Value\n");
					bufferWritter.close();

				}catch(Exception e) {
					e.printStackTrace();
				}
		}
	}

	public static String getLogsPath() {
		return logsPath;
	}

	public static void setLogsPath(String logsPath) {
		SaveResult.logsPath = logsPath;
	}

	public static String getScreenshotPath() {
		return screenshotPath;
	}

	public static void setScreenshotPath(String screenshotPath) {
		SaveResult.screenshotPath = screenshotPath;
	}

	public static String getLogFilePath() {
		return logFilePath;
	}

	public static void setLogFilePath(String logFilePath) {
		SaveResult.logFilePath = logFilePath;
	}

	public static File getCsvfile() {
		return csvfile;
	}

	public static void setCsvfile(File csvfile) {
		SaveResult.csvfile = csvfile;
	}

	public static void Result(String expectedValue, String actualValue) {
		System.out.println("-----------------------Saving Logs--------------------------------");
		Date now = new Date();
		Calendar calender = Calendar.getInstance();
		double responseTime = (calender.getTimeInMillis()) - (Run_Framework.getStartTime());
		double tTotalTime = responseTime / 1000;
		String totalTimeRounded = String.format("%.2f", tTotalTime);
		calender.setTime(now);
		int iMonth = calender.get(Calendar.MONTH) + 1;
		String currentTimeDB = Calendar.getInstance().get(Calendar.YEAR) + "-" + iMonth + "-"
				+ calender.get(Calendar.DATE) + " " + calender.get(Calendar.HOUR_OF_DAY) + ":"
				+ calender.get(Calendar.MINUTE) + ":" + calender.get(Calendar.SECOND);
		String tSystemTime = calender.get(Calendar.HOUR_OF_DAY) + ":" + calender.get(Calendar.MINUTE) + ":"
				+ calender.get(Calendar.SECOND) + " ";
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		String date = format.format(now);
		String status;
		if (expectedValue.equalsIgnoreCase(actualValue)) {
			status = "PASS";

		} else {
			status = "FAIL";
		}
		try {
			FileWriter fileWritter = new FileWriter(csvfile, true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			totalTimeRounded = String.format("%.2f", tTotalTime);

			bufferWritter.write(ReadFromDatasheet.getDescription() + ","
					+ ReadFromDatasheet.getDescription() + "," + ReadFromDatasheet.getDescription()
					+ "," + ReadFromDatasheet.getDescription() + "," + ReadFromDatasheet.getDescription()
					+ "," + actualValue + "," + expectedValue + "," + status + "," + "=HYPERLINK(\""
					+ SaveResult.getScreenshotFileLocation1() + "\")" + ","
					+ ReadFromDatasheet.getDescription() + "," + ReadFromDatasheet.getSrNO() + ","
					+ tSystemTime + "," + tSystemTime + "\n");
			bufferWritter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public static String getScreenshotFileLocation() {
		return screenshotFileLocation;
	}

	public static void setScreenshotFileLocation(String screenshotFileLocation) {
		SaveResult.screenshotFileLocation = screenshotFileLocation;
	}

	public static String getScreenshotFileLocation1() {
		return screenshotFileLocation1;
	}

	public static void setScreenshotFileLocation1(String screenshotFileLocation1) {
		SaveResult.screenshotFileLocation1 = screenshotFileLocation1;
	}

	public static void TakeScrennshot() {
		try {
			BufferedImage screenShot;
			// BufferedImage screenShot = new
			// AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver).getImage();
				screenShot = new AShot().takeScreenshot(Run_Framework.getDriver()).getImage();
			Date now = new Date();
			int year = Calendar.getInstance().get(Calendar.YEAR);
			String monthName = new SimpleDateFormat("MMM").format(now);
			int monthday = Calendar.getInstance().get(Calendar.DATE);

			String file1;
					file1 = Run_Framework.getHomeDir() + "/Reports/Web/ExcelReport_" + monthday + " " + monthName + " "
							+ year + "/" + Run_Framework.getApplicatioName() + "/Screenshots/";
			String file = "./Screenshots/";

			Date nowScreen = new Date();
			@SuppressWarnings("deprecation")
			String ScreenShotTime = ReadFromDatasheet.getPageName() + "" + nowScreen.getHours() + ""
					+ nowScreen.getMinutes() + "_" + nowScreen.getSeconds();
			screenshotFileLocation = file + ScreenShotTime + ".png";
			screenshotFileLocation1 = file1 + ScreenShotTime + ".png";
			ImageIO.write(screenShot, "PNG", new File(screenshotFileLocation1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
