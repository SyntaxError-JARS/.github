package com.revature.BanksofBanks.models;

public class Accounts {
    private int accountId;
    private int currentBalance;
    private String email;


    // This is a No-Args Constructor. IT's the default, IFFFl there is no other constructor added.
    // Otherwise, the custom constructor overwrites

    public Accounts(int accountId, int currentBalance, String email){
        super();
        this.accountId = accountId; // shadowing, with provided arguments
        this.currentBalance = currentBalance;
        this.email = email;

    }

    public Accounts() {
        
    }

    public int getaccountId(){
        return accountId;
    }

    public void setPokemonName(int accountId) {
        this.accountId = accountId;
    }

    public int getcurrentBalance(){
        return this.currentBalance;
    }

    public void setcurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "Accounts{" +
                "accountId='" + accountId + '\'' +
                ", currentBalance='" + currentBalance + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public static void setaccountId(int accountId) {
    }
}



