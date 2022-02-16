package com.example.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.model.Department;
import com.example.repository.DepartmentRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DepartmentLoader implements CommandLineRunner {
    private final DepartmentRepository repository;
    
    @Override
    public void run (String... args) throws Exception {
        if (repository.count () <= 0) {
            // TODO 自動生成されたメソッド・スタブ
            Department department = new Department ();
            department.setDepartment_name ("営業一課");
            repository.save (department);
            department = new Department ();
            department.setDepartment_name ("営業二課");
            repository.save (department);
            department = new Department ();
            department.setDepartment_name ("営業三課");
            repository.save (department);
            department = new Department ();
            department.setDepartment_name ("営業四課");
            repository.save (department);
        }
    }
}
