package com.soft1841.cn.controller;

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

    public void login() throws Exception {
        String account = accountField.getText().trim();
        String password = passwordField.getText().trim();
        if (user.getSelectedToggle().getUserData() == null) {
            if ("2233".equals(account) && "123".equals(password)) {
                //读入主布局文件
                Stage mainStage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/cashier.fxml"));
                BorderPane root = fxmlLoader.load();
                Scene scene = new Scene(root);
                scene.getStylesheets().add("/css/style.css");
                mainStage.setTitle("收银系统");
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
        } else {
            if ("7987".equals(account) && "123".equals(password)) {
                Stage mainStage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
                BorderPane root = fxmlLoader.load();
                Scene scene = new Scene(root);
                scene.getStylesheets().add("/css/style.css");
                mainStage.setTitle("收银管理系统");
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
}
