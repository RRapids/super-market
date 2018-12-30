package com.soft1841.cn.controller;

import com.soft1841.cn.entity.Goods;
import com.soft1841.cn.entity.Type;
import com.soft1841.cn.service.GoodsService;
import com.soft1841.cn.service.TypeService;
import com.soft1841.cn.utils.ServiceFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddGoodsController implements Initializable {
    private ObservableList<Goods> goodsData = FXCollections.observableArrayList();

    public ObservableList<Goods> getGoodsData() {
        return goodsData;
    }

    public void setGoodsData(ObservableList<Goods> goodsData) {
        this.goodsData = goodsData;
    }

    @FXML
    private ComboBox<Type> goodsType;
    @FXML
    private TextField goodsName, goodsAvatar, goodsBarCode, goodsPrice, goodsQuantity;
    @FXML
    private TextArea goodsDescription;

    private ObservableList<Type> typeData = FXCollections.observableArrayList();

    private GoodsService goodsService = ServiceFactory.getGoodsServiceInstance();

    private TypeService typeService = ServiceFactory.getTypeServiceInstance();

    private List<Type> typeList = null;

    private Long typeId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        typeList = typeService.getAllTypes();
        typeData.addAll(typeList);
        goodsType.setItems(typeData);
        goodsType.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                    typeId = newValue.getId();
                }
        );
    }
    public void addGoods(){
        String nameString = goodsName.getText().trim();
        String avatarString = goodsAvatar.getText().trim();
        String barCodeString = goodsBarCode.getText().trim();
        String priceString = goodsPrice.getText().trim();
        String quantityString = goodsQuantity.getText().trim();
        String descriptionString = goodsDescription.getText().trim();

        Goods goods = new Goods();
        goods.setTypeId(typeId);
        goods.setName(nameString);
        goods.setAvatar(avatarString);
        goods.setQuantity(quantityString);
        goods.setBarCode(barCodeString);
        goods.setDescription(descriptionString);
        goods.setPrice(priceString);
        long id = goodsService.addGoods(goods);
        goods.setId(id);
        this.getGoodsData().add(goods);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示信息");
        alert.setHeaderText("新增成功!");
        alert.showAndWait();
        Stage stage = (Stage) goodsName.getScene().getWindow();
        stage.close();
    }
}
