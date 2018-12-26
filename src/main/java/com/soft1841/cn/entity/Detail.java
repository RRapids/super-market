package com.soft1841.cn.entity;

public class Detail {
    private long id;
    private long ticketID;
    private String barCode;
    private String number;

    public Detail(long id, long ticketID, String barCode, String number) {
        this.id = id;
        this.ticketID = ticketID;
        this.barCode = barCode;
        this.number = number;
    }

    public Detail() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTicketID() {
        return ticketID;
    }

    public void setTicketID(long ticketID) {
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

    @Override
    public String toString() {
        return "Detail{" +
                "id=" + id +
                ", ticketID=" + ticketID +
                ", barCode='" + barCode + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
