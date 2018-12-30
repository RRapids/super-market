package com.soft1841.cn.controller;

import com.soft1841.cn.entity.Member;
import com.soft1841.cn.service.MemberService;
import com.soft1841.cn.utils.ServiceFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MemberController implements Initializable {
    @FXML
    private FlowPane memberPane;

    @FXML
    private TextField keywordField;

    private MemberService memberService = ServiceFactory.getMemberServiceInstance();

    private List<Member> memberList = new ArrayList<>();

    private ObservableList<Member> memberData = FXCollections.observableArrayList();

    private static final int MAX_THREADS = 4;
    //线程池配置
    private final Executor exec = Executors.newFixedThreadPool(MAX_THREADS, runnable -> {
        Thread t = new Thread(runnable);
        t.setDaemon(true);
        return t;
    });

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        memberList = memberService.selectAllMember();
        listMember(memberList);
    }

    public void listMember(List<Member> memberList) {
        memberPane.getChildren().clear();
        for (Member member : memberList
        ) {
            VBox vBox = new VBox();
            vBox.setPrefSize(150, 120);
            vBox.getStyleClass().add("box");
            vBox.setSpacing(30);
            vBox.setAlignment(Pos.TOP_CENTER);
            Label nameLabel = new Label(member.getName());
            Label phLabel = new Label("电话：" + member.getPhone());
            Label daLabel = new Label("地址：" + member.getAddress());
            Label intLabel = new Label("积分：" + member.getIntegral());
            vBox.getChildren().addAll(nameLabel, phLabel, daLabel, intLabel);
            memberPane.getChildren().add(vBox);
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
                            long id = member.getId();
                            //删除掉这行记录
                            memberService.deleteMemberById(id);
                            //从流式面板移除当前这个人的布局
                            memberPane.getChildren().remove(vBox);
                        }
                    });

                    //修改电话
                    Button psBtn = new Button("修改电话");
                    psBtn.getStyleClass().add("warning-theme");
                    psBtn.setOnAction(psevent -> {
                        //创建新对话框
                        TextInputDialog cgpsDialog = new TextInputDialog();
                        cgpsDialog.setTitle("修改电话");
                        cgpsDialog.setContentText("请输入新电话：");
                        Optional<String> resultps = cgpsDialog.showAndWait();
                        if (resultps.isPresent()) {
                            String phone = resultps.get();
                            member.setPhone(phone);
                            memberService.updateMemberPhone(member);
                        }
                    });

                    //返回按钮
                    Button reBtn = new Button("返回");
                    reBtn.getStyleClass().add("warning-theme");
                    reBtn.setOnAction(retevent -> {
                        vBox.getChildren().clear();
                        Label phlabel = new Label(member.getPhone());
                        vBox.getChildren().addAll(nameLabel, phlabel, daLabel, intLabel);
                    });

                    vBox.getChildren().addAll(delBtn, psBtn, reBtn);

                }

            });

        }
    }

    public void searchMemberByName() {
        memberPane.getChildren().clear();
        String keywords = keywordField.getText().trim();
        memberList = memberService.getMemberByName(keywords);
        for (Member member : memberList
        ) {
            VBox vBox = new VBox();
            vBox.setPrefSize(150, 120);
            vBox.getStyleClass().add("box");
            vBox.setSpacing(30);
            vBox.setAlignment(Pos.TOP_CENTER);
            Label nameLabel = new Label(member.getName());
            Label phLabel = new Label("电话：" + member.getPhone());
            Label daLabel = new Label("地址：" + member.getAddress());
            Label intLabel = new Label("积分：" + member.getIntegral());
            vBox.getChildren().addAll(nameLabel, phLabel, daLabel, intLabel);
            memberPane.getChildren().add(vBox);
        }
    }

    public void addMemberStage() throws Exception {
        Stage addMemberStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/add_member.fxml"));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/style.css");
        AddMemberController addMemberController = fxmlLoader.getController();
        addMemberController.setMemberData(memberData);
        addMemberStage.setTitle("新增会员界面");
        addMemberStage.setResizable(false);
        addMemberStage.setScene(scene);
        addMemberStage.show();
    }

}