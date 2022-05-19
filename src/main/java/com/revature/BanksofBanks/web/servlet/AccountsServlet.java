package com.revature.BanksofBanks.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.BanksofBanks.models.Accounts;
import com.revature.BanksofBanks.services.AccountsServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.revature.BanksofBanks.web.servlet.Authable.checkAuth;


public class AccountsServlet extends HttpServlet implements Authable {

    private final AccountsServices accountsServices;
    private final ObjectMapper mapper;

    public AccountsServlet(AccountsServices accountsServices, ObjectMapper mapper) {
        this.accountsServices = accountsServices;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("accountId") != null){
            Accounts accounts = accountsServices.readById(req.getParameter("accountId"));
            String payload = mapper.writeValueAsString(accounts);
            resp.getWriter().write(payload);
            return;
        }

        List<Accounts> accounts = accountsServices.readAll();
        String payload = mapper.writeValueAsString(accounts);

        resp.getWriter().write(payload);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(!checkAuth(req, resp)) return;
        // TODO: Let's create an account
        Accounts newAccounts = mapper.readValue(req.getInputStream(), Accounts.class); // from JSON to Java Object (Accounts)
        Accounts persistedAccounts = accountsServices.create(newAccounts);

        String payload = mapper.writeValueAsString(persistedAccounts); // Mapping from Java Object (Accounts) to JSON

        resp.getWriter().write("Persisted the provided account as show below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }

    @Override
    public void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //update

    }

    @Override
    public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //create

    }

}
