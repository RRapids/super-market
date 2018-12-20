package com.soft1841.cn.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private TextField accountField;
    @FXML
    private PasswordField passwordField;

    public void enter()throws Exception{
        String account = accountField.getText().trim();
        String password = passwordField.getText().trim();
        if ("7987".equals(account) && "123".equals(password)) {
            //创建登录信息提示表
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("信息");
            alert.setContentText("成功登录");
            alert.showAndWait();
            //登录成功后跳转到图书管理界面
            Stage mainStage = new Stage();
            //读入主布局文件
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
            BorderPane root = fxmlLoader.load();
            Scene scene = new Scene(root);
            //读入样式文件
            scene.getStylesheets().add("/css/style.css");
            mainStage.setTitle("收银管理系统登录");
            mainStage.setMaximized(true);
            mainStage.setScene(scene);
            mainStage.show();
            Stage loginStage = (Stage) accountField.getScene().getWindow();
            loginStage.close();

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示");
            alert.setContentText("账号或密码错误，登录失败!");
            alert.showAndWait();
        }
    }
}
