package com.soft1841.cn.controller;

import cn.hutool.db.Entity;
import com.soft1841.cn.dao.GoodsDAO;
import com.soft1841.cn.entity.Detail;
import com.soft1841.cn.entity.Goods;
import com.soft1841.cn.utils.DAOFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.apache.poi.ss.formula.functions.T;

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
    @FXML
    private TextField keywordsField;
    @FXML
    private ComboBox typeComboBox;

    private GoodsDAO goodsDAO = DAOFactory.getGoodsDAOInstance();

    private List<Entity> goodsList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            goodsList = goodsDAO.getAllGoods();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        showGoods(goodsList);

        typeComboBox.getItems().setAll("服装类", "食品类", "生活用品类", "学习工具类", "小礼品类", "摆件类", "医药类", "手机类", "电脑类");
    }

    private void showGoods(List<Entity> goodsList) {
        ObservableList<Node> observableList = goodsPane.getChildren();
        goodsPane.getChildren().removeAll(Collections.singleton(observableList));
        //通过循环遍历readerList集合，创建HBox来显示每个商品信息
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
            //商品名
            Label nameLabel = new Label(entity.getStr("name"));
            nameLabel.getStyleClass().add("font-title");
            //条码
            Label barCodeLabel = new Label(entity.getStr("barCode"));
            //数量
            Label quantityLabel = new Label(entity.getStr("quantity"));
            //描述
            Label descriptionLabel = new Label(entity.getStr("description"));
            //类别
            Label typeNameLabel = new Label(entity.getStr("typename"));
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
            //商品图片大小
            avatarImg.setFitWidth(80);
            avatarImg.setFitHeight(80);
            //给商品图设置圆形（为头像大小的一半）
            Circle circle = new Circle();
            circle.setCenterX(40);
            circle.setCenterY(40);
            circle.setRadius(40);
            avatarImg.setClip(circle);
            //商品图加入左边
            leftBox.getChildren().add(avatarImg);
            leftBox.getChildren().add(priceLabel);
            rightBox.getChildren().addAll(typeNameLabel, nameLabel, descriptionLabel, quantityLabel, barCodeLabel, delBtn);
            //左边加入卡片
            hBox.getChildren().add(leftBox);
            hBox.getChildren().add(rightBox);
            goodsPane.getChildren().add(hBox);
        }
    }

        //新增商品方法
    public void addGoods() throws SQLException {
        //创建Goods对象
        Goods goods = new Goods();
        //新建舞台
        Stage stage = new Stage();
        stage.setTitle("新增商品界面");
        //创建垂直布局，以便放入新增组件
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(20, 10, 10, 10));

        TextField nameField = new TextField();
        nameField.setPromptText("请输入商品");
        TextField avatarField = new TextField();
        avatarField.setPromptText("请输入商品图");

        //选择商品类别
        String[] departments = {"服装类", "食品类", "生活用品类", "学习工具类", "小礼品类", "摆件类", "医药类", "手机类", "电脑类"};
        List<String> list = Arrays.asList(departments);
        //将list中的数据加入observableList
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(list);
        //创建院系下拉框
        ComboBox<String> depComboBox = new ComboBox<>();
        depComboBox.setPromptText("选择类别");
        //给下拉框初始化值
        depComboBox.setItems(observableList);
        //选中改变
        depComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                goods.setTypename(newValue);

            }
        });
        //条码输入框
        TextField barCodeField = new TextField();
        barCodeField.setPromptText("请输入条码");
        //价格输入框
        TextField priceField = new TextField();
        priceField.setPromptText("请输入价格");
        //货存输入框
        TextField quantityField = new TextField();
        quantityField.setPromptText("货存");
        //描述框
        TextField descriptionField = new TextField();
        descriptionField.setPromptText("描述");
        //新增按钮
        Button addBtn = new Button("新增");
        addBtn.getStyleClass().add("blue-theme");
        vBox.getChildren().addAll(nameField, depComboBox, avatarField, barCodeField, priceField, quantityField, descriptionField, addBtn);
        Scene scene = new Scene(vBox, 304, 380);
        scene.getStylesheets().add("/css/style.css");
        stage.setScene(scene);
        stage.show();
        //点击新增按钮，将界面数据封装成一个Reader对象，写入数据库
        addBtn.setOnAction(event -> {
            String nameString = nameField.getText().trim();
            String avatarString = avatarField.getText().trim();
            String barCodeString = barCodeField.getText().trim();
            String priceString = priceField.getText().trim();
            String quantityString = quantityField.getText().trim();
            String descriptionString = descriptionField.getText().trim();


            goods.setName(nameString);
            goods.setAvatar(avatarString);
            goods.setQuantity(quantityString);
            goods.setBarCode(barCodeString);
            goods.setDescription(descriptionString);
            goods.setPrice(priceString);
            System.out.println(goods.getName() + goods.getTypename() + goods.getBarCode());
            try {
                goodsDAO.insertGoods(goods);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            stage.close();
            //重新读取数据显示
            try {
                goodsList = goodsDAO.getAllGoods();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public void enter() {
        keywordsField.setText("");
    }

    //商品模糊查询
    public void searchByKeywords() throws SQLException {
        ObservableList<Node> observableList = goodsPane.getChildren();
        goodsPane.getChildren().removeAll(Collections.singleton(observableList));
        String keywords = keywordsField.getText().trim();
        //通过循环遍历goodsList集合，创建HBox来显示每个商品信息
        for (Entity goods : goodsList) {
            HBox hBox = new HBox();
            hBox.setPrefSize(300, 280);
            hBox.setSpacing(10);
            hBox.setPadding(new Insets(10, 10, 10, 10));
            hBox.getStyleClass().add("box");
            //创建左侧垂直布局
            VBox leftBox = new VBox();
            leftBox.setSpacing(10);
            leftBox.setAlignment(Pos.TOP_CENTER);
            //商品图片
            ImageView avatarImg = new ImageView(new Image(goods.getStr("avatar")));
            //价格
            Label priceLabel = new Label(goods.getStr("price"));
            priceLabel.getStyleClass().add("role-name");
            //创建右侧垂直布局
            VBox rightBox = new VBox();
            rightBox.setSpacing(15);
            rightBox.setAlignment(Pos.TOP_RIGHT);
            //商品名
            Label nameLabel = new Label(goods.getStr("name"));
            nameLabel.getStyleClass().add("font-title");
            //条码
            Label barCodeLabel = new Label(goods.getStr("barCode"));
            //数量
            Label quantityLabel = new Label(goods.getStr("quantity"));
            //描述
            Label descriptionLabel = new Label(goods.getStr("description"));
            //类别
            Label typeNameLabel = new Label(goods.getStr("typename"));
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
                        long id = goods.getLong("id");
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
            //商品图片大小
            avatarImg.setFitWidth(80);
            avatarImg.setFitHeight(80);
            //给商品图设置圆形（为头像大小的一半）
            Circle circle = new Circle();
            circle.setCenterX(40);
            circle.setCenterY(40);
            circle.setRadius(40);
            avatarImg.setClip(circle);
            //商品图加入左边
            leftBox.getChildren().add(avatarImg);
            leftBox.getChildren().add(priceLabel);
            rightBox.getChildren().addAll(typeNameLabel, nameLabel, descriptionLabel, quantityLabel, barCodeLabel, delBtn);
            //左边加入卡片
            hBox.getChildren().add(leftBox);
            hBox.getChildren().add(rightBox);

            //goodsPane.getChildren().add(hBox);
            if (goods.getStr("name").contains(keywords)) {
                goodsPane.getChildren().add(hBox);
            }
            if (goods.getStr("barCode").contains(keywords)) {
                goodsPane.getChildren().add(hBox);
            }
            if (goods.getStr("typename").contains(keywords)) {
                goodsPane.getChildren().add(hBox);
            }
            //goodsPane.getChildren().add(hBox);

        }

    }
}

