package com.telconet.practica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telconet.practica.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
