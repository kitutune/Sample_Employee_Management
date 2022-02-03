package com.example.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.form.EmployeeForm;
import com.example.model.Employee;

@Service
public class EmployeeService {
    
    public Map<String, String> getGenderList () {
        Map<String, String> genderList = new HashMap<> ();
        genderList.put ("男", "男");
        genderList.put ("女", "女");
        genderList.put ("other", "other");
        
        return genderList;
    };
    
    public Employee formToEntity (EmployeeForm form) {
        Employee employee = new Employee ();
        
        employee.setUserId (form.getUserId ());
        // 氏名
        employee.setUserName (form.getUserName ());
        // Email
        employee.setEmail (form.getEmail ());
        // 電話番号
        employee.setPhone_number (form.getPhone_number ());
        // // 性別
        employee.setGender (form.getGender ());
        // 生年月日
        employee.setBirthday (form.getBirthday ());
        // 入社日
        employee.setJoinday (form.getJoinday ());
        // // コメント
        employee.setComment (form.getComment ());
        return employee;
    }
    
    public EmployeeForm entityToForm (Employee employee) {
        EmployeeForm form = new EmployeeForm ();
        form.setUserId (employee.getUserId ());
        form.setUserName (employee.getUserName ());
        form.setEmail (employee.getEmail ());
        form.setPhone_number (employee.getPhone_number ());
        form.setGender (employee.getGender ());
        form.setBirthday (employee.getBirthday ());
        form.setJoinday (employee.getJoinday ());
        form.setComment (employee.getComment ());
        return form;
    }
    
}
