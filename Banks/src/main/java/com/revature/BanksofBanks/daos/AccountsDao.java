package com.revature.BanksofBanks.daos;


import com.revature.BanksofBanks.exceptions.ResourcePersistanceException;
import com.revature.BanksofBanks.models.Accounts;
import com.revature.BanksofBanks.util.ConnectionFactory;
import com.revature.BanksofBanks.util.logging.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

    public class AccountsDao implements Crudable<Accounts> {

        private Logger logger = Logger.getLogger();

        @Override
        public Accounts create(Accounts newAccounts) {
            try(Connection conn = ConnectionFactory.getInstance().getConnection();) {

                String sql = "insert into accounts values (default, ?, ?, ?, ?, ?, ?)";

                PreparedStatement ps = conn.prepareStatement(sql);

                // 1-indexed, so first ? starts are 1
                ps.setString(1, newAccounts.getPokemonName());
                ps.setInt(2, newAccounts.getHp());
                ps.setInt(3, newAccounts.getAtk());
                ps.setInt(4, newAccounts.getElementType());
                ps.setString(5, newAccounts.getAbility1());
                ps.setString(6, newAccounts.getAbility2());

                int checkInsert = ps.executeUpdate();

                if(checkInsert == 0){
                    throw new ResourcePersistanceException("AccountsServlet was not entered into database due to some issue.");
                }

            } catch (SQLException e){
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

                ResultSet rs =s.executeQuery(sql);

                while (rs.next()) {
                    Accounts account = new Accounts();

                    accounts.setAccountsName(rs.getString("pokemon_name"));
                    accounts.setHp(rs.getInt("hp"));
                    accounts.setAtk(rs.getInt("atk"));
                    accounts.setElementType(rs.getInt("element_type"));
                    accounts.setAbility1(rs.getString("ability1"));
                    accounts.setAbility2(rs.getString("ability2"));

                    accounts.add(pokemon);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }

            return accounts;
        }

        @Override
        public Accounts findById(String id) {
            try (Connection conn = ConnectionFactory.getInstance().getConnection();) { // try with resources, because Connection extends the interface Auto-Closeable

                String sql = "select * from accounts where id = ?";
                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setInt(1, Integer.parseInt(id));

                ResultSet rs = ps.executeQuery();

                if(!rs.next()){
                    throw new ResourcePersistanceException("Pokemon was not found in the database, please check ID entered was correct.");
                }

                Accounts pokemon = new Pokemon();
                accounts.setPokemonName(rs.getString("pokemon_name"));
                accounts.setHp(rs.getInt("hp"));
                accounts.setAtk(rs.getInt("atk"));
                accounts.setElementType(rs.getInt("element_type"));
                accounts.setAbility1(rs.getString("ability1"));
                accounts.setAbility2(rs.getString("ability2"));

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
        public boolean delete(String id) {
            return false;
        }
}
