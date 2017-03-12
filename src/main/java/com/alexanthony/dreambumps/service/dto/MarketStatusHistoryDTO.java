package com.alexanthony.dreambumps.service.dto;


import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the MarketStatusHistory entity.
 */
public class MarketStatusHistoryDTO implements Serializable {

    private Long id;

    private ZonedDateTime dateTime;

    @NotNull
    @Min(value = 0)
    @Max(value = 4)
    private Integer day;

    @NotNull
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

    public void setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }
    public Boolean getOpen() {
        return open;
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

        MarketStatusHistoryDTO marketStatusHistoryDTO = (MarketStatusHistoryDTO) o;

        if ( ! Objects.equals(id, marketStatusHistoryDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MarketStatusHistoryDTO{" +
            "id=" + id +
            ", dateTime='" + dateTime + "'" +
            ", day='" + day + "'" +
            ", open='" + open + "'" +
            '}';
    }
}
