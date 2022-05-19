package com.revature.BanksofBanks.web.dto;

public class LoginCreds {

    private String email;
    private Integer last4Social;

    // JACKSON REQUIRES A NO ARG CONSTRUCTOR

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getLast4Social() { return last4Social; }


    public void setLast4Social() { this.last4Social = last4Social; }
    }