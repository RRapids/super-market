package com.soft1841.cn.entity;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class Type {
    //以JavaFX属性绑定的形式，定义和数据表字段id和type_name对应的属性，注意命名规范
    private final SimpleLongProperty id = new SimpleLongProperty();
    private final SimpleStringProperty typeName = new SimpleStringProperty("");
    private final SimpleStringProperty picture = new SimpleStringProperty("");
    public Type(){
    }
    public Type(long id,String typeName,String picture){
        setId(id);
        setTypeName(typeName);
        setPicture(picture);
    }

    public long getId() {
        return id.get();
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }


    public String getTypeName() {
        return typeName.get();
    }

    public SimpleStringProperty typeNameProperty() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName.set(typeName);
    }


    public String getPicture(){return picture.get();}

    public SimpleStringProperty pictureProperty(){return picture;}

    public void setPicture(String picture){this.picture.set(picture);}


    @Override
    public String toString() {
        return typeName.get();
    }
}
