package com.soft1841.cn.controller;

import com.soft1841.cn.entity.Admin;
import com.soft1841.cn.service.AdminService;
import com.soft1841.cn.utils.ServiceFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    private FlowPane adminPane;

    @FXML
    private TextField keywordsField;

    private AdminService adminService = ServiceFactory.getAdminServiceInstance();

    private List<Admin> adminList = new ArrayList<>();

    private ObservableList<Admin> adminData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adminList = adminService.selectAllAdmins();
        listAdmin(adminList);
    }

    private void listAdmin(List<Admin> adminList) {
        //移除之前的记录
        adminPane.getChildren().clear();
        for (Admin admin : adminList
        ) {
            VBox vBox = new VBox();
            vBox.setPrefSize(150, 120);
            vBox.getStyleClass().add("box");
            vBox.setSpacing(30);
            vBox.setAlignment(Pos.TOP_CENTER);
            ImageView imageView = new ImageView();
            Image image = new Image(admin.getAvatar());
            imageView.setImage(image);
            imageView.setFitWidth(80);
            imageView.setFitHeight(80);
            Circle circle = new Circle();
            circle.setCenterX(40.0);
            circle.setCenterY(40.0);
            circle.setRadius(40.0);
            imageView.setClip(circle);
            Label nameLabel = new Label(admin.getName());
            Label idLabel = new Label("账号：" + admin.getNumber());
            vBox.getChildren().addAll(imageView, nameLabel, idLabel);
            adminPane.getChildren().add(vBox);
            vBox.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    vBox.getChildren().clear();
                    //删除按钮
                    Button delBtn = new Button("删除");
                    delBtn.getStyleClass().add("warning-theme");
                    delBtn.setOnAction(delevent -> {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("确认对话框");
                        alert.setContentText("确定要删除这行记录吗?");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            long id = admin.getId();
                            //删除掉这行记录
                            adminService.deleteAdminById(id);
                            //从流式面板移除当前这个人的布局
                            adminPane.getChildren().remove(vBox);
                        }
                    });

                    //修改密码
                    Button psBtn = new Button("修改密码");
                    psBtn.getStyleClass().add("warning-theme");
                    psBtn.setOnAction(psevent -> {
                        //创建新对话框
                        TextInputDialog cgpsDialog = new TextInputDialog();
                        cgpsDialog.setTitle("修改密码");
                        cgpsDialog.setHeaderText("管理员：" + admin.getName());
                        cgpsDialog.setContentText("请输入新密码：");
                        Optional<String> resultps = cgpsDialog.showAndWait();
                        if (resultps.isPresent()) {
                            String password = resultps.get();
                            admin.setPassword(password);
                            adminService.updateAdmin(admin);
                        }
                    });

                    //返回按钮
                    Button reBtn = new Button("返回");
                    reBtn.getStyleClass().add("warning-theme");
                    reBtn.setOnAction(retevent -> {
                        vBox.getChildren().clear();
                        vBox.getChildren().addAll(imageView, nameLabel, idLabel);
                    });

                    vBox.getChildren().addAll(delBtn, psBtn, reBtn);
                }
            });
        }
    }

    public void searchBySellerName(ActionEvent actionEvent) {
        adminPane.getChildren().clear();
        String keywords = keywordsField.getText().trim();
        adminList = adminService.getAdminByName(keywords);
        for (Admin admin : adminList) {
            ImageView imageView1 = new ImageView(new Image(admin.getAvatar()));
            imageView1.setFitWidth(80);
            imageView1.setFitHeight(80);
            Circle circle1 = new Circle();
            circle1.setCenterX(40.0);
            circle1.setCenterY(40.0);
            circle1.setRadius(40.0);
            imageView1.setClip(circle1);
            Label nameLabel1 = new Label(admin.getName());
            Label idLabel1 = new Label(admin.getNumber());
            VBox vBox = new VBox();
            vBox.setPrefSize(150, 120);
            vBox.getStyleClass().add("box");
            vBox.setSpacing(30);
            vBox.setAlignment(Pos.TOP_CENTER);
            vBox.getChildren().addAll(imageView1, nameLabel1, idLabel1);
            adminPane.getChildren().add(vBox);
        }
    }


    public void addAdminStage(ActionEvent actionEvent) throws Exception {
        Stage addAdminStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/add_admin.fxml"));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/style.css");
        AddAdminController addAdminController = fxmlLoader.getController();
        addAdminController.setAdminData(adminData);
        addAdminStage.setTitle("新增收银员界面");
        addAdminStage.setResizable(false);
        addAdminStage.setScene(scene);
        addAdminStage.show();
    }
}
