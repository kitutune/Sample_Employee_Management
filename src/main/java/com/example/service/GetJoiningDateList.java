package com.example.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetJoiningDateList {
    private final GetBirthList gBirth;
    
    public List<Integer> getJoinYear () {
        Date date = new Date ();
        Calendar calendar = Calendar.getInstance ();
        
        calendar.setTime (date);
        int yEnd = calendar.get (Calendar.YEAR);
        int yStart = calendar.get (Calendar.YEAR) - 65;
        
        List<Integer> year = new ArrayList<> ();
        for (int i = yStart; i < yEnd; i++) {
            year.add (i);
        }
        
        return year;
        
    }
    
    public List<Integer> getJoinMonth () {
        var month = gBirth.getBirthMonth ();
        
        return month;
    }
    
    public List<Integer> getJoinDay () {
        var day = gBirth.getBirthDay ();
        return day;
    }
    
}
