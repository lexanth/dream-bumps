package com.alexanthony.dreambumps.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

import com.alexanthony.dreambumps.domain.enumeration.Sex;

/**
 * A UserCrewMember.
 */
@Entity
@Table(name = "user_crew_member")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserCrewMember implements Serializable {

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
    @Max(value = 9)
    @Column(name = "seat", nullable = false)
    private Integer seat;

    @ManyToOne(optional = false)
    @NotNull
    private User user;

    @ManyToOne
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

    public UserCrewMember sex(Sex sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Integer getSeat() {
        return seat;
    }

    public UserCrewMember seat(Integer seat) {
        this.seat = seat;
        return this;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    public User getUser() {
        return user;
    }

    public UserCrewMember user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Crew getCrew() {
        return crew;
    }

    public UserCrewMember crew(Crew crew) {
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
        UserCrewMember userCrewMember = (UserCrewMember) o;
        if (userCrewMember.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, userCrewMember.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "UserCrewMember{" +
            "id=" + id +
            ", sex='" + sex + "'" +
            ", seat='" + seat + "'" +
            '}';
    }
}
