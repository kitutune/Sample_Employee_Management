// package com.example.config;
//
// import java.util.List;
//
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;
//
// import com.example.model.Demployee;
// import com.example.model.Employee;
// import com.example.repository.DemployeeRepository;
// import com.example.repository.EmployeeRepository;
//
// import lombok.RequiredArgsConstructor;
//
// @RequiredArgsConstructor
// @Component
// public class DemployeeLoader implements CommandLineRunner {
// private final DemployeeRepository repository;
// private final EmployeeRepository emp;
//
// @Override
// public void run (String... args) throws Exception {
// Demployee employee = new Demployee ();
// List<Employee> eList = emp.findAll ();
// for (Employee emp : eList) {
//
// employee.setUsername (emp.getUsername ());
// employee.setUserId (emp.getUserId ());
// }
// System.out.println ("成功");
// repository.save (employee);
// }
//
// }
