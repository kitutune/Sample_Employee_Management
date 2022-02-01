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
        System.out.println ("社員数は" + repository.findAll ().size ());
        return "list";
    }
    
    @GetMapping ("/regist")
    public String getRegistEmployee (@ModelAttribute Employee employee, Model model) {
        // System.out.println ("year" + employee.getBirth_year ());
        // System.out.println ("month" + employee.getBirth_month ());
        // System.out.println ("day" + employee.getBirth_day ());
        if (employee.getGender () == null) {
            // System.out.print (employee.getGender ());
            employee.setGender ("男");
            // System.out.print (employee.getGender ());
        }
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
    @PostMapping ("/regist")
    public String registEmployee (@Validated @ModelAttribute Employee employee, BindingResult result, Model model) {
        System.out.println ("year" + employee.getBirth_year ());
        System.out.println ("month" + employee.getBirth_month ());
        System.out.println ("day" + employee.getBirth_day ());
        // System.out.println (employee.getGender ());
        
        if (result.hasErrors ()) {
            model.addAttribute ("genderList", gGenderList.getGender ());
            model.addAttribute ("birthYear", gBirthList.getBirthYear ());
            model.addAttribute ("birthMonth", gBirthList.getBirthMonth ());
            model.addAttribute ("birthDay", gBirthList.getBirthDay ());
            model.addAttribute ("joiningYear", gJoiningDateList.getJoinYear ());
            model.addAttribute ("joiningMonth", gJoiningDateList.getJoinMonth ());
            model.addAttribute ("joiningDay", gJoiningDateList.getJoinDay ());
            System.out.print ("エラー出てる");
            return "regist";
        }
        
        repository.save (employee);
        return "redirect:/";
    }
    
    @GetMapping ("/edit/{user_id}")
    public String getEditEmployee (@PathVariable Long user_id, Model model) {
        model.addAttribute ("employee", repository.findById (user_id));
        
        System.out.println ("year" + repository.findById (user_id).get ().getBirth_year ());
        System.out.println ("month" + repository.findById (user_id).get ().getBirth_month ());
        System.out.println ("month" + repository.findById (user_id).get ().getBirth_day ());
        model.addAttribute ("birth_year", repository.findById (user_id).get ().getBirth_year ());
        model.addAttribute ("birth_month", repository.findById (user_id).get ().getBirth_month ());
        model.addAttribute ("birth_day", repository.findById (user_id).get ().getBirth_day ());
        model.addAttribute ("jdate_year", repository.findById (user_id).get ().getJdate_year ());
        model.addAttribute ("jdate_month", repository.findById (user_id).get ().getJdate_month ());
        model.addAttribute ("jdate_day", repository.findById (user_id).get ().getJdate_day ());
        
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
            model.addAttribute ("genderList", gGenderList.getGender ());
            model.addAttribute ("birthYear", gBirthList.getBirthYear ());
            model.addAttribute ("birthMonth", gBirthList.getBirthMonth ());
            model.addAttribute ("birthDay", gBirthList.getBirthDay ());
            model.addAttribute ("joiningYear", gJoiningDateList.getJoinYear ());
            model.addAttribute ("joiningMonth", gJoiningDateList.getJoinMonth ());
            model.addAttribute ("joiningDay", gJoiningDateList.getJoinDay ());
            
            return "edit";
        }
        
        repository.save (employee);
        return "list";
    }
    
    @GetMapping ("/delete/{id}")
    public String deleteEmployee (@PathVariable Long id) {
        repository.deleteById (id);
        return "redirect:/";
    }
    
}
