package com.revature.BanksofBanks.services;

import java.io.IOException;

public class AccountOwnerServices {
    private AccountOwnerDao accountOwnerDao = new AccountOwnerDao();

    public void readAccountOwner(){
        System.out.println("Begin reading Account Owners in our file database.");
        AccountOwner[] accountowners;

        try {
            // TODO: What accountownerDao intellisense telling me?
            accountowners = accountOwnerDao.findAll();
            System.out.println("All account owners have been found here are the results: \n");
//            for (int i = 0; i < accountowners.length; i++) {
//                AccountOwner accountowner = accountowners[i];
//                if(accountowner != null) {
//                    System.out.println(trainer);
//                }
//            }

            // first time declaring variable you must defined data type (primitive or non-primitive)
            // account owner is now declared as a reference variables for an instance of an AccountOwner class
            // new keyword allows for the construction (or more technically the instantiation of an AccountOwner class with a No-Arg Construtor)
            // new AccountOwner() is instantiating a new accountowner object via the No-Args Constructor
            AccountOwner trainer = new AccountOwner();

            // TODO: Why is this declared as an Object and not a AccountOwner??
            Object accountowner1 = new AccountOwner("Charles", "Jester", "cj@mail.com", "p", "1111");

            AccountOwner iCanNameThisWhatEverTheHeckoIWant = new AccountOwner();
            System.out.println(iCanNameThisWhatEverTheHeckoIWant.getLname());

            System.out.println(" ----------THIS THINGGGGGGGG--------------- ");
            System.out.println(accountowner1.toString());
            System.out.println("-------------------------");
            // the (AccountOwner) is casting the Object accountowner1 in java's Heap Memory to view as an AccountOwner object instead
            System.out.println(((AccountOwner) accountowner1).getFname());

            // forEach
            for(Object t:accountowner ){
                if(t != null) {
                    System.out.println((AccountOwner) t); // account owner indicates a single element in the accountowners array
                }
            }

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }



    // TODO: Implement me to check that the email is not already in our database.
    // public this allows the use of this method anywhere there is a AccountOwnerServices object or within the class itself
    // boolean - it's a true or false value in java and it's specifying the return type
    // validateEmailNotUse() this is a method what we want to call to the DAO to check if the email is already in use
    // String email is the defined parameters for arguments required when invoking this method
    public boolean validateEmailNotUsed(String email){
        accountownerDao.checkEmail(email);
        return false;
    }

    public boolean registerAccountOwner(AccountOwner newAccountOwner){
        System.out.println("AccountOwner trying to be registered: " + newTAccountOwner);
        if(!validateAccountOwnerInput(newAccountOwner)){ // checking if false
            System.out.println("User was not validated");
            // TODO: throw - what's this keyword?
            throw new RuntimeException();
        }

        // TODO: Will implement with JDBC (connecting to our database)
        validateEmailNotUsed(newAccountOwner.getEmail());

        AccountOwner persistedAccountOwner = accountownerDao.create(newAccountOwner);

        if(persistedAccountOwner == null){
            throw new RuntimeException();
        }
        System.out.println("Account Owner has been persisted: " + newAccountOwner);
        return true;
    }

    private boolean validateAccountOwnerInput(AccountOwner newAccountOwner) {
        System.out.println("Validating Account Owner: " + newAccountOwner);
        if(newAccountOwner == null) return false;
        if(newAccountOwner.getFname() == null || newAccountOwner.getFname().trim().equals("")) return false;
        if(newAccountOwner.getLname() == null || newAccountOwner.getLname().trim().equals("")) return false;
        if(newAccountOwner.getEmail() == null || newAccountOwner.getEmail().trim().equals("")) return false;
        if(newAccountOwner.getPassword() == null || newAccountOwner.getPassword().trim().equals("")) return false;
        return newAccountOwner.getDob() != null || !newAccountOwner.getDob().trim().equals("");
    }
}
