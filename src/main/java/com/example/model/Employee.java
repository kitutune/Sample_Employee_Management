package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table (name = "employee")
public class Employee {
    // 社員番号
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long userId;
    // 氏名
    private String username;
    // Email
    private String email;
    // 電話番号
    private String phone_number;
    // 性別
    private String gender;
    // 生年月日
    private String birthday;
    // 入社日
    private String joinday;
    // コメント
    private String comment;
}
