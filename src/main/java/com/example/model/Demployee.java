package com.example.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
// これがないとテーブルの自動生成がされない場合がある様子
@Table (name = "demployee")
@NoArgsConstructor
public class Demployee implements Serializable {
    @Id
    // @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long userId;
    
    private String username;
    // 担当地区
    private String area;
    // 売上
    private Long sales;
    // 保有顧客数 Number of customers held
    private Long customers;
    // 更新日時
    private String update_day;
    
    @ManyToOne
    // @JoinColumn (name = "id")
    private Department department;
}
