package com.example.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
// これがないとテーブルの自動生成がされない場合がある様子
@Table (name = "department")
public class Department implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String department_name;
    
    private Long total_sales;
    
    // Total number of people
    private Long total_people;
    // Individual sales average
    private Long individual_sales_average;
    
    @OneToMany (mappedBy = "department")
    private List<Demployee> demployee;
}
