package com.soft1841.cn.controller;

import com.soft1841.cn.entity.Admin;
import com.soft1841.cn.service.AdminService;
import com.soft1841.cn.service.SellerService;
import com.soft1841.cn.utils.ServiceFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField accountField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ToggleGroup user;


    private SellerService sellerService = ServiceFactory.getSellerServiceInstance();
    private AdminService adminService = ServiceFactory.getAdminServiceInstance();

    public void login() throws Exception {
        String account = accountField.getText().trim();
        String password = passwordField.getText().trim();
        if (user.getSelectedToggle().getUserData() == null) {
            //调用service的登录功能
            boolean flag = sellerService.login(account, password);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示");
            if (flag) {
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
                alert.setContentText("账号或密码错误，登录失败!");
                alert.showAndWait();
            }
        } else {
            //调用service的登录功能
            boolean flag = adminService.login(account, password);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示");
            if (flag) {
                Stage mainStage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
                BorderPane root = fxmlLoader.load();
                Scene scene = new Scene(root);
                scene.getStylesheets().add("/css/style.css");
                mainStage.setTitle("收银管理系统");
                mainStage.setMaximized(true);
                mainStage.setScene(scene);
                mainStage.show();
               //将这个管理员信息传给主控制器
                Admin admin = adminService.getAdminByNumber(account);
                MainController mainController = fxmlLoader.getController();
                mainController.setAdmin(admin);
                Stage loginStage = (Stage) accountField.getScene().getWindow();
                loginStage.close();
            } else {
                alert.setContentText("账号或密码错误，登录失败!");
                alert.showAndWait();
            }
        }
    }


}

