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
    
    @GetMapping ("/regist")
    public String getRegistEmployee (@ModelAttribute Employee employee, Model model) {
        // ラジオボタンの初期位置を男に設定する
        if (employee.getGender () == null) {
            employee.setGender ("男");
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
    
    @PostMapping ("/regist")
    public String registEmployee (@Validated @ModelAttribute Employee employee, BindingResult result, Model model) {
        
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
        
        // editの画面に行った際に何故かEmployeeの年月日関係のデータを送れないのでここで用意する
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
    
    @PostMapping ("/edit/edit")
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
        return "redirect:/";
    }
    
    @GetMapping ("/delete/{id}")
    public String deleteEmployee (@PathVariable Long id) {
        repository.deleteById (id);
        return "redirect:/";
    }
    
}
