package com.alexanthony.dreambumps.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the CrewMember entity.
 */
public class CrewMemberDTO implements Serializable {

    private Long id;

    @NotNull
    @Min(value = 1)
    @Max(value = 9)
    private Integer seat;

    @NotNull
    @Size(min = 1)
    private String name;

    private Long crewId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCrewId() {
        return crewId;
    }

    public void setCrewId(Long crewId) {
        this.crewId = crewId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CrewMemberDTO crewMemberDTO = (CrewMemberDTO) o;

        if ( ! Objects.equals(id, crewMemberDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CrewMemberDTO{" +
            "id=" + id +
            ", seat='" + seat + "'" +
            ", name='" + name + "'" +
            '}';
    }
}
