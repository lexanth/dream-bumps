package com.alexanthony.dreambumps.service.dto;

import java.io.Serializable;

import com.alexanthony.dreambumps.domain.enumeration.Sex;

public class TransactionDTO implements Serializable {
	/**
   * 
   */
  private static final long serialVersionUID = -8223244111009800655L;
  private Long userCrewMemberId;
	private Long crewId;

	public Long getCrewId() {
		return crewId;
	}

	public void setCrewId(Long crewId) {
		this.crewId = crewId;
	}

  public Long getUserCrewMemberId() {
    return userCrewMemberId;
  }

  public void setUserCrewMemberId(Long userCrewMemberId) {
    this.userCrewMemberId = userCrewMemberId;
  }
}
