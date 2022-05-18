package com.revature.BanksofBanks.daos;

import com.revature.BanksofBanks.exceptions.models.Owners;

import java.io.IOException;

// public interface Crudable {
    // This is another form of abstraction

    public interface Crudable<T> {

        // public final int age = 16; we call a constant variable because by default it's final and cannot changed

        // Create
        T create(T newObject);

        // Read
        T[] findAll() throws IOException;
        T findById();

        Owners findByEmail();

        // Update
        public boolean update(T updatedObj);

        //Delete
        boolean delete(int id);

    }


