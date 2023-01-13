package com.telconet.practica.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModelProperty;

@Data
@NoArgsConstructor
@Entity
@Table(name = "categories")
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id",
    scope = Category.class)
@JsonInclude(Include.NON_EMPTY)
public class Category implements Serializable {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(position = 1)
    private Long id;

    @NotEmpty(message = "La categoria es obligatoria.")
    @ApiModelProperty(position = 2)
    private String name;

    @Column(name = "min_date")
    @ApiModelProperty(position = 3)
    private String minDate;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"category", "services"})
    @ApiModelProperty(position = 4)
    private Collection<Inscription> inscriptions;
}
