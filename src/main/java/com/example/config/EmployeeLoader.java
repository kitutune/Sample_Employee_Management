package com.example.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class EmployeeLoader implements CommandLineRunner {
    private final EmployeeRepository emp;
    // private final DemployeeRepository demp;
    
    @Override
    public void run (String... args) throws Exception {
        
        Employee employee = new Employee ();
        employee.setUsername ("たけし");
        employee.setPhone_number ("09012345678");
        employee.setEmail ("takeshi@email.com");
        employee.setBirthday ("1990-10-11");
        employee.setJoinday ("2000-10-11");
        employee.setGender ("男");
        employee.setComment ("世紀末リーダー");
        emp.save (employee);
        
        employee = new Employee ();
        employee.setUsername ("ハルハラ　ハルコ");
        employee.setPhone_number ("07056782345");
        employee.setEmail ("flcl@email.com");
        employee.setBirthday ("1995-11-11");
        employee.setJoinday ("2015-10-11");
        employee.setGender ("女");
        employee.setComment ("GAINAX");
        emp.save (employee);
        employee = new Employee ();
        employee.setUsername ("ジョマ");
        employee.setPhone_number ("08008089987");
        employee.setEmail ("ocama@email.com");
        employee.setBirthday ("1960-11-11");
        employee.setJoinday ("1990-10-11");
        employee.setGender ("other");
        employee.setComment ("クレヨンしんちゃん");
        emp.save (employee);
        
    }
    
}
