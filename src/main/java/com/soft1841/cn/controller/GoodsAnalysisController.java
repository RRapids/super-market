package com.soft1841.cn.controller;

import com.soft1841.cn.entity.Type;
import com.soft1841.cn.service.GoodsService;
import com.soft1841.cn.service.TypeService;
import com.soft1841.cn.utils.ServiceFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GoodsAnalysisController implements Initializable {
    @FXML
    private StackPane pieCharPane, barCharPane;


    private TypeService typeService = ServiceFactory.getTypeServiceInstance();
    private GoodsService goodsService = ServiceFactory.getGoodsServiceInstance();
    private ObservableList<PieChart.Data> pieCharData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initPieChart();
        initBarChart();
    }

    private void initPieChart() {
        List<Type> typeList = typeService.getAllTypes();
        for (Type type : typeList) {
            int count = goodsService.countByType(type.getId());
            pieCharData.add(new PieChart.Data(type.getTypeName(), count));

        }
        final PieChart chart = new PieChart(pieCharData);
        chart.setTitle("按商品类别统计饼图");
        pieCharPane.getChildren().add(chart);
    }

    private void initBarChart() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc =
                new BarChart<>(xAxis, yAxis);
        bc.setTitle("根据类别统计柱形图");
        xAxis.setLabel("商品类别");
        yAxis.setLabel("商品数量");
        XYChart.Series series = new XYChart.Series();
        series.setName("2018年统计数据");
        List<Type> typeList = typeService.getAllTypes();
        for (Type type : typeList) {
            int count = goodsService.countByType(type.getId());
            series.getData().add(new XYChart.Data(type.getTypeName(), count));
        }
        bc.getData().addAll(series);
        barCharPane.getChildren().add(bc);
    }
}