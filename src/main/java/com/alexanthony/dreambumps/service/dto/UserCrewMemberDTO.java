package com.alexanthony.dreambumps.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import com.alexanthony.dreambumps.domain.enumeration.Sex;

/**
 * A DTO for the UserCrewMember entity.
 */
public class UserCrewMemberDTO implements Serializable {

    private Long id;

    @NotNull
    private Sex sex;

    @NotNull
    @Min(value = 1)
    @Max(value = 9)
    private Integer seat;

    private Long userId;

    private Long crewId;

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
    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

        UserCrewMemberDTO userCrewMemberDTO = (UserCrewMemberDTO) o;

        if ( ! Objects.equals(id, userCrewMemberDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "UserCrewMemberDTO{" +
            "id=" + id +
            ", sex='" + sex + "'" +
            ", seat='" + seat + "'" +
            '}';
    }
}
