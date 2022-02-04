package com.example.form;

import java.util.Date;

import lombok.Data;

@Data
public class GetSearchWord {
    private String name;
    private String gender;
    private Date getJoinDateFrom;
    private Date getJoinDateTo;
}
