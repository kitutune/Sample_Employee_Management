package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;
import com.example.service.GetBirthList;
import com.example.service.GetGenderList;
import com.example.service.GetJoiningDateList;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeRepository repository;
    private final GetBirthList gBirthList;
    private final GetJoiningDateList gJoiningDateList;
    private final GetGenderList gGenderList;
    
    @GetMapping ("/")
    public String getList (Model model) {
        // JpaRepositoryのメソッドであるfindAll()を使ってDBに保存されているemployeeのデータを持ってくる
        model.addAttribute ("employees", repository.findAll ());
        
        return "list";
    }
    
    @GetMapping ("regist")
    public String getRegistEmployee (@ModelAttribute Employee employee, Model model) {
        model.addAttribute ("genderList", gGenderList.getGender ());
        model.addAttribute ("birthYear", gBirthList.getBirthYear ());
        model.addAttribute ("birthMonth", gBirthList.getBirthMonth ());
        model.addAttribute ("birthDay", gBirthList.getBirthDay ());
        model.addAttribute ("joiningYear", gJoiningDateList.getJoinYear ());
        model.addAttribute ("joiningMonth", gJoiningDateList.getJoinMonth ());
        model.addAttribute ("joiningDay", gJoiningDateList.getJoinDay ());
        return "regist";
    }
    
    // actionで受けとる
    @PostMapping ("/registed")
    public String registEmployee (@Validated @ModelAttribute Employee employee, BindingResult result, Model model) {
        if (result.hasErrors ()) {
            model.addAttribute ("genderList", gGenderList.getGender ());
            model.addAttribute ("birthYear", gBirthList.getBirthYear ());
            model.addAttribute ("birthMonth", gBirthList.getBirthMonth ());
            model.addAttribute ("birthDay", gBirthList.getBirthDay ());
            model.addAttribute ("joiningYear", gJoiningDateList.getJoinYear ());
            model.addAttribute ("joiningMonth", gJoiningDateList.getJoinMonth ());
            model.addAttribute ("joiningDay", gJoiningDateList.getJoinDay ());
            return "regist";
        }
        
        repository.save (employee);
        return "list";
    }
    
    @GetMapping ("/edit{id}")
    public String getEditEmployee (@PathVariable Long id, Model model) {
        model.addAttribute ("employee", repository.findById (id));
        
        model.addAttribute ("genderList", gGenderList.getGender ());
        model.addAttribute ("birthYear", gBirthList.getBirthYear ());
        model.addAttribute ("birthMonth", gBirthList.getBirthMonth ());
        model.addAttribute ("birthDay", gBirthList.getBirthDay ());
        model.addAttribute ("joiningYear", gJoiningDateList.getJoinYear ());
        model.addAttribute ("joiningMonth", gJoiningDateList.getJoinMonth ());
        model.addAttribute ("joiningDay", gJoiningDateList.getJoinDay ());
        
        return "edit";
    }
    
    // actionで受けとる
    @PostMapping ("/edited")
    public String editEmployee (@Validated @ModelAttribute Employee employee, BindingResult result, Model model) {
        if (result.hasErrors ()) {
            
            return "edit";
        }
        
        repository.save (employee);
        return "list";
    }
    
}
