package com.alexanthony.dreambumps.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

import com.alexanthony.dreambumps.domain.enumeration.Sex;

/**
 * A UserCrewPriceHistory.
 */
@Entity
@Table(name = "user_crew_price_history")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserCrewPriceHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sex", nullable = false)
    private Sex sex;

    @NotNull
    @Column(name = "date_time", nullable = false)
    private ZonedDateTime dateTime;

    @NotNull
    @Column(name = "value", precision=10, scale=2, nullable = false)
    private BigDecimal value;

    @NotNull
    @Column(name = "cash", precision=10, scale=2, nullable = false)
    private BigDecimal cash;

    @ManyToOne(optional = false)
    @NotNull
    private User user;
    
    public UserCrewPriceHistory() {
      
    }

    public UserCrewPriceHistory(UserCrewPrice userCrewPrice) {
      this.sex = userCrewPrice.getSex();
      this.value = userCrewPrice.getValue();
      this.cash = userCrewPrice.getCash();
      this.user = userCrewPrice.getUser();
      this.dateTime = ZonedDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sex getSex() {
        return sex;
    }

    public UserCrewPriceHistory sex(Sex sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    public UserCrewPriceHistory dateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public void setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getValue() {
        return value;
    }

    public UserCrewPriceHistory value(BigDecimal value) {
        this.value = value;
        return this;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public UserCrewPriceHistory cash(BigDecimal cash) {
        this.cash = cash;
        return this;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public User getUser() {
        return user;
    }

    public UserCrewPriceHistory user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserCrewPriceHistory userCrewPriceHistory = (UserCrewPriceHistory) o;
        if (userCrewPriceHistory.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, userCrewPriceHistory.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "UserCrewPriceHistory{" +
            "id=" + id +
            ", sex='" + sex + "'" +
            ", dateTime='" + dateTime + "'" +
            ", value='" + value + "'" +
            ", cash='" + cash + "'" +
            '}';
    }
}
