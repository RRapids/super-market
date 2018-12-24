package com.soft1841.cn.entity;

public class Seller {
    private Long id;
    private String number;
    private String name;
    private String password;
    private String headImage;

    public Seller(Long id, String number, String name, String password, String headImage) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.password = password;
        this.headImage = headImage;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }
}
