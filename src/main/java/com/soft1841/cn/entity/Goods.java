package com.soft1841.cn.entity;

import cn.hutool.db.Entity;

public class Goods {
    private long id;
    private long typeId;
    private String barCode;
    private String name;
    private String price;
    private String avatar;
    private String quantity;
    private String description;
    private String typename;

    public Goods() {
        this.id = id;
        this.typeId = typeId;
        this.barCode = barCode;
        this.name = name;
        this.price = price;
        this.avatar = avatar;
        this.quantity = quantity;
        this.description = description;
        this.typename = typename;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", typeId=" + typeId +
                ", barCode='" + barCode + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", avatar='" + avatar + '\'' +
                ", quantity='" + quantity + '\'' +
                ", description='" + description + '\'' +
                ", typename='" + typename + '\'' +
                '}';
    }
}
