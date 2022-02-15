package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.form.DemployeeForm;
import com.example.model.Demployee;
import com.example.model.Employee;
import com.example.repository.DemployeeRepository;
import com.example.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DepartmentService {
    private final EmployeeRepository emp;
    private final DemployeeRepository demp;
    
    public List<Demployee> getDemployees () {
        var empList = emp.findAll ();
        List<Demployee> dempList = null;
        Demployee demp = new Demployee ();
        for (Employee emp : empList) {
            demp.setUsername (emp.getUsername ());
            demp.setUserId (emp.getUserId ());
            this.demp.save (demp);
        }
        dempList = this.demp.findAll ();
        return dempList;
    }
    
    public Demployee setDemp (Long id) {
        var emp = this.emp.getById (id);
        Demployee demp = new Demployee ();
        demp.setUsername (emp.getUsername ());
        demp.setUserId (emp.getUserId ());
        this.demp.save (demp);
        return demp;
    }
    
    public DemployeeForm convert (Demployee demp) {
        DemployeeForm dForm = new DemployeeForm ();
        dForm.setUserId (demp.getUserId ());
        dForm.setUsername (demp.getUsername ());
        dForm.setArea (demp.getArea ());
        dForm.setSales (demp.getCustomers_held ());
        dForm.setUpdate_day (demp.getUpdate_day ());
        dForm.setDepartment (demp.getDepartment ());
        return dForm;
        
    }
    
    public Demployee convert (DemployeeForm dForm) {
        Demployee demp = new Demployee ();
        demp.setUserId (dForm.getUserId ());
        demp.setUsername (dForm.getUsername ());
        demp.setArea (dForm.getArea ());
        demp.setSales (dForm.getCustomers_held ());
        demp.setUpdate_day (dForm.getUpdate_day ());
        demp.setDepartment (dForm.getDepartment ());
        return demp;
        
    }
    
}
