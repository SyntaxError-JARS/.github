package com.revature.BanksofBanks.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.BanksofBanks.models.Transactions;
import com.revature.BanksofBanks.services.TransactionsServices;
import com.revature.BanksofBanks.util.collections.serializers.LinkedListSerializerTransactions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
public class TransactionsServlet extends HttpServlet  {
    private final TransactionsServices transactionsServices;
    private final ObjectMapper mapper;

    public TransactionsServlet(TransactionsServices transactionsServices, ObjectMapper mapper) {
        this.transactionsServices = transactionsServices;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("transactionsID") != null){
            Transactions transactions = transactionsServices.readById(req.getParameter("transactionsID"));
            String payload = mapper.writeValueAsString(transactions);
            resp.getWriter().write(payload);
            return;
        }

        List<Transactions> transactions = transactionsServices.readAll();
        String payload = mapper.writeValueAsString(abilities);

        resp.getWriter().write(payload);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Transactions transactions = mapper.readValue(req.getInputStream(), Transactions.class);
        Transactions transactions = transactionsServices.create(transactions);

        String payload = mapper.writeValueAsString(ability);

        resp.getWriter().write(payload);
        resp.setStatus(201);

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    protected boolean checkAuth(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession httpSession = req.getSession();
        if(httpSession.getAttribute("authTrainer") == null){
            resp.getWriter().write("Unauthorized request - not log in as registered user");
            resp.setStatus(401); // Unauthorized
            return false;
        }
        return true;
    }
}
