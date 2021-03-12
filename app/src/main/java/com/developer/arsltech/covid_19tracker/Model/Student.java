package com.developer.arsltech.covid_19tracker.Model;

public class Student {

    private String name;
    private String age;
    private String covid;

    public Student() {

    }

    public Student(String name, String age, String covid) {
        this.name = name;
        this.age = age;
        this.covid = covid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCovid() {
        return covid;
    }

    public void setCovid(String covid) {
        this.covid = covid;
    }
}