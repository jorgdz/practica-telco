package com.telconet.practica.service.security;

import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public interface SecurityConfigService {

    /**
	 * Cargar la configuracion de la sesion
	 * Deshabilitar el csrf token
	 * @param http
	 * @return la instancia actual del servicio
	 * @throws Exception
	 */
	SecurityConfigService loadSessionManagment(HttpSecurity http) 
    throws Exception ;

    /**
     * Carga la lista de ACLs
     * @param http
     * @return la instancia actual del servicio
     * @throws Exception
     */
    SecurityConfigService loadAcls(HttpSecurity http) throws Exception;

    /**
     * * Carga filtros de seguridad
     * @param http
     * @param authManager manejador de la autenticacion
     * @param appContext contexto de la aplicacion
     * @return la instancia actual del servicio
     * @throws Exception
     */
    SecurityConfigService loadFilters(HttpSecurity http, 
        AuthenticationManager authManager, ApplicationContext appContext) 
        throws Exception;

    /**
     * Loads authentication service
     * Adds a password encoder
     * @param auth
     * @return la instancia actual del servicio
     * @throws Exception
     */
    SecurityConfigService loadAuthenticationService(
        AuthenticationManagerBuilder auth) throws Exception;
}
