package com.revature.BanksofBanks.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.revature.BanksofBanks.daos.AccountsDao;
import com.revature.BanksofBanks.daos.OwnersDao;
import com.revature.BanksofBanks.services.AccountsServices;
import com.revature.BanksofBanks.services.OwnersServices;
import com.revature.BanksofBanks.web.servlet.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Make our single ObjectMapper instance
        ObjectMapper mapper = new ObjectMapper();

        // Instantiate all Daos first
        OwnersDao ownersDao = new OwnersDao();
        AccountsDao accountsDao = new AccountsDao();

        // Instantiate and initialize the services with their dao dependency
        OwnersServices ownersServices = new OwnersServices(ownersDao);
        AccountsServices accountsServices = new AccountsServices(accountsDao);


        AuthServlet authServlet = new AuthServlet(ownersServices, mapper);
        OwnersServlet ownersServlet = new OwnersServlet(ownerServices, mapper);
        AccountsServlet accountsServlet = new AccountsServlet(accountsServices, mapper);

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
