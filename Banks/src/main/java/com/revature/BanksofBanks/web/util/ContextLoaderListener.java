package com.revature.BanksofBanks.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.BanksofBanks.daos.TransactionsDao;
import com.revature.BanksofBanks.daos.ElementTypeDao;
import com.revature.BanksofBanks.daos.AccountsDao;
import com.revature.BanksofBanks.daos.AccountOwnerDao;
import com.revature.BanksofBanks.models.Transactions;
import com.revature.BanksofBanks.models.ElementType;
import com.revature.BanksofBanks.models.Accounts;
import com.revature.BanksofBanks.services.TransactionsServices;
import com.revature.BanksofBanks.services.ElementTypeServices;
import com.revature.BanksofBanks.services.AccountsServices;
import com.revature.BanksofBanks.services.AccountOwnerServices;
import com.revature.BanksofBanks.web.servlets.*;

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
        AccoutOwnerDao accoutownerDao = new AccountOwnerDao();
        ElementTypeDao elementTypeDao = new ElementTypeDao();
        TransactionsDao transactionsDao = new TransactionsDao();
        AccountsDao pokemonDao = new AccountsDao();

        // Instantiate and initialize the services with their dao dependency
        AccountOwnerServices accountownerServices = new AccountOwnerServices(accountownerDao);
        ElementTypeServices elementTypeServices = new ElementTypeServices(elementTypeDao);
        TransactionsServices transactionsServices = new AbilitiesServices(elementTypeDao, transactionsDao);
        AccountsServices accountsServices = new AccountsServices(accountsDao,elementTypeDao,transactionsDao);


        AuthServlet authServlet = new AuthServlet(accountownerServices, mapper);
        AccountOwnerServlet accountownerServlet = new AccountOwnerServlet(accountownerServices, mapper);
        ElementTypeServlet elementTypeServlet = new ElementTypeServlet(elementTypeServices, mapper);
        TransactionsServlet transactionsServlet = new TransactionsServlet(transactionsServices, mapper);
        AccountsServlet accountsServlet = new AccountsServlet(accountsServices, mapper);

        ServletContext context = sce.getServletContext();
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
        context.addServlet("AccountOwnerServlet", accountownerServlet).addMapping("/accountowner/*");
        context.addServlet("ElementTypeServlet", elementTypeServlet).addMapping("/elementTypes/*");
        context.addServlet("TransactionsServlet", transactionsServlet).addMapping("/transactions/*");
        context.addServlet("AccountsServlet", accountsServlet).addMapping("/accounts/*");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
