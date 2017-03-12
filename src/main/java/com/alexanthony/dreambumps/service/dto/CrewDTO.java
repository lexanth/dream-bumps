package com.alexanthony.dreambumps.service.dto;


import javax.persistence.Column;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import com.alexanthony.dreambumps.domain.enumeration.Sex;

/**
 * A DTO for the Crew entity.
 */
public class CrewDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Sex sex;

    private String image;
    
    @NotNull
    private BigDecimal price;
    
    private BigDecimal startPrice;
    
    private Integer position;
    
    private Integer startPosition;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getPrice() {
      return price;
    }

    public void setPrice(BigDecimal price) {
      this.price = price;
    }

    public BigDecimal getStartPrice() {
      return startPrice;
    }

    public void setStartPrice(BigDecimal startPrice) {
      this.startPrice = startPrice;
    }

    public Integer getPosition() {
      return position;
    }

    public void setPosition(Integer position) {
      this.position = position;
    }

    public Integer getStartPosition() {
      return startPosition;
    }

    public void setStartPosition(Integer startPosition) {
      this.startPosition = startPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CrewDTO crewDTO = (CrewDTO) o;

        if ( ! Objects.equals(id, crewDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CrewDTO{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", sex='" + sex + "'" +
            ", image='" + image + "'" +
            '}';
    }
}
