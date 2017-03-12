package com.alexanthony.dreambumps.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A MarketStatusHistory.
 */
@Entity
@Table(name = "market_status_history")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MarketStatusHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "date_time", nullable = false)
    private ZonedDateTime dateTime;

    @NotNull
    @Min(value = 0)
    @Max(value = 4)
    @Column(name = "day", nullable = false)
    private Integer day;

    @NotNull
    @Column(name = "open", nullable = false)
    private Boolean open;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    public MarketStatusHistory dateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public void setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getDay() {
        return day;
    }

    public MarketStatusHistory day(Integer day) {
        this.day = day;
        return this;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Boolean isOpen() {
        return open;
    }

    public MarketStatusHistory open(Boolean open) {
        this.open = open;
        return this;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MarketStatusHistory marketStatusHistory = (MarketStatusHistory) o;
        if (marketStatusHistory.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, marketStatusHistory.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MarketStatusHistory{" +
            "id=" + id +
            ", dateTime='" + dateTime + "'" +
            ", day='" + day + "'" +
            ", open='" + open + "'" +
            '}';
    }
}
