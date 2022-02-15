package com.example.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
// これがないとテーブルの自動生成がされない場合がある様子
@Table (name = "demployee")
public class Demployee {
    @Id
    private Long userId;
    private String username;
    // 担当地区
    private String area;
    // 売上
    private Long sales;
    // 保有顧客数 Number of customers held
    private Long customers_held;
    // 更新日時
    private String update_day;
    
    @ManyToOne
    @JoinColumn (name = "id")
    private Department department;
}
