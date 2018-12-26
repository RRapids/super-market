package com.soft1841.cn.entity;

import java.util.Date;

public class Ticket {
    private Long id;
    private String sellerID;
    private String memberID;
    private Date collectDate;
    private String total;

    public Ticket(Long id, String sellerID, String memberID, Date collectDate, String total) {
        this.id = id;
        this.sellerID = sellerID;
        this.memberID = memberID;
        this.collectDate = collectDate;
        this.total = total;
    }

    public Ticket() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public Date getCollectDate() {
        return collectDate;
    }

    public void setCollectDate(Date collectDate) {
        this.collectDate = collectDate;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
