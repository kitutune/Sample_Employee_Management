package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Demployee;

public interface DemployeeRepository extends JpaRepository<Demployee, Long> {
    
}
