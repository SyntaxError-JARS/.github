package com.revature.BanksofBanks.daos;


import com.revature.BanksofBanks.exceptions.ResourcePersistanceException;
import com.revature.BanksofBanks.models.Accounts;
import com.revature.BanksofBanks.models.Owners;
import com.revature.BanksofBanks.util.ConnectionFactory;
import com.revature.BanksofBanks.util.logging.Logger;

import java.io.IOException;
import java.sql.*;

public abstract class AccountsDao implements Crudable<Accounts> {

    private Logger logger = Logger.getLogger();
    private Accounts[] Accounts;
    private com.revature.BanksofBanks.models.Owners[] Owners;
    private com.revature.BanksofBanks.models.Accounts accounts;

    @Override
    public Accounts create(Accounts newAccounts) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "insert into accounts values (getaccountId, getcurrentBalance, getEmail)";

            PreparedStatement ps = conn.prepareStatement(sql);

            // 1-indexed, so first ? starts are 1
            ps.setInt(1, newAccounts.getaccountId());
            ps.setInt(2, newAccounts.getcurrentBalance());
            ps.setString(3, newAccounts.getEmail());
            int checkInsert = ps.executeUpdate();
            
            accounts.setaccountId(getInteger("accountId"));
            accounts.setcurrentBalance(getInteger("currentBalance"));
            accounts.setEmail(getString("email"));

            if (checkInsert == 0) {
                throw new ResourcePersistanceException("AccountsServlet was not entered into database due to some issue.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return newAccounts;
    }

    private int getInteger(String currentBalance) {
        return 0;
    }

    private String getString(String email) {
        return email;
    }

    @Override
    public com.revature.BanksofBanks.models.Owners[] findAll() throws IOException {
        Accounts[] accounts = new Accounts[20];



        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "select * from accounts";
            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                AccountsDao accountsDao = new AccountsDao() {
                    @Override
                    public com.revature.BanksofBanks.models.Owners create(com.revature.BanksofBanks.models.Owners newOwners) {
                        return null;
                    }

                    @Override
                    public Accounts create(Accounts newAccounts) {
                        return null;
                    }

                    @Override
                    public Accounts findById() {
                        return null;
                    }

                    @Override
                    public com.revature.BanksofBanks.models.Owners findByEmail() {
                        return null;
                    }

                    @Override
                    public com.revature.BanksofBanks.models.Owners findByEmail(String email) {
                        return null;
                    }

                    @Override
                    public boolean update(Accounts updatedObj) {
                        return false;
                    }

                    @Override
                    public boolean update(com.revature.BanksofBanks.models.Owners updatedObj) {
                        return false;
                    }
                };



            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return new Owners[0];
    }

    @Override
    public Accounts findById(int accountId) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) { // try with resources, because Connection extends the interface Auto-Closeable

            String sql = "select * from accounts where accountId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(String.valueOf(accountId)));

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                throw new ResourcePersistanceException("Account was not found in the database, please check if account ID entered was correct.");
            }

            Accounts accounts = new Accounts();
            accounts.setaccountId(rs.getInt("accountId"));
            accounts.setcurrentBalance(rs.getInt("currentBalance"));
            accounts.setEmail(rs.getString("email"));

            return accounts;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public abstract Owners findByEmail();

    @Override
    public boolean update(Accounts updatedAccounts) {
        return false;
    }

    @Override
    public boolean delete(int accountId) {
        return false;
    }

    @Override
    public boolean delete(String email) {
        return false;
    }

}