package com.revature.BanksofBanks.services;

import com.revature.BanksofBanks.daos.OwnersDao;
import com.revature.BanksofBanks.exceptions.models.Owners;

import java.io.IOException;

public class OwnersServices {
    private com.revature.BanksofBanks.daos.OwnersDao OwnersDao = new OwnersDao();

    public OwnersServices(OwnersDao ownersDao) {

    }

    public void readOwner(){
        System.out.println("Begin reading Owners in our file database.");
        Owners[] owners;

        try {
            // TODO: What OwnersDao intellisense telling me?
            owners = OwnersDao.findAll();
            System.out.println("All owners have been found here are the results: \n");
//            for (int i = 0; i < owners.length; i++) {
//                Owners owners = owners[i];
//                if(owners != null) {
//                    System.out.println(owners);
//                }
//            }

            // first time declaring variable you must defined data type (primitive or non-primitive)
            // account owner is now declared as a reference variables for an instance of an AccountOwner class
            // new keyword allows for the construction (or more technically the instantiation of an AccountOwner class with a No-Arg Construtor)
            // new AccountOwner() is instantiating a new accountowner object via the No-Args Constructor
            //Owners owners = new Owners();

            // TODO: Why is this declared as an Object and not a AccountOwner??
            Object owner1 = new owners("Charles", "Jester", "cj@mail.com", "p", "1111");

            owners iCanNameThisWhatEverTheHeckoIWant = new owners();
            System.out.println(iCanNameThisWhatEverTheHeckoIWant.getLname());

            System.out.println(" ----------THIS THINGGGGGGGG--------------- ");
            System.out.println(owner1.toString());
            System.out.println("-------------------------");
            // the (AccountOwner) is casting the Object accountowner1 in java's Heap Memory to view as an AccountOwner object instead
            System.out.println(((Owners) owners).getFname());

            // forEach
            for(owners t:owners ){
                if(owners != null) {
                    System.out.println((Owners) owners); // account owner indicates a single element in the accountowners array
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
    public void validateEmailNotUsed(
            String email
    )

    {
        OwnersDao.checkEmail(email);
    }

    public boolean registerOwners(Owners newtOwners){
        System.out.println("Owners trying to be registered: " + newtOwners);
        if(!validateOwnersInput(newtOwners)){ // checking if false
            System.out.println("User was not validated");
            // TODO: throw - what's this keyword?
            throw new RuntimeException();
        }

        // TODO: Will implement with JDBC (connecting to our database)
        validateEmailNotUsed(newtOwners.getEmail());

        Owners persistedOwners = OwnersDao.create(newtOwners);

        if(persistedOwners == null){
            throw new RuntimeException();
        }
        System.out.println("Owners has been persisted: " + newtOwners);
        return true;
    }

    private boolean validateOwnersInput(Owners newtOwners) {
        System.out.println("Validating Owners: " + newtOwners);
        if(newtOwners == null) return false;
        if(newtOwners.getEmail() == null || newtOwners.getEmail().trim().equals("")) return false;
        if(newtOwners.getFname() == null || newtOwners.getFname().trim().equals("")) return false;
        if(newtOwners.getLname() == null || newtOwners.getLname().trim().equals("")) return false;
        if(newtOwners.getAge() == null) return false;
        if (newtOwners.getlast4Social() == null) {
            newtOwners.getlast4Social();
        }
        return true;
    }
}
