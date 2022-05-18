package com.revature.BanksofBanks.web.dto;

public class LoginCreds {

    private String email;
    private String last4Social;

    // JACKSON REQUIRES A NO ARG CONSTRUCTOR

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return last4Social;
    }

    public void setPassword(String password) {
        this.last4Social = last4Social;
    }
}
