package com.example.fishingapp.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public  class Validator {
    public static boolean validateEmail(String email){
        if(email == null){
            return false;
        }
        //basic regex for email validation
        final String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        final Pattern pattern = Pattern.compile(emailRegex);

        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean validatePassword(String password){
        if(password == null){
            return false;
        }
        final String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{2,}$";
        //at least 2 characters, capital and small letters at least one of each and at least one number
        final Pattern pattern = Pattern.compile(passwordRegex);

        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
