package com.soft1841.cn.entity;

public class Member {
    private Long id;
    private String number;
    private String name;
    private String department;
    private String phone;
    private String integral;

    public Member(Long id, String number, String name, String department, String phone, String integral) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.department = department;
        this.phone = phone;
        this.integral = integral;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

}
