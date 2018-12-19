package com.soft1841.cn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        primaryStage.setTitle("登录界面");
        primaryStage.setScene(new Scene(root, 566, 495));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
