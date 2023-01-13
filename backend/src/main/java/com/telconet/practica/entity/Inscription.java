package com.telconet.practica.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModelProperty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "inscriptions")
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id",
    scope = Inscription.class)
@JsonInclude(Include.NON_EMPTY)
public class Inscription implements Serializable {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(position = 1)
    private Long id;

    @Column(name = "max_hour")
    @ApiModelProperty(position = 2)
    private Integer maxHour;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "professional_id", referencedColumnName = "id")
	@JsonIgnoreProperties({"inscription", "roles", "services"})
    @ApiModelProperty(position = 3)
    private User professional;

    @ManyToOne
	@JsonIgnoreProperties({"inscriptions"})
    @ApiModelProperty(position = 4)
    private Category category;

    @OneToMany(mappedBy = "inscription", cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"inscription"})
    @ApiModelProperty(position = 5)
    private Collection<Service> services;
}
