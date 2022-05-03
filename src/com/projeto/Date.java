package com.projeto;


public class Date {

    public Date(Integer day, Integer month, Integer year){
        this.day = day;
        this.month = month;
        this.year = year;
    }        //OLA DANIEL


    public Integer day;

    public Integer month;

    public Integer year;


    public boolean beforeDate(Date d) {
        if (this.year < d.year) return true;
        else if (this.year.equals(d.year)) {
            if (this.month < d.month)
                return true;
            if (this.month.equals(d.month)) {
                return this.day < d.day;
            }
        }
        return false;
    }

    public boolean afterDate(Date d) {
        if (this.day.equals(d.day) && this.month.equals(d.month) && this.year.equals(d.year))
            return false;
        return !beforeDate(d);
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}