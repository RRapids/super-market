package com.soft1841.cn.controller;

import com.soft1841.cn.entity.Member;
import com.soft1841.cn.service.MemberService;
import com.soft1841.cn.utils.ServiceFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddMemberController {

    @FXML
    private TextField memberName, memberAddress, memberPhone;

    private ObservableList<Member> memberData = FXCollections.observableArrayList();

    private MemberService memberService = ServiceFactory.getMemberServiceInstance();

    public ObservableList<Member> getMemberData() {
        return memberData;
    }

    public void setMemberData(ObservableList<Member> memberData) {
        this.memberData = memberData;
    }

    public void addMember() {
        String name = memberName.getText();
        String address = memberAddress.getText();
        String phone = memberPhone.getText();
        Member member = new Member();
        member.setName(name);
        member.setAddress(address);
        member.setPhone(phone);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示信息");

        if (name.isEmpty() || address.isEmpty() || phone.isEmpty()) {
            alert.setHeaderText("新增会员失败！");
            alert.showAndWait();
        } else {

            long id = memberService.insertMember(member);
            member.setId(id);
            this.getMemberData().add(member);

            alert.setHeaderText("新增会员成功！");
            alert.showAndWait();
            Stage stage = (Stage) memberName.getScene().getWindow();
            stage.close();
        }
    }
}
