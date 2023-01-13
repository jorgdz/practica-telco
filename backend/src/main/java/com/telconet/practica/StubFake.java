package com.telconet.practica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.telconet.practica.entity.Role;
import com.telconet.practica.entity.User;
import com.telconet.practica.repository.RoleRepository;
import com.telconet.practica.repository.UserRepository;
import com.telconet.practica.service.security.SecurityConfigServiceManager;

@Component
public class StubFake implements ApplicationListener<ContextRefreshedEvent>{

    private static final String ADMIN = "ADMIN";
    private static final String CLIENTE = "CLIENTE";
    private static final String PROFESIONAL = "PROFESIONAL";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
	private SecurityConfigServiceManager securityConfigService;
    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // ADMINISTRADOR
        User admin = new User();
        admin.setName("Jorge");
        admin.setLastname("Diaz");
        admin.setEmail("jorge.diaz@gizlocorp.com");
        admin.setUsername("admin");
        admin.setPassword(securityConfigService.passwordEncoder().encode("admin"));

        this.userRepository.save(admin);

        Role role = new Role();
        role.setName(ADMIN);
        role.setUser(admin);
        this.roleRepository.save(role);
        
        // CLIENTE
        User cliente = new User();
        cliente.setName("Pedro");
        cliente.setLastname("Infante");
        cliente.setEmail("pedro.infante@gizlocorp.com");
        cliente.setUsername("pedro");
        cliente.setPassword(securityConfigService.passwordEncoder().encode("1234"));

        this.userRepository.save(cliente);

        Role role2 = new Role();
        role2.setName(CLIENTE);
        role2.setUser(cliente);
        this.roleRepository.save(role2);
       
        // PROFESIONALES
        User profesional = new User();
        profesional.setName("Carlos");
        profesional.setLastname("Perez");
        profesional.setEmail("carlos.perez@gizlocorp.com");
        profesional.setUsername("carlos");
        profesional.setPassword(securityConfigService.passwordEncoder().encode("1234"));

        this.userRepository.save(profesional);

        Role role3 = new Role();
        role3.setName(PROFESIONAL);
        role3.setUser(profesional);
        this.roleRepository.save(role3);
    }    
}
