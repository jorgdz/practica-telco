package com.telconet.practica.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
@Entity
@Table(name = "users")
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id",
    scope = Service.class)
public class User implements Serializable {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(position = 1)
    private Long id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnoreProperties({"user"})
	@ApiModelProperty(position = 2)
	private Collection<Role> roles;

    @OneToOne(mappedBy = "professional")
	@JsonIgnoreProperties({"professional", "services"})
	@ApiModelProperty(position = 3)
    private Inscription inscription;
    
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"client"})
	@ApiModelProperty(position = 4)
    private Collection<Service> services;

    @Size(min = 3, max = 155, message = "El nombre debe ser mayor a 3 y menor a 155 caracteres.")
	@NotEmpty(message = "El nombre es obligatorio.")
	@ApiModelProperty(position = 5)
	private String name;
	
	@Size(min = 3, max = 155, message = "El apellido debe ser mayor a 3 y menor a 155 caracteres.")
	@NotEmpty(message = "El apellido es obligatorio.")
	@ApiModelProperty(position = 6)
	private String lastname;

    @Size(min = 3, max = 16, message = "El nombre de usuario debe ser mayor a 3 y menor a 16 caracteres.")
	@NotEmpty(message = "El nombre de usuario es obligatorio.")
	@Pattern(regexp = "^[a-z0-9]+$", message = "El nombre de usuario debe ser alfanumérico.")
	@ApiModelProperty(position = 7)
	private String username;
	
	@Email(message = "El correo no es válido.")
	@Size(min = 5, max = 255, message = "El correo debe ser mayor a 5 y menor a 255 caracteres.")
	@NotEmpty(message = "El correo es obligatorio.")
	@ApiModelProperty(position = 8)
	private String email;
	
	@NotEmpty(message = "La contraseña es obligatoria.")
	@JsonProperty(access = Access.WRITE_ONLY)
	@ApiModelProperty(position = 9)
	private String password;
}
