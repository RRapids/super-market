package com.soft1841.cn.entity;

public class Goods {
    private Long id;
    private Long typeID;
    private String barCode;
    private String name;
    private String price;
    private String avatar;
    private String quantity;
    private String description;


    public Goods(Long id, Long typeID, String barCode, String name, String price, String avatar, String quantity, String description) {
        this.id = id;
        this.typeID = typeID;
        this.barCode = barCode;
        this.name = name;
        this.price = price;
        this.avatar = avatar;
        this.quantity = quantity;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTypeID() {
        return typeID;
    }

    public void setTypeID(Long typeID) {
        this.typeID = typeID;
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

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", typeID=" + typeID +
                ", barCode='" + barCode + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", avatar='" + avatar + '\'' +
                ", quantity='" + quantity + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
