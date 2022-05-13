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
    private final AbilitiesServices abilitiesServices;
    private final ObjectMapper mapper;

    public AbilitiesServlet(AbilitiesServices abilitiesServices, ObjectMapper mapper) {
        this.abilitiesServices = abilitiesServices;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("abilityName") != null){
            Abilities ability = abilitiesServices.readById(req.getParameter("abilityName"));
            String payload = mapper.writeValueAsString(ability);
            resp.getWriter().write(payload);
            return;
        }

        List<Abilities> abilities = abilitiesServices.readAll();
        String payload = mapper.writeValueAsString(abilities);

        resp.getWriter().write(payload);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Abilities abilities = mapper.readValue(req.getInputStream(), Abilities.class);
        Abilities ability = abilitiesServices.create(abilities);

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
