package com.alexanthony.dreambumps.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import com.alexanthony.dreambumps.domain.enumeration.Sex;

/**
 * A UserCrewPositionHistory.
 */
@Entity
@Table(name = "user_crew_position_history")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserCrewPositionHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sex", nullable = false)
    private Sex sex;

    @NotNull
    @Min(value = 1)
    @Max(value = 4)
    @Column(name = "day", nullable = false)
    private Integer day;

    @NotNull
    @Min(value = 1)
    @Max(value = 9)
    @Column(name = "seat", nullable = false)
    private Integer seat;

    @NotNull
    @Column(name = "bumps", nullable = false)
    private Integer bumps;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "dividend", precision=10, scale=2, nullable = false)
    private BigDecimal dividend;

    @ManyToOne(optional = false)
    @NotNull
    private User user;

    @ManyToOne(optional = false)
    @NotNull
    private Crew crew;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sex getSex() {
        return sex;
    }

    public UserCrewPositionHistory sex(Sex sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Integer getDay() {
        return day;
    }

    public UserCrewPositionHistory day(Integer day) {
        this.day = day;
        return this;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getSeat() {
        return seat;
    }

    public UserCrewPositionHistory seat(Integer seat) {
        this.seat = seat;
        return this;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    public Integer getBumps() {
        return bumps;
    }

    public UserCrewPositionHistory bumps(Integer bumps) {
        this.bumps = bumps;
        return this;
    }

    public void setBumps(Integer bumps) {
        this.bumps = bumps;
    }

    public BigDecimal getDividend() {
        return dividend;
    }

    public UserCrewPositionHistory dividend(BigDecimal dividend) {
        this.dividend = dividend;
        return this;
    }

    public void setDividend(BigDecimal dividend) {
        this.dividend = dividend;
    }

    public User getUser() {
        return user;
    }

    public UserCrewPositionHistory user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Crew getCrew() {
        return crew;
    }

    public UserCrewPositionHistory crew(Crew crew) {
        this.crew = crew;
        return this;
    }

    public void setCrew(Crew crew) {
        this.crew = crew;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserCrewPositionHistory userCrewPositionHistory = (UserCrewPositionHistory) o;
        if (userCrewPositionHistory.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, userCrewPositionHistory.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "UserCrewPositionHistory{" +
            "id=" + id +
            ", sex='" + sex + "'" +
            ", day='" + day + "'" +
            ", seat='" + seat + "'" +
            ", bumps='" + bumps + "'" +
            ", dividend='" + dividend + "'" +
            '}';
    }
}
