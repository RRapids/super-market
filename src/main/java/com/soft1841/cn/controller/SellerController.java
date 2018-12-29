package com.soft1841.cn.controller;

import com.soft1841.cn.entity.Seller;
import com.soft1841.cn.service.SellerService;
import com.soft1841.cn.utils.ServiceFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

/**
 * 管理收银员的控制器
 *
 * @author Yue Tang
 */

public class SellerController implements Initializable {

    @FXML
    private FlowPane sellerPane;

    private SellerService sellerService = ServiceFactory.getSellerServiceInstance();

    private List<Seller> sellerList = new ArrayList<>();

    private ObservableList<Seller> sellerData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sellerList = sellerService.selectAllSellers();
        listSeller(sellerList);
    }

    //通过循环遍历readerList集合，创建Hbox来显示每个读者信息
    //还有删除以及修改密码
    private void listSeller(List<Seller> sellerList) {
        //移除之前的记录
        sellerPane.getChildren().clear();
        for (Seller seller : sellerList
        ) {
            VBox vBox = new VBox();
            vBox.setPrefSize(150, 120);
            vBox.getStyleClass().add("box");
            vBox.setSpacing(30);
            vBox.setAlignment(Pos.TOP_CENTER);
            ImageView imageView = new ImageView(new Image(seller.getAvatar()));
            imageView.setFitWidth(80);
            imageView.setFitHeight(80);
            Circle circle = new Circle();
            circle.setCenterX(40.0);
            circle.setCenterY(40.0);
            circle.setRadius(40.0);
            imageView.setClip(circle);
            Label nameLabel = new Label(seller.getName());
            Label idLabel = new Label("账号：" + seller.getNumber());
            vBox.getChildren().addAll(imageView, nameLabel, idLabel);
            sellerPane.getChildren().add(vBox);
            //
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
                            long id = seller.getId();
                            //删除掉这行记录
                            sellerService.deleteSellerById(id);
                            //从流式面板移除当前这个人的布局
                            sellerPane.getChildren().remove(vBox);
                        }
                    });

                    //修改密码
                    Button psBtn = new Button("修改密码");
                    psBtn.getStyleClass().add("warning-theme");
                    psBtn.setOnAction(psevent -> {
                        //创建新对话框
                        TextInputDialog cgpsDialog = new TextInputDialog();
                        cgpsDialog.setTitle("修改密码");
                        cgpsDialog.setHeaderText("收银员：" + seller.getName());
                        cgpsDialog.setContentText("请输入新密码：");
                        Optional<String> resultps = cgpsDialog.showAndWait();
                        if (resultps.isPresent()) {
                            String password = resultps.get();
                            seller.setPassword(password);
                            sellerService.updateSeller(seller);
                        }
                    });

                    //返回按钮
                    Button reBtn = new Button("返回");
                    reBtn.getStyleClass().add("warning-theme");
                    reBtn.setOnAction(retevent -> {
                        vBox.getChildren().clear();
                        ImageView imageView1 = new ImageView(new Image(seller.getAvatar()));
                        imageView1.setFitWidth(80);
                        imageView1.setFitHeight(80);
                        Circle circle1 = new Circle();
                        circle1.setCenterX(40.0);
                        circle1.setCenterY(40.0);
                        circle1.setRadius(40.0);
                        imageView.setClip(circle1);
                        Label nameLabel1 = new Label(seller.getName());
                        Label idLabel1 = new Label(seller.getNumber());
                        vBox.getChildren().addAll(imageView1, nameLabel1, idLabel1);
                    });

                    vBox.getChildren().addAll(delBtn, psBtn, reBtn);
                }
            });
        }

    }

    public void addSellerStage() throws Exception {
        Stage addSellerStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/add_seller.fxml"));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/style.css");
        AddSellerController addSellerController = fxmlLoader.getController();
        addSellerController.setSellerData(sellerData);
        addSellerStage.setTitle("新增收银员界面");
        addSellerStage.setResizable(false);
        addSellerStage.setScene(scene);
        addSellerStage.show();
    }


}