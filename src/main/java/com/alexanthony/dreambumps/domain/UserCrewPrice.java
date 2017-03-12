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
 * A UserCrewPrice.
 */
@Entity
@Table(name = "user_crew_price")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserCrewPrice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sex", nullable = false)
    private Sex sex;

    @NotNull
    @Column(name = "value", precision=10, scale=2, nullable = false)
    private BigDecimal value;

    @NotNull
    @Column(name = "cash", precision=10, scale=2, nullable = false)
    private BigDecimal cash;

    @ManyToOne(optional = false)
    @NotNull
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sex getSex() {
        return sex;
    }

    public UserCrewPrice sex(Sex sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public BigDecimal getValue() {
        return value;
    }

    public UserCrewPrice value(BigDecimal value) {
        this.value = value;
        return this;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public UserCrewPrice cash(BigDecimal cash) {
        this.cash = cash;
        return this;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public User getUser() {
        return user;
    }

    public UserCrewPrice user(User user) {
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
        UserCrewPrice userCrewPrice = (UserCrewPrice) o;
        if (userCrewPrice.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, userCrewPrice.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "UserCrewPrice{" +
            "id=" + id +
            ", sex='" + sex + "'" +
            ", value='" + value + "'" +
            ", cash='" + cash + "'" +
            '}';
    }
}
