package com.developer.arsltech.covid_19tracker.Model;

public class Student {

    private String name;
    private String age;
    private String covid;
    private String city;
    private String family;

    public Student() {

    }

    public Student(String name, String age, String covid, String city, String family) {
        this.name = name;
        this.age = age;
        this.covid = covid;
        this.city = city;
        this.family = family;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }
}