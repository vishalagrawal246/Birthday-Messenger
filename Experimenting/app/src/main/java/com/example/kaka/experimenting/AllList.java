package com.example.kaka.experimenting;

import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by kaka on 2/6/2018.
 */

public class AllList implements Comparable<AllList> {
    String DatenTime;
    String Numberrr;

    AllList(String DatenTime, String Numberrr) {
        this.DatenTime = DatenTime;
        this.Numberrr = Numberrr;
    }

    public String getDatenTime() {
        return DatenTime;
    }



    public String getNumberrr() {
        return Numberrr;
    }



    @Override
    public String toString() {
        return "AllList{" +
                "DatenTime='" + DatenTime + '\'' +
                ", Numberrr='" + Numberrr + '\'' +
                '}';
    }




        @Override
        public int compareTo (@NonNull AllList allList){
        return allList.DatenTime.compareTo(this.DatenTime);
    }
    }
