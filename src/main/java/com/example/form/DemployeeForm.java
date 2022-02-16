package com.example.form;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.example.model.Department;

import lombok.Data;

@Data
public class DemployeeForm {
    
    @Id
    private Long userId;
    private String username;
    // 担当地区
    @NotNull (message = "必須入力です")
    private String area;
    // 売上
    @NotNull (message = "必須入力です")
    private Long sales;
    // 保有顧客数 Number of customers held
    @NotNull (message = "必須入力です")
    private Long customers;
    // 更新日時
    private String update_day;
    
    private Department department;
    
}
