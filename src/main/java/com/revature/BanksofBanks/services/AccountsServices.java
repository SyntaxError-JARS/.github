package com.revature.BanksofBanks.services;

import com.revature.BanksofBanks.daos.AccountsDao;
import com.revature.BanksofBanks.models.Accounts;

import java.util.List;

public class AccountsServices {
    public AccountsServices(AccountsDao accountsDao) {

    }

    public Accounts readById(String accountId) {

        return null;
    }

    public List<Accounts> readAll() {
        return null;
    }

    public Accounts create(Accounts newAccounts) {
        return newAccounts;
    }
}
