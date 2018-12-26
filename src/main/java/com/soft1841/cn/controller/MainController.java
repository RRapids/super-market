package com.soft1841.cn.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController{
    @FXML
    private StackPane mainContainer;

    @FXML
    private javafx.scene.control.Button closeButton;

    @FXML
    private javafx.scene.control.Button exitButton;

    @FXML
    private void exitButtonAction() throws Exception {
        Stage loginStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 570, 395);
        scene.getStylesheets().add("/css/style.css");
        loginStage.setTitle("登录");
        loginStage.setScene(scene);
        loginStage.show();
        Stage mainStage = (Stage) exitButton.getScene().getWindow();
        mainStage.close();
    }

    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    //封装一个切换视图的方法：用来根据fxml文件切换视图内容
    private void switchView(String fileName) throws Exception {
        //清空原有内容
        mainContainer.getChildren().clear();
        AnchorPane anchorPane = new FXMLLoader(getClass().getResource("/fxml/" + fileName)).load();
        mainContainer.getChildren().add(anchorPane);
    }

    //显示默认主页数据
    public void listDefault() throws Exception {
        switchView("default.fxml");
    }

    public void listType() throws Exception {
        switchView("type.fxml");
    }

    public void listGoods() throws Exception {
        switchView("goods.fxml");
    }

}
