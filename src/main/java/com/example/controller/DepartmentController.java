package com.example.controller;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.form.DemployeeForm;
import com.example.repository.DemployeeRepository;
import com.example.repository.DepartmentRepository;
import com.example.service.DepartmentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentRepository dRepository;
    private final DemployeeRepository deRepository;
    private final DepartmentService service;
    
    // このコントローラーで空のまま送信された値は全て空文字ではなくnullにする
    @InitBinder
    public void initBinder (WebDataBinder binder) {
        // bind empty strings as null
        binder.registerCustomEditor (String.class, new StringTrimmerEditor (true));
    }
    
    @GetMapping ("/departmentlist")
    public String getDepartmentList (Model model) {
        service.saveDemp ();
        model.addAttribute ("departments", dRepository.findAll ());
        return "departmentlist";
    }
    
    // 部門ごとの社員一覧を表示するページに遷移
    @GetMapping ("/department/{id}")
    public String getDepartmentEmployeeList (@PathVariable Long id, Model model) {
        // 部門Idに一致する社員を取得
        // model.addAttribute ("",)
        System.out.println ("送られてきたdemployeeはこれ:" + id);
        return "departmentemployeelist";
        
    }
    
    // 部門ごとの社員一覧から在籍している人たちを表示するページに遷移
    // 部門ごとの社員一覧から退職している人たちを表示するページに遷移
    
    // 部門の社員を編集するページに遷移
    @GetMapping ("/dempedit/{id}")
    public String dEmployeeEdit (@PathVariable Long id, Model model) {
        service.saveDemp ();
        // 既にその社員の部門データがあるのかを判断
        // Demployee demp = null;
        // try {
        // deRepository.getById (id);
        // System.out.println ("getUserIdは：" + (deRepository.getById (id).getUserId
        // ()));
        // System.out.println ("getByIdは：" + (deRepository.getById (id)));
        // } catch (EntityNotFoundException e) {
        // demp = service.setDemp (id);
        // var dForm = service.convert (demp);
        // model.addAttribute ("demployeeForm", dForm);
        // System.out.println ("エラー");
        // return "dempedit";
        // }
        // System.out.println ("エラーなし");
        var demp = deRepository.getById (id);
        var dForm = service.convert (demp);
        model.addAttribute ("demployeeForm", dForm);
        return "dempedit";
    }
    
    // 部門の社員を編集したデータを受け取る
    @PostMapping ("/dempedit")
    public String dEmployeeEdit (@Validated @ModelAttribute DemployeeForm form, BindingResult result, Model model) {
        if (result.hasErrors ()) {
            return "dempedit";
        }
        // formをDemployeeに変換
        var demp = service.convert (form);
        // repositoryをsave
        deRepository.save (demp);
        return "redirect:departmentlist";
    }
    
}
