package com.alexanthony.dreambumps.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A CrewPositionHistory.
 */
@Entity
@Table(name = "crew_position_history")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CrewPositionHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(value = 0)
    @Max(value = 4)
    @Column(name = "day", nullable = false)
    private Integer day;

    @NotNull
    @Min(value = 1)
    @Column(name = "position", nullable = false)
    private Integer position;

    @NotNull
    @Column(name = "bumps", nullable = false)
    private Integer bumps;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "dividend", precision=10, scale=2, nullable = false)
    private BigDecimal dividend;

    @ManyToOne(optional = false)
    @NotNull
    private Crew crew;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDay() {
        return day;
    }

    public CrewPositionHistory day(Integer day) {
        this.day = day;
        return this;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getPosition() {
        return position;
    }

    public CrewPositionHistory position(Integer position) {
        this.position = position;
        return this;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getBumps() {
        return bumps;
    }

    public CrewPositionHistory bumps(Integer bumps) {
        this.bumps = bumps;
        return this;
    }

    public void setBumps(Integer bumps) {
        this.bumps = bumps;
    }

    public BigDecimal getDividend() {
        return dividend;
    }

    public CrewPositionHistory dividend(BigDecimal dividend) {
        this.dividend = dividend;
        return this;
    }

    public void setDividend(BigDecimal dividend) {
        this.dividend = dividend;
    }

    public Crew getCrew() {
        return crew;
    }

    public CrewPositionHistory crew(Crew crew) {
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
        CrewPositionHistory crewPositionHistory = (CrewPositionHistory) o;
        if (crewPositionHistory.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, crewPositionHistory.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CrewPositionHistory{" +
            "id=" + id +
            ", day='" + day + "'" +
            ", position='" + position + "'" +
            ", bumps='" + bumps + "'" +
            ", dividend='" + dividend + "'" +
            '}';
    }
}
