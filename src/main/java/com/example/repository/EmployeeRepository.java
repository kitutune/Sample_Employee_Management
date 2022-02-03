package com.example.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Employee;

// @Repositoryを付けてもいいが、JpaRepositoryを継承しているので省略してもいい
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    
    List<Employee> findByUserName (String userName);
    
    List<Employee> findBygender (String gender);
    
    List<Employee> findByJoinday (LocalDate joinday);
    
}
