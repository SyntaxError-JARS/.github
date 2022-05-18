package com.revature.BanksofBanks.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.BanksofBanks.exceptions.ResourcePersistanceException;
import com.revature.BanksofBanks.services.OwnersServices;
import com.revature.BanksofBanks.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.revature.BanksofBanks.web.servlet.Authable.checkAuth;

// @WebServlet("/trainers")



public class OwnersServlet extends HttpServlet implements Authable {
    private final OwnersServices OwnersServices;
    private final ObjectMapper mapper;
    private final Logger logger = Logger.getLogger();

    public OwnersServlet(OwnersServices ownersServices, ObjectMapper mapper) {
        this.OwnersServices = ownersServices;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(!Authable.checkAuth(req, resp)) return;
        // The below code allows to split information from the endpoint after the /trainers/. Reminder the first element is empty because it takes the value from before the first /
//        String pathInfo = req.getPathInfo();
//        String[] pathParts = pathInfo.split("/");
//        System.out.println(pathParts[0] + pathParts[1] + pathParts[2]);


        // Handling the query params in the /accountowner?id=x&email=y
        if(req.getParameter("email") != null && req.getParameter("last4Social") != null){
            resp.getWriter().write("Hey you have the follow owner email and last 4 of social " + req.getParameter("last4Social") + " " + req.getParameter("email") );
            return;
        }

        // Handling the query params in the endpoint /acountowner?id=x
        if(req.getParameter("email") != null){
            Owners owners;
            try {
                owners = ownersServices.readByEmail(req.getParameter("email")); // EVERY PARAMETER RETURN FROM A URL IS A STRING
            } catch (ResourcePersistanceException e){
                logger.warn(e.getMessage());
                resp.setStatus(404);
                resp.getWriter().write(e.getMessage());
                return;
            }
            String payload = mapper.writeValueAsString(owners);
            resp.getWriter().write(payload);
            return;
        }

        List<owners> owners = ownersServices.readAll();
        String payload = mapper.writeValueAsString(owners);

        resp.getWriter().write(payload);
    }

    @Override
    public void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
