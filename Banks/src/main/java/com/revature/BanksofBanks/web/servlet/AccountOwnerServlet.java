package com.revature.BanksofBanks.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.BanksofBanks.exceptions.ResourcePersistanceException;
import com.revature.BanksofBanks.models.AccountOwner;
import com.revature.BanksofBanks.services.AccountOwnerServices;
import com.revature.BanksofBanks.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.revature.BanksofBanks.web.servlets.Authable.checkAuth;

// @WebServlet("/trainers")



public class AccountOwnerServlet extends HttpServlet implements Authable {
    private final TrainerServices trainerServices;
    private final ObjectMapper mapper;
    private final Logger logger = Logger.getLogger();

    public TrainerServlet(TrainerServices trainerServices, ObjectMapper mapper) {
        this.trainerServices = trainerServices;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(!checkAuth(req, resp)) return;
        // The below code allows to split information from the endpoint after the /trainers/. Reminder the first element is empty because it takes the value from before the first /
//        String pathInfo = req.getPathInfo();
//        String[] pathParts = pathInfo.split("/");
//        System.out.println(pathParts[0] + pathParts[1] + pathParts[2]);


        // Handling the query params in the /trainers?id=x&email=y
        if(req.getParameter("id") != null && req.getParameter("email") != null){
            resp.getWriter().write("Hey you have the follow id and email " + req.getParameter("id") + " " + req.getParameter("email") );
            return;
        }

        // Handling the query params in the endpoint /trainers?id=x
        if(req.getParameter("id") != null){
            Trainer trainer;
            try {
                trainer = trainerServices.readById(req.getParameter("id")); // EVERY PARAMETER RETURN FROM A URL IS A STRING
            } catch (ResourcePersistanceException e){
                logger.warn(e.getMessage());
                resp.setStatus(404);
                resp.getWriter().write(e.getMessage());
                return;
            }
            String payload = mapper.writeValueAsString(trainer);
            resp.getWriter().write(payload);
            return;
        }

        List<Trainer> trainers = trainerServices.readAll();
        String payload = mapper.writeValueAsString(trainers);

        resp.getWriter().write(payload);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
