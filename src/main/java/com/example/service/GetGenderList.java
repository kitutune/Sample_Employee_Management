package com.example.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class GetGenderList {
    public Map<Integer, String> getGender () {
        Map<Integer, String> gender = new HashMap<> ();
        gender.put (1, "男");
        gender.put (2, "女");
        gender.put (3, "Other");
        return gender;
    }
}
