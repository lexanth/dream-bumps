package com.alexanthony.dreambumps.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.alexanthony.dreambumps.domain.enumeration.Sex;

public class UserCrewPriceDTO implements Serializable {

  private Long id;

  @NotNull
  private Sex sex;

  private Long userId;

  private BigDecimal value;
  private BigDecimal cash;
  private Integer bumps;
  private BigDecimal dividends;

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public Sex getSex() {
    return sex;
  }
  public void setSex(Sex sex) {
    this.sex = sex;
  }
  public Long getUserId() {
    return userId;
  }
  public void setUserId(Long userId) {
    this.userId = userId;
  }
  public BigDecimal getValue() {
    return value;
  }
  public void setValue(BigDecimal value) {
    this.value = value;
  }
  public BigDecimal getCash() {
    return cash;
  }
  public void setCash(BigDecimal cash) {
    this.cash = cash;
  }

  public Integer getBumps() {
    return bumps;
  }

  public void setBumps(Integer bumps) {
    this.bumps = bumps;
  }

  public BigDecimal getDividends() {
    return dividends;
  }

  public void setDividends(BigDecimal dividends) {
    this.dividends = dividends;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
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
    UserCrewPriceDTO other = (UserCrewPriceDTO) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }
}
