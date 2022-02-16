package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.form.DemployeeForm;
import com.example.model.Demployee;
import com.example.model.Employee;
import com.example.repository.DemployeeRepository;
import com.example.repository.DepartmentRepository;
import com.example.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DepartmentService {
    private final EmployeeRepository eRepository;
    private final DemployeeRepository deRepository;
    private final DepartmentRepository dRepository;
    
    public List<Demployee> getDemployees () {
        var empList = eRepository.findAll ();
        List<Demployee> dempList = null;
        Demployee demp = new Demployee ();
        for (Employee emp : empList) {
            demp.setUsername (emp.getUsername ());
            demp.setUserId (emp.getUserId ());
            deRepository.save (demp);
        }
        dempList = deRepository.findAll ();
        return dempList;
    }
    
    public Demployee setDemp (Long id) {
        var emp = eRepository.getById (id);
        Demployee demp = new Demployee ();
        demp.setUsername (emp.getUsername ());
        demp.setUserId (emp.getUserId ());
        deRepository.save (demp);
        return demp;
    }
    
    public DemployeeForm convert (Demployee demp) {
        DemployeeForm dForm = new DemployeeForm ();
        
        dForm.setUserId (demp.getUserId ());
        dForm.setUsername (demp.getUsername ());
        dForm.setArea (demp.getArea ());
        dForm.setSales (demp.getSales ());
        dForm.setCustomers_held (demp.getCustomers_held ());
        dForm.setUpdate_day (demp.getUpdate_day ());
        dForm.setDepartment (demp.getDepartment ());
        return dForm;
        
    }
    
    public Demployee convert (DemployeeForm dForm) {
        Demployee demp = new Demployee ();
        demp.setUserId (dForm.getUserId ());
        demp.setUsername (dForm.getUsername ());
        demp.setArea (dForm.getArea ());
        demp.setSales (dForm.getSales ());
        demp.setCustomers_held (dForm.getCustomers_held ());
        demp.setUpdate_day (dForm.getUpdate_day ());
        demp.setDepartment (dForm.getDepartment ());
        return demp;
    }
    
    // employeeテーブルのデータをdemployeeテーブルに保存
    public void makeDempList () {
        // if (deRepository.count () <= 0) {
        Demployee demp = new Demployee ();
        List<Employee> eList = eRepository.findAll ();
        for (Employee emp : eList) {
            Long userId = emp.getUserId ();
            if (deRepository.existsById (userId)) {
                demp = deRepository.getById (userId);
            } else {
                demp.setUserId (eRepository.getById (userId).getUserId ());
                demp.setUsername (eRepository.getById (userId).getUsername ());
            }
            deRepository.save (demp);
        }
    }
    
    // employeeテーブルのデータをdemployeeテーブルに保存
    public Demployee editDemp (Long id) {
        Demployee demp = new Demployee ();
        if (deRepository.existsById (id)) {
            demp = deRepository.getById (id);
        } else {
            demp.setUserId (eRepository.getById (id).getUserId ());
            demp.setUsername (eRepository.getById (id).getUsername ());
        }
        return demp;
        
        // Demployee demp = null;
        // List<Employee> eList = eRepository.findAll ();
        // int i = 0;
        // for (Employee emp : eList) {
        // System.out.println (i++);
        // System.out.println (emp.getUsername ());
        //
        // demp = new Demployee ();
        // demp.setUsername (emp.getUsername ());
        // demp.setUserId (emp.getUserId ());
        // deRepository.save (demp);
        // }
    }
    
    // 売り上げの合計や平均値の計算
    public void Departmentcalc () {
        // デパートメントのレコード数
        for (int i = 1; i <= 4; i++) {
            int total_sales = 0;
            int total_people = 0;
            int individual_sales_average = 0;
            int count = 0;
            var demps = deRepository.findByDepartmentsList ((long) i);
            var department = dRepository.getById ((long) i);
            for (var demp : demps) {
                total_sales += demp.getSales ();
                total_people += demp.getCustomers_held ();
                count += 1;
            }
            System.out.println ("カウントと合計");
            System.out.println (count);
            System.out.println (total_sales);
            System.out.println (count > 0 && total_sales > 0);
            
            if (count > 0 && total_sales > 0) {
                individual_sales_average += (total_sales + count - 1) / count;
            }
            department.setTotal_people ((long) total_people);
            department.setTotal_sales ((long) total_sales);
            department.setIndividual_sales_average ((long) individual_sales_average);
            dRepository.save (department);
        }
    }
}
