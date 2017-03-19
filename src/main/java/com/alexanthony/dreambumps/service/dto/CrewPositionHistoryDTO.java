package com.alexanthony.dreambumps.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class CrewPositionHistoryDTO implements Serializable {
  private Long id;

  @NotNull
  private Integer day;
  
  @NotNull
  private Integer position;
  
  private Integer bumps;
  
  @NotNull
  private Long crewId;
  
  private BigDecimal dividend;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getDay() {
    return day;
  }

  public void setDay(Integer day) {
    this.day = day;
  }

  public Integer getPosition() {
    return position;
  }

  public void setPosition(Integer position) {
    this.position = position;
  }

  public Integer getBumps() {
    return bumps;
  }

  public void setBumps(Integer bumps) {
    this.bumps = bumps;
  }

  public Long getCrewId() {
    return crewId;
  }

  public void setCrewId(Long crewId) {
    this.crewId = crewId;
  }

  public BigDecimal getDividend() {
    return dividend;
  }

  public void setDividend(BigDecimal dividend) {
    this.dividend = dividend;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((bumps == null) ? 0 : bumps.hashCode());
    result = prime * result + ((crewId == null) ? 0 : crewId.hashCode());
    result = prime * result + ((day == null) ? 0 : day.hashCode());
    result = prime * result + ((dividend == null) ? 0 : dividend.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((position == null) ? 0 : position.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    CrewPositionHistoryDTO other = (CrewPositionHistoryDTO) obj;
    if (bumps == null) {
      if (other.bumps != null)
        return false;
    } else if (!bumps.equals(other.bumps))
      return false;
    if (crewId == null) {
      if (other.crewId != null)
        return false;
    } else if (!crewId.equals(other.crewId))
      return false;
    if (day == null) {
      if (other.day != null)
        return false;
    } else if (!day.equals(other.day))
      return false;
    if (dividend == null) {
      if (other.dividend != null)
        return false;
    } else if (!dividend.equals(other.dividend))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (position == null) {
      if (other.position != null)
        return false;
    } else if (!position.equals(other.position))
      return false;
    return true;
  }
}
