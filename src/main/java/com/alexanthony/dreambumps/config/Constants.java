package com.alexanthony.dreambumps.config;

/**
 * Application constants.
 */
public final class Constants {

    //Regex for acceptable logins
    public static final String LOGIN_REGEX = "^[_'.@A-Za-z0-9-]*$";

    public static final String SYSTEM_ACCOUNT = "system";
    public static final String ANONYMOUS_USER = "anonymoususer";
    
	public static final int CREWS_PER_DIVISION = 12;
	public static final int MENS_CREWS = 73;
	public static final int WOMENS_CREWS = 61;

    private Constants() {
    }
}
