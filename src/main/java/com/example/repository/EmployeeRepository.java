package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.Employee;

// @Repositoryを付けてもいいが、JpaRepositoryを継承しているので省略してもいい
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
    @Query (value = "select * from employee where (?1 is null or username LIKE %?1%)AND(?2 is null or gender = ?2)AND(?3 is null or joinday >= ?3 )AND(?4 is null or joinday <= ?4 )", nativeQuery = true)
    List<Employee> search (String name, String gender, String start, String end);
}
