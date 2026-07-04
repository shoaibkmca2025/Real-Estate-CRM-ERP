package com.bcs.utility;

/**
 * Application constants.
 *
 * Previously these hardcoded a Windows upload folder, the old UAT server IP
 * (132.148.21.45) and the sender address. They are now overridable via
 * environment variables (or the backend's .env file) with the legacy values
 * kept as defaults, so behaviour is unchanged where nothing is configured:
 *
 *   FILE_SAVE_LOCATION   - folder where uploads/PDFs are written
 *   SERVER_IMG_LOCATION  - public base URL used to serve those files
 *   MAIL_FROM            - "from" address for outgoing mail
 */
public class ConstantsUtil {

	private static String env(String key, String fallback) {
		String v = System.getenv(key);
		return (v == null || v.isBlank()) ? fallback : v;
	}

	public static final String FILE_SAVE_LOCATION  = env("FILE_SAVE_LOCATION",
			"C:\\Users\\ysmvts\\sms_images\\BuilderAppUat\\");

	public static final String SERVER_IMG_LOCATION = env("SERVER_IMG_LOCATION",
			"http://132.148.21.45:8080/images/BuilderAppUat/");

	public static final String FROM                = env("MAIL_FROM", "savita@ysmsoftware.com");

	public static final int sessionInvalidateDuration = 86400; // in ms (24 hrs )

	public static final int    MIN_LEN             =  6;
	public static final int    MAX_LEN             =  8;
	public static final int    NO_OF_CAPS_ALPHA    =  2;

	public static final int    NO_OF_DIGITS        =  2;
	public static final int    NO_OF_SPL_CHARS     =  2;

	public static final String LINE_CHART_URL = env("LINE_CHART_URL",
			"http://132.148.21.45:8080/BuilderAppAngular/getAllDashboadDetails/");

}
