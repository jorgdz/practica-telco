package com.telconet.practica.service.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.telconet.practica.entity.User;
import com.telconet.practica.repository.UserRepository;

@Service
public class UserAuthDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);

        if (user != null) {
            List<GrantedAuthority> roles = new ArrayList(user.getRoles());
            return ( new org.springframework.security.core.userdetails.User(
				user.getUsername(),
				user.getPassword(),
				true,
				true, true, true,
				roles
			));
        }

        throw new UsernameNotFoundException("El nombre de usuario no existe.");
    }
}
