package com.soft1841.cn.controller;

import com.soft1841.cn.entity.Goods;
import com.soft1841.cn.entity.Seller;
import com.soft1841.cn.service.GoodsService;
import com.soft1841.cn.utils.ServiceFactory;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


import java.util.List;

public class CashierController {

    @FXML
    private TextField barCodeField;

    @FXML
    private javafx.scene.control.Button exitButton;

    @FXML
    private TableView<Goods> goodsTable;

    //商品模型数据集合，可以实时相应数据变化，无需刷新
    private ObservableList<Goods> goodsData = FXCollections.observableArrayList();
    //商品Service对象，从DAO工厂通过静态方法获得
    private GoodsService goodsService = ServiceFactory.getGoodsServiceInstance();
    //商品集合，存放数据库图书表各种查询的结果
    private List<Goods> goodsList = null;

    //显示商品表格数据方法
    private  void showGoodsData(List<Goods> goodsList){
        goodsData.addAll(goodsList);
        goodsTable.setItems(goodsData);
    }
    //通过条码搜索商品事件
    public void barCodeEnter() throws Exception {
        String barCode = barCodeField.getText();
        goodsList = goodsService.getGoodsByBarCode(barCode);
        goodsTable.getItems().addAll(goodsData);
        showGoodsData(goodsList);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示");
        alert.setContentText("测试");
        alert.showAndWait();

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

//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        //开启一个UI线程 ,将登录界面传过来的管理员信息显示在主界面的右上角
//        Platform.runLater(new Runnable() {
//            @Override
//            public void run() {
//                Image image = new Image(seller.getAvatar());
//                sellerAvatar.setImage(image);
//                //将头像显示为圆形
//                Circle circle = new Circle();
//                circle.setCenterX(20.0);
//                circle.setCenterY(20.0);
//                circle.setRadius(20.0);
//                sellerAvatar.setClip(circle);
//                //显示管理员姓名
//                sellerName.setText(seller.getName());
//            }
//        });
//        //启一个线程，用来同步获取系统时间
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    //获取系统当前时间
//                    LocalDateTime now = LocalDateTime.now();
//                    //格式化时间
//                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
//                    String timeString = dateTimeFormatter.format(now);
//                    //启一个UI线程
//                    Platform.runLater(new Runnable() {
//                        @Override
//                        public void run() {
//                            //将格式化后的日期时间显示在标签上
//                            timeLabel2.setText(timeString);
//                        }
//                    });
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        System.err.println("中断异常");
//                    }
//                }
//            }
//        }).start();
//
//        try {
//            AnchorPane anchorPane = new FXMLLoader(getClass().getResource("/fxml/cashier.fxml")).load();
//            CashierController.getChildren().add(anchorPane);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static Collection<AnchorPane> getChildren() {
//        return null;
//    }
}
