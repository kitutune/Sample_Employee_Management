package com.example.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class GetGenderList {
    public Map<String, String> getGender () {
        Map<String, String> gender = new HashMap<> ();
        gender.put ("男", "男");
        gender.put ("女", "女");
        gender.put ("Other", "Other");
        return gender;
    }
}
