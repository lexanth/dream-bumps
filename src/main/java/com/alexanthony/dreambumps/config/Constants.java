package com.alexanthony.dreambumps.config;

import java.math.BigDecimal;

import com.alexanthony.dreambumps.domain.enumeration.Sex;

/**
 * Application constants.
 */
public final class Constants {

  // A lot of this is actually config stuff, not constant. TODO

    //Regex for acceptable logins
    public static final String LOGIN_REGEX = "^[_'.@A-Za-z0-9-]*$";

    public static final String SYSTEM_ACCOUNT = "system";
    public static final String ANONYMOUS_USER = "anonymoususer";

	public static final int CREWS_PER_DIVISION = 13;
	public static final int MENS_CREWS = 92;
	public static final int WOMENS_CREWS = 79;

  public static final BigDecimal THREE_HUNDRED = new BigDecimal("300");
  public static final BigDecimal MULTIPLIER = new BigDecimal("66");

  public static final BigDecimal ROWOVER_DIVIDEND = new BigDecimal("0.3");
  public static final BigDecimal BUMP_MULTIPLIER = new BigDecimal("3");
  public static final BigDecimal HEADSHIP_MULTIPLIER = new BigDecimal("3");

  public static final String REGATTA_NAME = "Eights 2017";


    private Constants() {
    }

  public static Integer getNumberOfCrewsForSex(Sex sex) {
    if (sex == Sex.male) {
      return MENS_CREWS;
    }
    return WOMENS_CREWS;
  }
}
