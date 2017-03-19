package com.alexanthony.dreambumps.service.dto;

import java.io.Serializable;

public class ConfigDTO implements Serializable {
	/**
   * 
   */
  private static final long serialVersionUID = -4917529693951855015L;
  private int crewsPerDivision;
	private int mensCrews;
	private int womensCrews;
	private String regatta;
	
	public int getCrewsPerDivision() {
		return crewsPerDivision;
	}
	public void setCrewsPerDivision(int crewsPerDivision) {
		this.crewsPerDivision = crewsPerDivision;
	}
	public int getMensCrews() {
		return mensCrews;
	}
	public void setMensCrews(int mensCrews) {
		this.mensCrews = mensCrews;
	}
	public int getWomensCrews() {
		return womensCrews;
	}
	public void setWomensCrews(int womensCrews) {
		this.womensCrews = womensCrews;
	}
  public String getRegatta() {
    return regatta;
  }
  public void setRegatta(String regatta) {
    this.regatta = regatta;
  }
}
