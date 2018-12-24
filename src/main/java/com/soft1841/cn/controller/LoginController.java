package com.soft1841.cn.controller;

import com.soft1841.cn.service.SellerService;
import com.soft1841.cn.utils.ServiceFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private TextField accountField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ToggleGroup user;

    private SellerService sellerService = ServiceFactory.getSellerServiceInstance();

    public void login() throws Exception {
        String account = accountField.getText().trim();
        String password = passwordField.getText().trim();
        //调用service的登录 功能
        boolean flag = sellerService.login(account,password);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示");
        if(flag){
            alert.setContentText("登录成功!");
            alert.showAndWait();
        }else {
            alert.setContentText("账号或密码错误，登录失败!");
            alert.showAndWait();
        }
    }
}