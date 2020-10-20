package com.example.fitzky2.Discount;

public class Discount {
    private String dID;
    private String dName;
    private String type;
    private Integer dPercentage;
    private Integer dPrice;

    public Discount() {
    }

    public Discount(String ddid, String ddname, String type, int ddper, int ddprice) {
        this.dID = ddid;
        this.dName = ddname;
        this.type = type;
        this.dPercentage = ddper;
        this.dPrice = ddprice;
    }


    public String getdID() {
        return dID;
    }

    public void setdID(String dID) {
        this.dID = dID;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getdPercentage() {
        return dPercentage;
    }

    public void setdPercentage(Integer dPercentage) {
        this.dPercentage = dPercentage;
    }

    public Integer getdPrice() {
        return dPrice;
    }

    public void setdPrice(Integer dPrice) {
        this.dPrice = dPrice;
    }


}
