package com.revature.BanksofBanks.daos;

import com.revature.BanksofBanks.exceptions.models.Owners;
import com.revature.BanksofBanks.util.ConnectionFactory;

import java.io.IOException;
import java.sql.*;

import static com.revature.BanksofBanks.util.ConnectionFactory.connectionFactory;

public class OwnersDao implements Crudable<Owners> {
    @Override
    public Owners create(Owners newOwners) {
        System.out.println("Here is the newOwners as it enters our DAO layer: " + newOwners); // What happens here? Java knows to invoke the toString() method when printing the object to the terminal

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            // NEVER EVER EVER EVER EVER concatenate or directly use these strings inside of the sql statement
            // String sql = "insert into owners value (" + newAccountOwner.getFname() + "," + newAccountOwner.getLname();

            // The commented out sql String is using default for auto-generating the ID ifyou used serial
            // String sql = "insert into accountowner values (default, ?, ?, ?, ?, ?)"; // incomplete sql statement, with default if not specifiying columns
            String sql = "insert into owners (fname, lname, age, email, last4_social) values (getFname, getLname, getAge, getEmail, getlast4Social)";

            PreparedStatement ps = conn.prepareStatement(sql);

            System.out.println(newOwners.getFname());
            System.out.println(newOwners.getLname());

            // 1-indexed, so first ? starts are 1
            ps.setString(1, newOwners.getFname());
            ps.setString(2, newOwners.getLname());
            ps.setString(3, newOwners.getEmail());
            ps.setInt(4, newOwners.getAge());
            ps.setInt(5, newOwners.getlast4Social());

            int checkInsert = ps.executeUpdate();

            if (checkInsert == 0) {
                throw new RuntimeException();
            }

        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
            return null;
        }
        return newOwners;
    }

    @Override
    public Owners[] findAll() throws IOException {

        // making an array of Account Owner classes, and seetting it to a max size of 10
        // declaring index variable as an int and intiliazation with the value of 0
        int index = 0; // we want to keep track of where we are placing each trainer from the file into the array

        // TODO: we trying something here and passing an argumetn???
        Owners owners = null;
        try (Connection conn = connectionFactory.getInstance().getConnection();) { // try with resources, because Connection extends the interface Auto-Closeable

            String sql = "select * from owners";
            Statement s = conn.createStatement();

            // conn.createStatement().executeQuery("select * from trainer"); fine but generally not used
            // TODO: Hey why are we using the sql variable string here?
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) { // the last line of the file is null
                owners = new Owners();

                // this column label MUST MATCH THE DB
                owners.setFname(rs.getString("fname"));
                owners.setLname(rs.getString("lname"));
                owners.setEmail(rs.getString("email"));
                owners.setAge(rs.getInt("age"));
                owners.setlast4Social(rs.getInt("last4Social"));

                System.out.println("Inserted owners into index" + index);
                Owners[] Owners = new Owners[0];
                Owners[index] = owners;
                index++; // increment the index by 1, must occur after the trainer[index] re-assignment
                System.out.println("Going to the next line for our following index.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


        System.out.println("Returning owners information to user.");
        return new Owners[]{owners};
    }

    @Override
    public Owners findById() {
        return null;
    }

    @Override
    public Owners findByEmail() {
        return null;
    }

    @Override
    public Owners findByEmail(String email) {


        try (Connection conn = connectionFactory.getInstance().getConnection();) {

            String sql = "select * from owners where email = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(3, String.valueOf(email)); // Wrapper class example

            ResultSet rs = ps.executeQuery(); // remember dql, bc selects are the keywords

            Owners owners = new Owners();

            // this column lable MUST MATCH THE DB
            owners.setFname(rs.getString("fname"));
            owners.setLname(rs.getString("lname"));
            owners.setEmail(rs.getString("email"));
            owners.setAge(rs.getInt("age"));
            owners.setlast4Social(rs.getInt("last4Social"));

            return owners;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean update(Owners updatedObj) {
        return false;
    }

    @Override
    public boolean delete(String email) {
        return false;
    }

    @Override
    public boolean delete(int accountId) {
        return false;
    }

    public void checklast4Social(Integer last4Social) {
        String sql = "select email from owners where last4Social = " + findByEmail(); // issues with SQL injection
    }

    public void checkEmail(
            String email
    ) {

    }
}