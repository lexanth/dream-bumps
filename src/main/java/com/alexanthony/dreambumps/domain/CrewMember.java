package com.alexanthony.dreambumps.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A CrewMember.
 */
@Entity
@Table(name = "crew_member")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CrewMember implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(value = 1)
    @Max(value = 9)
    @Column(name = "seat", nullable = false)
    private Integer seat;

    @NotNull
    @Size(min = 1)
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(optional = false)
    @NotNull
    private Crew crew;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSeat() {
        return seat;
    }

    public CrewMember seat(Integer seat) {
        this.seat = seat;
        return this;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    public String getName() {
        return name;
    }

    public CrewMember name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Crew getCrew() {
        return crew;
    }

    public CrewMember crew(Crew crew) {
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
        CrewMember crewMember = (CrewMember) o;
        if (crewMember.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, crewMember.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CrewMember{" +
            "id=" + id +
            ", seat='" + seat + "'" +
            ", name='" + name + "'" +
            '}';
    }
}
