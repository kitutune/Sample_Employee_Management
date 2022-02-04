package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.Employee;

// @Repositoryを付けてもいいが、JpaRepositoryを継承しているので省略してもいい
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>, JpaSpecificationExecutor<Employee> {
    
    @Query (value = "select * from employee where user_name Like =?1 AND  gender = ?2 AND =?3 < joinday AND joinday < =?4 ", nativeQuery = true)
    List<Employee> search (String userName, String gender, String joinDateFrom, String joinDateTo);
    
    @Query (value = "select * from employee where user_name Like  ?1", nativeQuery = true)
    List<Employee> namesearch (String name);
    
    @Query (value = "select * from employee where gender = ?1 ", nativeQuery = true)
    List<Employee> gendersearch (String gender);
    
    @Query (value = "select * from employee where  =?1 < joinday AND joinday < =?2 ", nativeQuery = true)
    List<Employee> dayearch (String joinDateFrom, String joinDateTo);
    
    // List<Employee> findByUserNameLike (String userName);
    //
    // List<Employee> findBygenderLike (String gender);
    //
    // List<Employee> findByJoinday (LocalDate joinday);
    
}
