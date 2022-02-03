package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.form.EmployeeForm;
import com.example.model.Employee;
import com.example.repository.EmployeeRepository;
import com.example.service.EmployeeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeRepository repository;
    private final EmployeeService eService;
    
    @GetMapping ("/")
    public String getList (Model model) {
        // JpaRepositoryのメソッドであるfindAll()を使ってDBに保存されているemployeeのデータを持ってくる
        model.addAttribute ("employees", repository.findAll ());
        return "list";
    }
    
    @GetMapping ("/regist")
    public String getRegistEmployee (@ModelAttribute EmployeeForm form, Model model) {
        // ラジオボタンの初期位置を男に設定する
        if (form.getGender () == null) {
            form.setGender ("男");
        }
        
        model.addAttribute ("genderList", eService.getGenderList ());
        
        return "regist";
    }
    
    @PostMapping ("/regist")
    public String registEmployee (@Validated @ModelAttribute EmployeeForm form, BindingResult result, Model model) {
        log.info (form.toString ());
        
        if (result.hasErrors ()) {
            model.addAttribute ("genderList", eService.getGenderList ());
            System.out.print ("エラー出てる");
            return "regist";
        }
        Employee employee = eService.formToEntity (form);
        repository.save (employee);
        return "redirect:/";
    }
    
    @GetMapping ("/edit/{user_id}")
    public String getEditEmployee (@PathVariable String user_id, Model model) {
        EmployeeForm form = eService.entityToForm (repository.getById (user_id));
        model.addAttribute ("employeeForm", form);
        // return "edit";
        return "regist";
    }
    
    // @PostMapping ("/edit/edit")
    // public String editEmployee (@Validated @ModelAttribute EmployeeForm form,
    // BindingResult result, Model model) {
    // if (result.hasErrors ()) {
    // // 引き継げない
    // // model.addAttribute ("genderList", gGenderList.getGender ());
    // // return "redirect:/edit/3";
    // return "edit";
    // }
    // Employee employee = eService.formToEntity (form);
    // repository.save (employee);
    // return "redirect:/";
    // }
    
    @GetMapping ("/delete/{id}")
    public String deleteEmployee (@PathVariable String id) {
        repository.deleteById (id);
        return "redirect:/";
    }
    
}
