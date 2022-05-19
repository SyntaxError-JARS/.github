package com.revature.BanksofBanks.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.BanksofBanks.daos.AccountsDao;
import com.revature.BanksofBanks.daos.OwnersDao;
import com.revature.BanksofBanks.models.Accounts;
import com.revature.BanksofBanks.models.Owners;
import com.revature.BanksofBanks.services.AccountsServices;
import com.revature.BanksofBanks.services.OwnersServices;
import com.revature.BanksofBanks.web.servlet.AccountsServlet;
import com.revature.BanksofBanks.web.servlet.AuthServlet;
import com.revature.BanksofBanks.web.servlet.OwnersServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class List implements ServletContextListener {

    private com.revature.BanksofBanks.services.AccountsServices AccountsServices;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Make our single ObjectMapper instance
        ObjectMapper mapper = new ObjectMapper();

        // Instantiate all Daos first
        OwnersDao ownersDao = new OwnersDao();
        AccountsDao accountsDao = new AccountsDao() {

            @Override
            public com.revature.BanksofBanks.models.Owners create(Owners newOwners) {
                return null;
            }

            @Override
            public Accounts findById() {
                return null;
            }


            @Override
            public Owners findByEmail() {
                return null;
            }

            @Override
            public Owners findByEmail(String email) {
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

        // Instantiate and initialize the services with their dao dependency
        OwnersServices OwnersServices = new OwnersServices(ownersDao);
        AccountsServices accountServices = new AccountsServices(accountsDao);


        AuthServlet authServlet = new AuthServlet(OwnersServices, mapper);
        OwnersServlet ownersServlet = new OwnersServlet(OwnersServices, mapper);
        AccountsServlet accountsServlet = new AccountsServlet(AccountsServices, mapper);

        ServletContext context = sce.getServletContext();
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
        context.addServlet("OwnersServlet", ownersServlet).addMapping("/owners/*");
        context.addServlet("AccountsServlet", accountsServlet).addMapping("/accounts/*");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
