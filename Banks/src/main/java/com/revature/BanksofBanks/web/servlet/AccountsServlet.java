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

import static com.revature.BanksofBanks.web.servlets.Authable.checkAuth;


public class AccountsServlet extends HttpServlet implements Authable {

    private final PokemonServices pokemonServices;
    private final ObjectMapper mapper;

    public PokemonServlet(PokemonServices pokemonServices, ObjectMapper mapper) {
        this.pokemonServices = pokemonServices;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("id") != null){
            Pokemon pokemon = pokemonServices.readById(req.getParameter("id"));
            String payload = mapper.writeValueAsString(pokemon);
            resp.getWriter().write(payload);
            return;
        }

        List<Pokemon> pokemons = pokemonServices.readAll();
        String payload = mapper.writeValueAsString(pokemons);

        resp.getWriter().write(payload);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(!checkAuth(req, resp)) return;
        // TODO: Let's create a pokemon
        Pokemon newPokemon = mapper.readValue(req.getInputStream(), Pokemon.class); // from JSON to Java Object (Pokemon)
        Pokemon persistedPokemon = pokemonServices.create(newPokemon);

        String payload = mapper.writeValueAsString(persistedPokemon); // Mapping from Java Object (Pokemon) to JSON

        resp.getWriter().write("Persisted the provided pokemon as show below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

}
