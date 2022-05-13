package com.revature.BanksofBanks.daos;

import java.io.IOException;
import java.sql.*;

public class AccountOwnerDao implements Crudable<AccountOwner>{
    @Override
    public AccountOwner create(AccountOwner newAccountOwner) {
        System.out.println("Here is the newAccountOwner as it enters our DAO layer: "+ newAccountOwner); // What happens here? Java knows to invoke the toString() method when printing the object to the terminal

        try(Connection conn = ConnectionFactory.getInstance().getConnection();) {

            // NEVER EVER EVER EVER EVER concatenate or directly use these strings inside of the sql statement
            // String sql = "insert into accountowner value (" + newAccountOwner.getFname() + "," + newAccountOwner.getLname();

            // The commented out sql String is using default for auto-generating the ID ifyou used serial
            // String sql = "insert into accountowner values (default, ?, ?, ?, ?, ?)"; // incomplete sql statement, with default if not specifiying columns
            String sql = "insert into accountowner (fname, lname, email, password, dob) values (?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            System.out.println(newAccountOwner.getFname());
            System.out.println(newAccountOwner.getLname());

            // 1-indexed, so first ? starts are 1
            ps.setString(1, newAccountOwner.getFname());
            ps.setString(2, newAccountOwner.getLname());
            ps.setString(3, newAccountOwner.getEmail());
            ps.setString(4, newAccountOwner.getPassword());
            ps.setString(5, newAccountOwner.getDob());

            int checkInsert = ps.executeUpdate();

            if(checkInsert == 0){
                throw new RuntimeException();
            }

        } catch (SQLException | RuntimeException e){
            e.printStackTrace();
            return null;
        }
        return newAccountOwner;
    }

    @Override
    public AccountOwner[] findAll() throws IOException {

        // making an array of Account Owner classes, and seetting it to a max size of 10
        AccountOwner[] accountowner = new AccountOwner[10];
        // declaring index variable as an int and intiliazation with the value of 0
        int index = 0; // we want to keep track of where we are placing each trainer from the file into the array

        // TODO: we trying something here and passing an argumetn???
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) { // try with resources, because Connection extends the interface Auto-Closeable

            String sql = "select * from accountowner";
            Statement s = conn.createStatement();

            // conn.createStatement().executeQuery("select * from trainer"); fine but generally not used
            // TODO: Hey why are we using the sql variable string here?
            ResultSet rs =s.executeQuery(sql);

            while (rs.next()) { // the last line of the file is null
                AccountOwner accountowner = new AccountOwner();

                accountowner.setFname(rs.getString("fname")); // this column label MUST MATCH THE DB
                accountowner.setLname(rs.getString("lname"));
                accountowner.setDob(rs.getString("dob"));
                accountowner.setPassword(rs.getString("password"));
                accountowner.setEmail(rs.getString("email"));

                System.out.println("Inserted account owner into index" + index);
                accountowner[index] = accountowner;
                index++; // increment the index by 1, must occur after the trainer[index] re-assignment
                System.out.println("Going to the next line for our following index.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }



        System.out.println("Returning account owner information to user.");
        return accountowner;
    }

    @Override
    public AccountOwner findById(String id) {


        try(Connection conn = ConnectionFactory.getInstance().getConnection();){

            String sql = "select * from accountowner where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(id)); // Wrapper class example

            ResultSet rs = ps.executeQuery(); // remember dql, bc selects are the keywords

            AccountOwner accountowner = new AccountOwner();

            trainer.setFname(rs.getString("fname")); // this column lable MUST MATCH THE DB
            trainer.setLname(rs.getString("lname"));
            trainer.setDob(rs.getString("dob"));
            trainer.setPassword(rs.getString("password"));
            trainer.setEmail(rs.getString("email"));

            return trainer;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean update(AccountOwner updatedObj) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    public void checkEmail(String email) {
        String sql = "select email from account owner where email = " + email; // issues with SQL injection
    }
}
