package com.soft1841.cn.controller;

import com.soft1841.cn.service.GoodsService;
import com.soft1841.cn.utils.ServiceFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Alert;

import java.awt.*;


public class CashierController {

    @FXML
    private TextField barCodeField;

    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/cashier.fxml"));

    private GoodsService goodsService = ServiceFactory.getGoodsServiceInstance();

    public void barCodeEnter() throws Exception {
        String barCode = barCodeField.getText().trim();
        if (goodsService.barCodeEnter(barCode)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示");
            alert.setContentText("测试");
            alert.showAndWait();

        }


    }
}
