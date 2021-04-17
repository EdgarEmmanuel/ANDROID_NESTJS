package com.example.transfertandroidproject.helpers;

import java.util.ArrayList;

public class Utils {


    public static Boolean verifyStringFields(ArrayList<String> arraysOfValues) {
        int numberOfValueInTheArray = arraysOfValues.size();
        int numberOfValueTrue=0;

        /**
         * we loop through
         */
        for(String val : arraysOfValues ){
            if(!val.trim().equalsIgnoreCase("")){
                numberOfValueTrue++;
            }
        }

        if(numberOfValueInTheArray==numberOfValueTrue) return true;
        return false;
    }

    public static Boolean verifyIntFields(ArrayList<String> arraysOfValues){
        int numberOfValues = arraysOfValues.size();
        int numberOfValueTrue=0;

        /**
         * we loop through to verify if all the value can be transform in an integer
         */
        for(String val : arraysOfValues ){
            try {
                Integer.parseInt(val.trim());
                numberOfValueTrue++;
            }catch (Exception e){
                continue;
            }
        }
        if(numberOfValues==numberOfValueTrue) return true;
        return false;
    }


}
