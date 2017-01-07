package com.dm.client;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 
 * @author ercmt
 */
public class Debug {
	public static boolean debugOn = true;

	public static boolean exceptionsOn = true;

	public static void println(String message) {
		if (debugOn)
			System.out.println(">> " + message);
	}

	public static void println(Exception e) {
		if (exceptionsOn)
			System.out.println("EXCEPTION!! " + e.toString());
	}

	public static void printStackTrace(Exception e) {
		if (debugOn) {
			System.out.println("-- Start Exception Stack Trace (" + new Date()
					+ ") --");
			System.out.println();
			// e.printStackTrace(System.out);
			System.out.println(e.getMessage());
			System.out.println();
			System.out.println("-- End Exception Stack Trace --");
			System.out.println();
		}
	}

	public static void println(String message, Exception e) {
		if (exceptionsOn)
			System.out.println("EXCEPTION!! " + message + " [" + e.toString()
					+ "]");
	}

	public static void log(String message, String page) {
		try {
			/*
			 * BufferedWriter writer = null; String filename = "LOGGER"; File
			 * logFile = new
			 * File("/data/app/ecomm/UMP_IMS_ERecruitment/"+filename); writer =
			 * new BufferedWriter(new FileWriter(logFile, true));
			 * writer.write(message);
			 */
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			DataQuery dq = new DataQuery("Debug.java");
			String query = "UPDATE ERCMT_SETTING SET ES_LOG=?";
			String data[] = new String[1];
			data[0] = "[" + sdf.format(cal.getTime()) + "] / " + "[" + page
					+ "] / [" + message + "]";
			dq.setData(query, data);
			dq.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Close the writer regardless of what happens...
				// writer.close();
			} catch (Exception e) {
			}
		}
	}
}
