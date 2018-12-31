package com.soft1841.cn.controller;

import com.soft1841.cn.dao.GoodsDAO;
import com.soft1841.cn.dao.TypeDAO;
import com.soft1841.cn.entity.Detail;
import com.soft1841.cn.entity.Goods;
import com.soft1841.cn.entity.Type;
import com.soft1841.cn.service.GoodsService;
import com.soft1841.cn.service.TypeService;
import com.soft1841.cn.service.impl.TypeServiceImpl;
import com.soft1841.cn.utils.DAOFactory;
import com.soft1841.cn.utils.ServiceFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;;

import java.awt.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

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
    private ComboBox<Type> typeComboBox;

    //商品模型数据集合，可以实时相应数据变化，无需刷新
    private ObservableList<Goods> goodsData = FXCollections.observableArrayList();
    //类型模型数据集合
    private ObservableList<Type> typeData = FXCollections.observableArrayList();
    //类别GoodsService对象
    private GoodsService goodsService = ServiceFactory.getGoodsServiceInstance();
    //类别TypeService对象
    private TypeService typeService = ServiceFactory.getTypeServiceInstance();
    //商品集合，存放数据库类别表查询结果
    private List<Goods> goodsList = null;
    //类别集合，存放数据库类别表查询结果
    private List<Type> typeList = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        goodsList = goodsService.getAllGoods();
        showGoods(goodsList);
        initComBox();

    }

    private void initComBox() {
        //1.到数据库查询所有的类别
        typeList = typeService.getAllTypes();
        //2.将typeList集合加入typeData模型数据集合
        typeData.addAll(typeList);
        //3.将数据模型设置给下拉框
        typeComboBox.setItems(typeData);
        //4.下拉框选择事件监听，根据选择不同的类别，过滤出该类别的商品
        typeComboBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            //移除掉之前的数据
            goodsPane.getChildren().removeAll(goodsData);
            goodsList = goodsService.getGoodsByTypeId(newValue.getId());
            showGoods(goodsList);
        });

    }

    private void showGoods(List<Goods> goodsList) {
        ObservableList<Node> observableList = goodsPane.getChildren();
        goodsPane.getChildren().removeAll(Collections.singleton(observableList));
        goodsPane.getChildren().clear();
        //通过循环遍历readerList集合，创建HBox来显示每个商品信息
        for (Goods goods : goodsList) {
            VBox vBox = new VBox();
            vBox.setPrefSize(320, 300);
            vBox.setSpacing(10);
            vBox.setPadding(new Insets(10, 10, 10, 10));
            vBox.getStyleClass().add("box");

            //创建上面垂直布局
            VBox topBox = new VBox();
            topBox.setStyle("-fx-pref-width: 320;-fx-pref-height: 230");
            topBox.setSpacing(10);
            topBox.setAlignment(Pos.TOP_CENTER);
            //创建下面垂直布局
            HBox bottomBox = new HBox();
            bottomBox.setStyle("-fx-pref-width: 320;-fx-pref-height: 30");
            bottomBox.setSpacing(10);
            bottomBox.setAlignment(Pos.BOTTOM_LEFT);

            //价格
            TextField priceLabel = new TextField("价格：" + goods.getPrice());
            priceLabel.getStyleClass().add("role-name");
            priceLabel.setEditable(false);
            //商品名
            TextField nameLabel = new TextField(goods.getName());
            nameLabel.getStyleClass().add("font-title");
            nameLabel.setEditable(false);
            //条码
            TextField barCodeLabel = new TextField("条码：" + goods.getBarCode());
            barCodeLabel.getStyleClass().add("font-title");
            barCodeLabel.setEditable(false);
            //库存
            TextField quantityLabel = new TextField("库存：" + goods.getQuantity());
            quantityLabel.getStyleClass().add("font-title");
            quantityLabel.setEditable(false);
            //描述
            TextField descriptionLabel = new TextField(goods.getDescription());
            descriptionLabel.getStyleClass().add("font-title");
            descriptionLabel.setEditable(false);

            //删除按钮
            Button delBtn = new Button("删除");
            delBtn.setStyle("-fx-pref-height:35;-fx-pref-width: 100");
            delBtn.setOnAction(event -> {
                //弹出一个确认对话框
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("确认对话框");
                alert.setContentText("确定要删除此纪录吗？");
                //确认
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    //得到id
                    long id = goods.getId();
                    goodsService.deleteGoodsByID(id);
                    //从流式面板删除
                    goodsPane.getChildren().remove(vBox);
                }
            });

            //编辑按钮
            Button alterBtn = new Button("编辑");
            alterBtn.setStyle("-fx-pref-height:35;-fx-pref-width: 100 ");
            alterBtn.setOnAction(event -> {
                nameLabel.setEditable(true);
                nameLabel.getStyleClass().add("textField");
                descriptionLabel.setEditable(true);
                descriptionLabel.getStyleClass().add("textField");
                priceLabel.setEditable(true);
                priceLabel.getStyleClass().add("textField");
                quantityLabel.setEditable(true);
                quantityLabel.getStyleClass().add("textField");
                barCodeLabel.setEditable(true);
                barCodeLabel.getStyleClass().add("textField");
            });


            //确认按钮
            Button yesBtn = new Button("确认");
            yesBtn.setStyle("-fx-pref-height:35;-fx-pref-width: 100");
            yesBtn.setOnAction(event -> {
                //价格
                priceLabel.getStyleClass().addAll("role-name","text-field");
                priceLabel.setEditable(false);
                //商品名
                nameLabel.getStyleClass().addAll("font-title","text-field");
                nameLabel.setEditable(false);
                //条码
                barCodeLabel.getStyleClass().addAll("font-title","text-field");
                barCodeLabel.setEditable(false);
                //库存
                quantityLabel.getStyleClass().addAll("font-title","text-field");
                quantityLabel.setEditable(false);
                //描述
                descriptionLabel.getStyleClass().addAll("font-title","text-field");
                descriptionLabel.setEditable(false);
                //获取输入框中的信息
                String name = nameLabel.getText().trim();
                String description = descriptionLabel.getText().trim();
                String price = priceLabel.getText().trim();
                String quantity = quantityLabel.getText().trim();
                String barCode = barCodeLabel.getText().trim();
                goods.setName(name);
                goods.setDescription(description);
                goods.setPrice(price);
                goods.setQuantity(quantity);
                goods.setBarCode(barCode);
                goodsService.updateGoods(goods);

            });


            //按钮美化
            delBtn.getStyleClass().addAll("btn-basic", "btn-radius-large", "blue-theme");
            alterBtn.getStyleClass().addAll("btn-basic", "btn-radius-large", "blue-theme");
            yesBtn.getStyleClass().addAll("btn-basic", "btn-radius-large", "blue-theme");

            //加入
            bottomBox.getChildren().addAll(alterBtn, delBtn, yesBtn);
            topBox.getChildren().addAll(nameLabel, descriptionLabel, priceLabel, quantityLabel, barCodeLabel);
            //加入卡片
            vBox.getChildren().addAll(topBox, bottomBox);
            goodsPane.getChildren().add(vBox);
            //双击事件
            vBox.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    vBox.getChildren().clear();
                    ImageView avatarImg = new ImageView(new Image(goods.getAvatar()));
                    avatarImg.setFitWidth(150);
                    avatarImg.setFitHeight(150);
                    vBox.getChildren().addAll(avatarImg);

                    //再次双击返回
                    vBox.setOnMouseClicked(event1 -> {
                        if (event1.getClickCount() == 2) {
                            vBox.getChildren().clear();
                            vBox.getChildren().addAll(topBox, bottomBox);
                        }
                    });
                }
            });

        }

    }


    private void showGoodsData(List<Goods> goodsList) {
        goodsData.addAll(goodsList);
        goodsPane.getChildren().addAll((Node) goodsData);
    }

    //弹出新增界面方法
    public void newGoodsStage() throws Exception {
        Stage addGoodsStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/add_goods.fxml"));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/style.css");
        AddGoodsController addGoodsController = fxmlLoader.getController();
        addGoodsController.setGoodsData(goodsData);
        addGoodsStage.setTitle("新增商品界面");
        //界面大小不可变
        addGoodsStage.setResizable(false);
        addGoodsStage.setScene(scene);
        addGoodsStage.show();
    }


    public void enter() {
        keywordsField.setText("");
    }

    public void searchByKeywords() {
        goodsPane.getChildren().removeAll(goodsData);
        String keywords = keywordsField.getText().trim();
        goodsList = goodsService.getGoodsLike(keywords);
        //通过条码查找（待修改）
//        String barCode = keywordsField.getText().trim();
//        goodsList = goodsService.getGoodsByBarCode(barCode);
        showGoods(goodsList);
    }
}




