package com.revature.BanksofBanks.daos;


import com.revature.BanksofBanks.exceptions.ResourcePersistanceException;
import com.revature.BanksofBanks.exceptions.models.Accounts;
import com.revature.BanksofBanks.util.ConnectionFactory;
import com.revature.BanksofBanks.util.logging.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

    public abstract class AccountsDao implements Crudable<Accounts> {

        private Logger logger = Logger.getLogger();

        @Override
        public Accounts create(Accounts newAccounts) {
            try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

                String sql = "insert into accounts values (getaccountId, getcurrentBalance, getEmail)";

                PreparedStatement ps = conn.prepareStatement(sql);

                // 1-indexed, so first ? starts are 1
                ps.setInt(1, newAccounts.getaccountId());
                ps.setInt(2, newAccounts.getcurrentBalance());
                ps.setString(3, newAccounts.getEmail());

                int checkInsert = ps.executeUpdate();

                if (checkInsert == 0) {
                    throw new ResourcePersistanceException("AccountsServlet was not entered into database due to some issue.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
            return newAccounts;
        }

        @Override
        public List<Accounts> findAll() throws IOException {
            List<Accounts> accounts = new LinkedList<>();

            try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

                String sql = "select * from accounts";
                Statement s = conn.createStatement();

                ResultSet rs = s.executeQuery(sql);

                while (rs.next()) {
                    Accounts account = new Accounts();

                    accounts.setaccountId(rs.getInt("accountId"));
                    accounts.setcurrentBalance(rs.getInt("currentBalance"));
                    accounts.setEmail(rs.getString("email"));


                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }

            return accounts;
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