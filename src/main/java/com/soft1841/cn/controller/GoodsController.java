package com.soft1841.cn.controller;

import cn.hutool.db.Entity;
import com.soft1841.cn.dao.GoodsDAO;
import com.soft1841.cn.entity.Goods;
import com.soft1841.cn.utils.DAOFactory;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;

/**
 * 商品
 *
 * @author 袁腾飞
 */
public class GoodsController implements Initializable {
    //获得布局文件中的表格对象
    @FXML
    private FlowPane goodsPane;

    private GoodsDAO goodsDAO = DAOFactory.getGoodsDAOInstance();
    List<Entity> goodsList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            goodsList = goodsDAO.getAllGoods();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        showGoods(goodsList);
    }

    private void showGoods(List<Entity> goodsList) {
        ObservableList<Node> observableList = goodsPane.getChildren();
        goodsPane.getChildren().removeAll(Collections.singleton(observableList));
        //通过循环遍历readerList集合，创建Hbox来显示每个读者信息
        for (Entity entity : goodsList) {
            HBox hBox = new HBox();
            hBox.setPrefSize(300, 280);
            hBox.setSpacing(10);
            hBox.setPadding(new Insets(10, 10, 10, 10));
            hBox.getStyleClass().add("box");
            //创建左侧垂直布局
            VBox leftBox = new VBox();
            leftBox.setSpacing(10);
            leftBox.setAlignment(Pos.TOP_CENTER);
            //头像
            ImageView avatarImg = new ImageView(new Image(entity.getStr("avatar")));
            //价格
            Label priceLabel = new Label(entity.getStr("price"));
            priceLabel.getStyleClass().add("role-name");
            //创建右侧垂直布局
            VBox rightBox = new VBox();
            rightBox.setSpacing(15);
            rightBox.setAlignment(Pos.TOP_RIGHT);
            //姓名
            Label nameLabel = new Label(entity.getStr("name"));
            nameLabel.getStyleClass().add("font-title");
            //条码
            Label barCodeLabel = new Label(entity.getStr("barCode"));
            //数量
            Label quantityLabel = new Label(entity.getStr("quantity"));
            //点击删除按钮做的事件
            Button delBtn = new Button("删除");
            //点击删除按钮做的事件
            delBtn.setOnAction(event -> {
                //弹出一个确认对话框
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("确认对话框");
                alert.setContentText("确定要删除此纪录吗？");
                //确认
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    try {
                        //得到id
                        long id = entity.getLong("id");
                        goodsDAO.deleteGoodsByID(id);
                        //从流式面板删除
                        goodsPane.getChildren().remove(hBox);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
            //按钮美化
            delBtn.getStyleClass().addAll("btn-basic", "warning-theme", "btn-radius-large");
            //头像大小
            avatarImg.setFitWidth(80);
            avatarImg.setFitHeight(80);
            //给头像设置圆形（为头像大小的一半）
            Circle circle = new Circle();
            circle.setCenterX(40);
            circle.setCenterY(40);
            circle.setRadius(40);
            avatarImg.setClip(circle);
            //头像加入左边
            leftBox.getChildren().add(avatarImg);
            leftBox.getChildren().add(priceLabel);
            rightBox.getChildren().addAll(nameLabel, barCodeLabel, quantityLabel, delBtn);
            //左边加入卡片
            hBox.getChildren().add(leftBox);
            hBox.getChildren().add(rightBox);
            goodsPane.getChildren().add(hBox);
        }
    }
}

