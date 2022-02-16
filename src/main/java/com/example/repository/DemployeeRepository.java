package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.model.Demployee;

public interface DemployeeRepository extends JpaRepository<Demployee, Long> {
    
    @Query (value = "SELECT * FROM demployee WHERE user_id = ?1", nativeQuery = true)
    public Demployee findByUserid (Long id);
    
    @Query (value = "SELECT * FROM demployee WHERE department_id = ?1", nativeQuery = true)
    public List<Demployee> findByDepartmentsList (Long id);
    
}
