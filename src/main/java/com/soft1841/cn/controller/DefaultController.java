package com.soft1841.cn.controller;

import com.soft1841.cn.service.AnalysisService;
import com.soft1841.cn.utils.ServiceFactory;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class DefaultController implements Initializable {

    @FXML
    private ImageView smImg;
    @FXML
    private Label typeCount, goodsCount;
    private AnalysisService analysisService = ServiceFactory.getAnalysisServiceInstance();

    //轮播图资源数组
    String[] imgPath = {"e.jpg", "f.png", "g.jpg", "h.jpg", "i.png"};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        typeCount.setText("类别"+analysisService.getTypesCount()+"种");
        goodsCount.setText("商品"+analysisService.getGoodsCount()+"个");

        //新建一个线程，用来轮播
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    //循环读取图片数组
                    for (int i = 0; i < imgPath.length; i++) {
                        //用每个资源创建一个图片对象
                        Image image = new Image("/img/" + imgPath[i]);
                        //开启一个UI线程
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                //将创建的Image对象设置给ImageView
                                smImg.setImage(image);
                            }
                        });
                        try {
                            //休眠2秒
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //轮播到最后一张图的时候，回到第一张重新播放
                        if (i == imgPath.length - 1) {
                            i = 0;
                        }
                    }
                }
            }
        }).start();
    }


}