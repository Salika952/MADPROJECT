package com.example.fitzky2.paymnet;

public class Creditcarddetails {
    private String id;
    private String cardno;
    private String expirdate;
    private String ccv;


    public Creditcarddetails(String id, String cardno, String expirdate, String ccv) {
        this.id = id;
        this.cardno = cardno;
        this.expirdate = expirdate;
        this.ccv = ccv;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getExpirdate() {
        return expirdate;
    }

    public void setExpirdate(String expirdate) {
        this.expirdate = expirdate;
    }

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }



}
