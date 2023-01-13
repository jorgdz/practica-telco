package com.telconet.practica.util;

import io.swagger.annotations.ApiModel;

/**
 * 
 * Enumeración que sirve para los días de la semana
 */
@ApiModel
public enum DayEnum {

    /**
	 * {@enum lunes}.
	 * 
	 */
	LUNES ("lunes"), 
	
	
	/**
	 * {@enum martes}.
	 * 
	 */
	MARTES ("martes"),
	
	/**
	 * {@enum miercoles}.
	 * 
	 */
	MIERCOLES("miercoles"),

    /**
	 * {@enum jueves}.
	 * 
	 */
	JUEVES("jueves"),

    /**
	 * {@enum miercoles}.
	 * 
	 */
	VIERNES("viernes"),

    /**
	 * {@enum sabado}.
	 * 
	 */
	SABADO("sabado"),

    /**
	 * {@enum domingo}.
	 * 
	 */
	DOMINGO("domingo");
	
	private final String value;
	
	private DayEnum (String value) {
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}
}
