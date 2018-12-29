package com.soft1841.cn.controller;

import com.soft1841.cn.entity.Member;
import com.soft1841.cn.service.MemberService;
import com.soft1841.cn.utils.ServiceFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MemberController implements Initializable {
    @FXML
    private FlowPane memberPane;

    private MemberService memberService = ServiceFactory.getMemberServiceInstance();

    private List<Member> memberList = new ArrayList<>();

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
        }

    }
}
