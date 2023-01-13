package com.telconet.practica.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.telconet.practica.util.DayEnum;

import io.swagger.annotations.ApiModelProperty;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
@Entity
@Table(name = "services")
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id",
    scope = Service.class)
public class Service {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(position = 1)
    private Long id;
    
    @ManyToOne
	@JsonIgnoreProperties({"services", "inscription", "roles"})
    @ApiModelProperty(position = 2)
    private User client;
    
    @ManyToOne
	@JsonIgnoreProperties({"services"})
    @ApiModelProperty(position = 3)
    private Inscription inscription;

    @ApiModelProperty(position = 4)
    private DayEnum day;
}
