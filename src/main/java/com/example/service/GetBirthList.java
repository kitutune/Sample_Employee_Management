package com.example.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class GetBirthList {
    public List<Integer> getBirthYear () {
        // 生年月日
        // 年
        Date date = new Date ();
        Calendar calendar = Calendar.getInstance ();
        
        calendar.setTime (date);
        int yCount = calendar.get (Calendar.YEAR) - 14;
        int yStart = calendar.get (Calendar.YEAR) - 100;
        
        List<Integer> year = new ArrayList<> ();
        for (int i = yStart; i < yCount; i++) {
            year.add (i);
        }
        
        // for (Integer i : year) {
        // System.out.println (i);
        // }
        return year;
    }
    
    public List<Integer> getBirthMonth () {
        // 月
        
        int mCount = 12;
        List<Integer> month = new ArrayList<> ();
        for (int i = 0; i < mCount; i++) {
            month.add (i + 1);
        }
        
        // for (Integer i : month) {
        // System.out.println (i);
        // }
        return month;
        
    }
    
    public List<Integer> getBirthDay () {
        // 日
        List<Integer> day = new ArrayList<> ();
        for (int i = 0; i < 31; i++) {
            day.add (i + 1);
        }
        
        // for (Integer i : day) {
        // System.out.println (i);
        // }
        return day;
    }
}
