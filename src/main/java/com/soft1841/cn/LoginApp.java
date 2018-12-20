package com.soft1841.cn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginApp extends Application {
    public void start(Stage primaryStage) throws Exception {
        //加载布局文件
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        Parent root = fxmlLoader.load();
        //给舞台设置标题
        primaryStage.setTitle("收银系统登录");
        //创建场景
        Scene scene = new Scene(root,500,400);
        //读入样式文件
        scene.getStylesheets().add("/css/loginStyle.css");
        //场景加入舞台
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
