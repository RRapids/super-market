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
import javafx.stage.Stage;;

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
        typeList = typeService.selectAllTypes();
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
            HBox hBox = new HBox();
            hBox.setPrefSize(290, 280);
            hBox.setSpacing(10);
            hBox.setPadding(new Insets(10, 10, 10, 10));
            hBox.getStyleClass().add("box");
            //创建左侧垂直布局
            VBox leftBox = new VBox();
            leftBox.setSpacing(10);
            leftBox.setAlignment(Pos.TOP_CENTER);
            //价格
            Label priceLabel = new Label("价格：" + goods.getPrice());
            priceLabel.getStyleClass().add("role-name");
            //创建右侧垂直布局
            VBox rightBox = new VBox();
            rightBox.setSpacing(15);
            rightBox.setAlignment(Pos.TOP_RIGHT);
            //商品名
            Label nameLabel = new Label(goods.getName());
            nameLabel.getStyleClass().add("font-title");
            //条码
            Label barCodeLabel = new Label("条码：" + goods.getBarCode());
            //数量
            Label quantityLabel = new Label("库存：" + goods.getQuantity());
            //描述
            Label descriptionLabel = new Label(goods.getDescription());
            //类别
            Label typeNameLabel = new Label(goods.getTypename());
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

                    //得到id
                    long id = goods.getId();
                    goodsService.deleteGoodsByID(id);
                    //从流式面板删除
                    goodsPane.getChildren().remove(hBox);

                }
            });
            //按钮美化
            delBtn.getStyleClass().addAll("btn-basic", "warning-theme", "btn-radius-large");
            //添加修改按钮
            Button priceBtn = new Button("价格修改");
            priceBtn.getStyleClass().addAll("btn-basic", "warning-theme", "btn-radius-large");
            //事件
            priceBtn.setOnAction(event -> {
                TextInputDialog dialog = new TextInputDialog("请输入价格");
                dialog.setTitle("商品修改");
                dialog.setHeaderText("商品名" + goods.getName());
                dialog.setContentText("输入新价格");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    String priceSting = result.get();
                    goods.setPrice(priceSting);
                    goodsService.updateGoods(goods);

                }
            });
            Button quantityBtn = new Button("库存修改");
            quantityBtn.getStyleClass().addAll("btn-basic", "warning-theme", "btn-radius-large");
            //事件
            quantityBtn.setOnAction(event -> {
                TextInputDialog dialog = new TextInputDialog("请输入库存");
                dialog.setTitle("商品修改");
                dialog.setHeaderText("商品名" + goods.getName());
                dialog.setContentText("更改库存");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    String quantityString = result.get();
                    goods.setQuantity(quantityString);
                    goodsService.updateGoods(goods);
                }
            });
            Button descriptionBtn = new Button("描述修改");
            descriptionBtn.getStyleClass().addAll("btn-basic", "warning-theme", "btn-radius-large");
            //事件
            descriptionBtn.setOnAction(event -> {
                TextInputDialog dialog = new TextInputDialog("请输入库存");
                dialog.setTitle("商品修改");
                dialog.setHeaderText("商品名" + goods.getName());
                dialog.setContentText("修改描述");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    String descriptionString = result.get();
                    goods.setDescription(descriptionString);
                    goodsService.updateGoods(goods);
                }
            });

            rightBox.getChildren().addAll(priceBtn, quantityBtn, descriptionBtn, delBtn);
            leftBox.getChildren().addAll(typeNameLabel, nameLabel, descriptionLabel, priceLabel, quantityLabel, barCodeLabel);
            //左边加入卡片
            hBox.getChildren().addAll(leftBox, rightBox);
            goodsPane.getChildren().add(hBox);
            //双击事件
            hBox.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    hBox.getChildren().clear();
                    ImageView avatarImg = new ImageView(new Image(goods.getAvatar()));
                    avatarImg.setFitWidth(150);
                    avatarImg.setFitHeight(150);
//                    Button reBtn = new Button("返回");
//                    reBtn.getStyleClass().add("warning-theme");
//                    reBtn.setOnAction(reEvent -> {
//                        hBox.getChildren().clear();
//                        rightBox.getChildren().addAll(priceBtn, quantityBtn, descriptionBtn, delBtn);
//                        leftBox.getChildren().addAll(typeNameLabel, nameLabel, descriptionLabel, priceLabel, quantityLabel, barCodeLabel);
//                        hBox.getChildren().addAll(leftBox,rightBox);
//                        goodsPane.getChildren().add(hBox);
//                    });
                    hBox.getChildren().addAll(avatarImg);

                }
            });


        }
    }
//    //弹出新增图书界面方法
//    public void newStage() throws Exception {
////        Stage addBookStage = new Stage();
////        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/add_book.fxml"));
////        AnchorPane root = fxmlLoader.load();
////        Scene scene = new Scene(root);
////        scene.getStylesheets().add("/css/style.css");
//        AddGoodsController addGoodsController = fxmlLoader.getController();
//        addGoodsController.setGoodsData(goodsList);
//        addBookStage.setTitle("新增图书界面");
//        //界面大小不可变
//        addBookStage.setResizable(false);
//        addBookStage.setScene(scene);
//        addBookStage.show();
//    }

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

            goodsService.addGoods(goods);
            stage.close();
            //重新读取数据显示
            goodsList = goodsService.getAllGoods();
            showGoods(goodsList);
        });
    }

    public void enter() {
        keywordsField.setText("");
    }

    public void searchByKeywords() {
        goodsPane.getChildren().removeAll(goodsData);
        String keywords = keywordsField.getText().trim();
        goodsList = goodsService.getGoodsLike(keywords);
        //通过条码查找（待修改）
        String barCode = keywordsField.getText().trim();
        goodsList = goodsService.getGoodsByBarCode(barCode);
        showGoods(goodsList);
    }
}


