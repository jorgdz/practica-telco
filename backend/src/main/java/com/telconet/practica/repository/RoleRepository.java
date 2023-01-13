package com.telconet.practica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telconet.practica.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    
}
