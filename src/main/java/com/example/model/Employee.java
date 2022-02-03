package com.example.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Employee {
    // 社員番号
    @Id
    private String userId;
    // 氏名
    private String userName;
    // Email
    private String email;
    // 電話番号
    private String phone_number;
    // 性別
    private String gender;
    // 生年月日
    private LocalDate birthday;
    // 入社日
    private LocalDate joinday;
    // コメント
    private String comment;
}
