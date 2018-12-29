package com.soft1841.cn.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class DefaultController implements Initializable {

    @FXML
    private ImageView smImg;

    //轮播图资源数组
    String[] imgPath = {"e.png", "f.png", "g.png", "h.png", "i.png"};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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