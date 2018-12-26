package com.soft1841.cn.entity;

import java.util.Date;

public class Ticket {
    private long id;
    private long sellerID;
    private long memberID;
    private Date collectDate;
    private String total;

    public Ticket(long id, long sellerID, long memberID, Date collectDate, String total) {
        this.id = id;
        this.sellerID = sellerID;
        this.memberID = memberID;
        this.collectDate = collectDate;
        this.total = total;
    }

    public Ticket() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSellerID() {
        return sellerID;
    }

    public void setSellerID(long sellerID) {
        this.sellerID = sellerID;
    }

    public long getMemberID() {
        return memberID;
    }

    public void setMemberID(long memberID) {
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
