package com.example.controller;

import java.util.List;

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
import com.example.model.Demployee;
import com.example.model.Department;
import com.example.repository.DemployeeRepository;
import com.example.repository.DepartmentRepository;
import com.example.service.DepartmentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
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
        // ここで合計売上・合計人数・１人あたりの売上平均値を計算するメソッドをサービスで
        // deRepository.findByDepartmentsList (1l).forEach (x -> System.out.println
        // (x.getUsername ()));
        // Function<Department,List<Demployee>>(e)=x->{
        // return a =new Department();
        // };
        
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
        
        List<Department> list = dRepository.findAll ();
        model.addAttribute ("departments", list);
        return "departmentlist";
    }
    
    // 部門ごとの社員一覧を表示するページに遷移
    @GetMapping ("/department/{id}")
    public String getDepartmentEmployeeList (@PathVariable Long id, Model model) {
        // 部門Idに一致する社員を取得
        return "departmentemployeelist";
        
    }
    
    // 部門ごとの社員一覧から在籍している人たちを表示するページに遷移
    // 部門ごとの社員一覧から退職している人たちを表示するページに遷移
    
    // 部門の社員を編集するページに遷移
    @GetMapping ("/dempedit/{id}")
    public String dEmployeeEdit (@PathVariable Long id, Model model) {
        // demployeeテーブルにemployeeのテーブルから名前と社員番号を抽出し登録
        service.saveDemp ();
        // // 受け取ったIDでdemployeeRepositoryからターゲットを抽出し、formにユーザー名と社員番号を渡す
        Demployee demp = deRepository.getById (id);
        DemployeeForm dForm = service.convert (demp);
        model.addAttribute ("demployeeForm", dForm);
        return "dempedit";
    }
    
    // 部門の社員を編集したデータを受け取る
    @PostMapping ("/dempedit")
    public String dEmployeeEdit (@Validated @ModelAttribute DemployeeForm form, BindingResult result, Model model) {
        if (result.hasErrors ()) {
            return "dempedit";
        }
        log.info (form.toString ());
        // formをDemployeeに変換
        Demployee demp = service.convert (form);
        deRepository.save (demp);
        return "redirect:departmentlist";
    }
    
}
