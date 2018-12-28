package com.soft1841.cn.controller;

import com.soft1841.cn.entity.Seller;
import com.soft1841.cn.service.SellerService;
import com.soft1841.cn.utils.ServiceFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddSellerController{

    private ObservableList<Seller> sellerData = FXCollections.observableArrayList();

    @FXML
    private TextField sellerName,sellerAvatar,sellerNumber;

    @FXML
    private PasswordField sellerPassword;

    private SellerService sellerService = ServiceFactory.getSellerServiceInstance();

    public ObservableList<Seller> getSellerData(){
        return sellerData;
    }

    public void setSellerData(ObservableList<Seller> sellerData) {
        this.sellerData = sellerData;
    }

    public void addSeller(){
        String name = sellerName.getText();
        String avatar = sellerAvatar.getText();
        String number = sellerNumber.getText();
        String password = sellerPassword.getText();
        Seller seller = new Seller();
        seller.setName(name);
        seller.setAvatar(avatar);
        seller.setNumber(number);
        seller.setPassword(password);
        long id = sellerService.insertSeller(seller);
        seller.setId(id);
        this.getSellerData().add(seller);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示信息");
        alert.setHeaderText("新增收银员成功！");
        alert.showAndWait();
        Stage stage = (Stage) sellerName.getScene().getWindow();
        stage.close();
    }

}