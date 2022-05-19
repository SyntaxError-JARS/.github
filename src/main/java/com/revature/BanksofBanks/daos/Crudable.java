package com.revature.BanksofBanks.daos;

import com.revature.BanksofBanks.models.Accounts;
import com.revature.BanksofBanks.models.Owners;

import java.io.IOException;

// public interface Crudable {
    // This is another form of abstraction

    public interface Crudable<T> {

        // public final int age = 16; we call a constant variable because by default it's final and cannot changed

        abstract Owners create(Owners newObject);

        // Create


        Accounts create(Accounts newAccounts);

        // Read
        Owners[] findAll() throws IOException;
        T findById();

        Owners findByEmail();

        Owners findByEmail(String email);

        T findById(int accountId);

        boolean update(Accounts updatedObj);

        boolean update(Owners updatedObj);



        // Update
        public boolean update(T updatedObj);

        boolean delete(String email);

        //Delete
        boolean delete(int id);

    }


