package com.soft1841.cn.controller;

import com.soft1841.cn.entity.Seller;
import com.soft1841.cn.service.GoodsService;
import com.soft1841.cn.utils.ServiceFactory;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;


public class CashierController {

    @FXML
    private TextField barCodeField;

    @FXML
    private javafx.scene.control.Button exitButton;

//    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/cashier.fxml"));

    private GoodsService goodsService = ServiceFactory.getGoodsServiceInstance();

    public void barCodeEnter() throws Exception {
        String barCode = barCodeField.getText().trim();
        goodsService.getGoodsByBarCode(barCode);

        if (goodsService.barCodeEnter(barCode)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示");
            alert.setContentText("测试");
            alert.showAndWait();
            System.out.println(barCode);
        }
    }

    @FXML
    public void exitButton() throws Exception {
        Stage loginStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 570, 395);
        scene.getStylesheets().add("/css/style.css");
        loginStage.setTitle("登录");
        loginStage.setMaximized(true);
        loginStage.setScene(scene);
        loginStage.show();
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
