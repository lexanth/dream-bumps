package com.alexanthony.dreambumps.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.alexanthony.dreambumps.domain.enumeration.Sex;

/**
 * A Crew.
 */
@Entity
@Table(name = "crew")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Crew implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sex", nullable = false)
    private Sex sex;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "crew")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CrewMember> crewMembers = new HashSet<>();

    @OneToMany(mappedBy = "crew")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CrewPriceHistory> crewPriceHistories = new HashSet<>();

    @OneToMany(mappedBy = "crew")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CrewPositionHistory> crewPositionHistories = new HashSet<>();
    
    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "price", precision=10, scale=2, nullable = false)
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Crew name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getSex() {
        return sex;
    }

    public Crew sex(Sex sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getImage() {
        return image;
    }

    public Crew image(String image) {
        this.image = image;
        return this;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<CrewMember> getCrewMembers() {
        return crewMembers;
    }

    public Crew crewMembers(Set<CrewMember> crewMembers) {
        this.crewMembers = crewMembers;
        return this;
    }

    public Crew addCrewMember(CrewMember crewMember) {
        this.crewMembers.add(crewMember);
        crewMember.setCrew(this);
        return this;
    }

    public Crew removeCrewMember(CrewMember crewMember) {
        this.crewMembers.remove(crewMember);
        crewMember.setCrew(null);
        return this;
    }

    public void setCrewMembers(Set<CrewMember> crewMembers) {
        this.crewMembers = crewMembers;
    }

    public Set<CrewPriceHistory> getCrewPriceHistories() {
        return crewPriceHistories;
    }

    public Crew crewPriceHistories(Set<CrewPriceHistory> crewPriceHistories) {
        this.crewPriceHistories = crewPriceHistories;
        return this;
    }

    public Crew addCrewPriceHistory(CrewPriceHistory crewPriceHistory) {
        this.crewPriceHistories.add(crewPriceHistory);
        crewPriceHistory.setCrew(this);
        return this;
    }

    public Crew removeCrewPriceHistory(CrewPriceHistory crewPriceHistory) {
        this.crewPriceHistories.remove(crewPriceHistory);
        crewPriceHistory.setCrew(null);
        return this;
    }

    public void setCrewPriceHistories(Set<CrewPriceHistory> crewPriceHistories) {
        this.crewPriceHistories = crewPriceHistories;
    }

    public Set<CrewPositionHistory> getCrewPositionHistories() {
        return crewPositionHistories;
    }

    public Crew crewPositionHistories(Set<CrewPositionHistory> crewPositionHistories) {
        this.crewPositionHistories = crewPositionHistories;
        return this;
    }

    public Crew addCrewPositionHistory(CrewPositionHistory crewPositionHistory) {
        this.crewPositionHistories.add(crewPositionHistory);
        crewPositionHistory.setCrew(this);
        return this;
    }

    public Crew removeCrewPositionHistory(CrewPositionHistory crewPositionHistory) {
        this.crewPositionHistories.remove(crewPositionHistory);
        crewPositionHistory.setCrew(null);
        return this;
    }

    public void setCrewPositionHistories(Set<CrewPositionHistory> crewPositionHistories) {
        this.crewPositionHistories = crewPositionHistories;
    }

    public BigDecimal getPrice() {
      return price;
    }

    public void setPrice(BigDecimal price) {
      this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Crew crew = (Crew) o;
        if (crew.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, crew.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Crew{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", sex='" + sex + "'" +
            ", image='" + image + "'" +
            '}';
    }
}
