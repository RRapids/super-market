package com.soft1841.cn.controller;

import com.soft1841.cn.entity.Admin;
import com.soft1841.cn.service.AdminService;
import com.soft1841.cn.utils.ServiceFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddAdminController {

    @FXML
    private TextField adminName, adminAvatar, adminNumber;

    @FXML
    private PasswordField adminPassword;

    private ObservableList<Admin> adminData = FXCollections.observableArrayList();

    private AdminService adminService = ServiceFactory.getAdminServiceInstance();

    public ObservableList<Admin> getAdminData() {
        return adminData;
    }

    public void setAdminData(ObservableList<Admin> adminData) {
        this.adminData = adminData;
    }

    public void addAdmin() {
        String name = adminName.getText();
        String avatar = adminAvatar.getText();
        String number = adminNumber.getText();
        String password = adminPassword.getText();

        Admin admin = new Admin();
        admin.setName(name);
        admin.setAvatar(avatar);
        admin.setNumber(number);
        admin.setPassword(password);


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示信息");

        if (password.isEmpty() || avatar.isEmpty() || name.isEmpty() || number.isEmpty()) {
            alert.setHeaderText("新增管理员失败！");
            alert.showAndWait();
        } else {
            long id = adminService.insertAdmin(admin);
            admin.setId(id);
            this.getAdminData().add(admin);
            Stage stage = (Stage) adminName.getScene().getWindow();
            stage.close();
        }
    }
}