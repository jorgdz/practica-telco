package com.telconet.practica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telconet.practica.entity.Inscription;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Long> {
    
}
