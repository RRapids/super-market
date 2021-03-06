package com.soft1841.cn.controller;

import com.soft1841.cn.entity.Admin;
import com.soft1841.cn.entity.Detail;
import com.soft1841.cn.entity.Ticket;
import com.soft1841.cn.service.DetailService;
import com.soft1841.cn.service.TicketService;
import com.soft1841.cn.utils.ExcelExportDetails;
import com.soft1841.cn.utils.ExcelExportTicket;
import com.soft1841.cn.utils.ServiceFactory;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;


public class MainController implements Initializable{
    @FXML
    private StackPane mainContainer;

    @FXML
    private javafx.scene.control.Button closeButton;

    @FXML
    private javafx.scene.control.Button exitButton;

    @FXML
    private ImageView adminAvatar;

    @FXML
    private Label adminName;

    @FXML
    private Label timeLabel;

    private List<Detail> detailList = null;

    private List<Ticket> ticketList = null;

    private Admin admin;

    private DetailService detailService = ServiceFactory.getDetailServiceInstance();

    private TicketService ticketService = ServiceFactory.getTicketServiceInstance();

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @FXML
    private void exitButtonAction() throws Exception {
        Stage loginStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 570, 395);
        scene.getStylesheets().add("/css/style.css");
        loginStage.setTitle("登录");
        loginStage.setMaximized(true);
        loginStage.setScene(scene);
        loginStage.show();
        Stage mainStage = (Stage) exitButton.getScene().getWindow();
        mainStage.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //开启一个UI线程 ,将登录界面传过来的管理员信息显示在主界面的右上角
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Image image = new Image(admin.getAvatar());
                adminAvatar.setImage(image);
                //将头像显示为圆形
                Circle circle = new Circle();
                circle.setCenterX(35.0);
                circle.setCenterY(35.0);
                circle.setRadius(35.0);
                adminAvatar.setClip(circle);
                //显示管理员姓名
                adminName.setText(admin.getName());
            }
        });


        //启一个线程，用来同步获取系统时间
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
                    String timeString = dateTimeFormatter.format(now);
                    //启一个UI线程
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            //将格式化后的日期时间显示在标签上
                            timeLabel.setText(timeString);
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.err.println("中断异常");
                    }
                }
            }
        }).start();

        try {
                AnchorPane anchorPane = new FXMLLoader(getClass().getResource("/fxml/default.fxml")).load();
            mainContainer.getChildren().add(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

        detailList = detailService.getAllDetails();

        ticketList = ticketService.getAllTicket();

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

    public void listSeller()throws Exception{
        switchView("seller.fxml");
    }

    public void listMember() throws Exception{
        switchView("member.fxml");
    }

    public void listAdmin() throws Exception {
        switchView("admin.fxml");
    }

    public void listGoodsAnalysis()throws Exception{
        switchView("goods_analysis.fxml");
    }

    //数据导出方法，采用hutool提供的工具类
    public void exportDetail() {
        ExcelExportDetails.export(detailList);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示信息");
        alert.setHeaderText("明细数据已导出!请到D盘根目录查看!");
        alert.showAndWait();
    }

    public void exportTicket() {
        ExcelExportTicket.export(ticketList);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示信息");
        alert.setHeaderText("小票数据已导出!请到D盘根目录查看!");
        alert.showAndWait();
    }

}
