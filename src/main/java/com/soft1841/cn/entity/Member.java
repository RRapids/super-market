package com.soft1841.cn.entity;

public class Member {
    private long id;
    private String name;
    private String address;
    private String phone;
    private String integral;

    public Member() {
    }

    public Member(long id, String name, String address, String phone, String integral) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.integral = integral;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", integral='" + integral + '\'' +
                '}';
    }
}
