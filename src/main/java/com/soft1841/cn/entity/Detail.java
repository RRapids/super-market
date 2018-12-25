package com.soft1841.cn.entity;

public class Detail {
    private Long id;
    private Long ticketID;
    private String barCode;
    private String number;

    public Detail(Long id, Long ticketID, String barCode, String number) {
        this.id = id;
        this.ticketID = ticketID;
        this.barCode = barCode;
        this.number = number;
    }

    public Detail() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTicketID() {
        return ticketID;
    }

    public void setTicketID(Long ticketID) {
        this.ticketID = ticketID;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
