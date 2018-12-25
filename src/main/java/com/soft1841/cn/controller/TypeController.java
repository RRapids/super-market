package com.soft1841.cn.controller;

import cn.hutool.db.Entity;
import com.soft1841.cn.dao.TypeDAO;
import com.soft1841.cn.entity.Type;
import com.soft1841.cn.utils.ComponentUtil;
import com.soft1841.cn.utils.DAOFactory;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class TypeController implements Initializable {
    @FXML
    private TableView typeTable;

    private ObservableList<Type> typeData = FXCollections.observableArrayList();

    private TypeDAO typeDAO = DAOFactory.getTypeDAOInstance();

    private List<Entity> entityList = null;

    private TableColumn<Type, Type> delCol = new TableColumn<>("操作");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //水平方向不显示滚动条
        typeTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        //在表格最后加入删除按钮
        delCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        delCol.setCellFactory(param -> new TableCell<Type, Type>() {
            private final Button deleteButton = ComponentUtil.getButton("删除", "warning-theme");

            @Override
            protected void updateItem(Type type, boolean empty) {
                super.updateItem(type, empty);
                if (type == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(deleteButton);
                //点击删除按钮，需要将这一行从表格移除，同时从底层数据库真正删除
                deleteButton.setOnAction(event -> {
                    //删除操作之前，弹出确认对话框，点击确认按钮才删除
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("确认对话框");
                    alert.setHeaderText("请确认");
                    alert.setContentText("确定要删除这行记录吗?");
                    Optional<ButtonType> result = alert.showAndWait();
                    //点击了确认按钮，执行删除操作，同时移除一行模型数据
                    if (result.get() == ButtonType.OK) {
                        typeData.remove(type);
                        try {
                            typeDAO.deleteTypeById(type.getId());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        //删除列加入表格
        typeTable.getColumns().add(delCol);
        try {
            entityList = typeDAO.selectAllTypes();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        showTypeData(entityList);
    }

    private void showTypeData(List<Entity> entityList) {
        //遍历实体集合
        for (Entity entity : entityList) {
            //取出属性，创建Type的对象
            Type type = new Type();
            type.setId(entity.getInt("id"));
            type.setTypeName(entity.getStr("name"));
            //加入ObservableList模型数据集合
            typeData.add(type);
        }
        typeTable.setItems(typeData);

    }

    public void addType() {
        //创建一个输入对话框
        TextInputDialog dialog = new TextInputDialog("新类别");
        dialog.setTitle("商品类别");
        dialog.setHeaderText("新增商品类别");
        dialog.setContentText("请输入商品类别名称:");
        Optional<String> result1 = dialog.showAndWait();
//        result.ifPresent(name -> System.out.println("你的输入： " + name));
        //确认输入了内容
        if (result1.isPresent()) {
            //获得输入的内容
            String typeName = result1.get();
            //创建一个Type对象，插入数据库，并返回主键
            Type type = new Type();
            type.setTypeName(typeName);
            long id = 0;
            try {
                id = typeDAO.insertType(type);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            type.setId(id);
            //加入ObservableList，刷新模型视图，不用重新查询数据库也可以立刻看到结果
            typeData.add(type);

        }
    }


}

