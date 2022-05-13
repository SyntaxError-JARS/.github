package com.revature.BanksofBanks.util;

pimport com.revature.BanksofBanks.daos.TrainerDao;
import com.revature.BanksofBanks.menus.RegisterMenu;
import com.revature.BanksofBanks.menus.WelcomeMenu;
import com.revature.BanksofBanks.services.AccountOwnerServices;
import com.revature.BanksofBanks.util.logging.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {

    private static boolean isRunning;
    private WelcomeMenu welcomeMenu;
    private RegisterMenu registerMenu;
    private final Logger logger;
    // once we add register we will need private final MenuRouter router;

    public AppState() {

        logger = Logger.getLogger(true);

        logger.log("2. Generating instance of AppState.");
        isRunning = true;
        BufferedReader terminalReader = new BufferedReader(new InputStreamReader(System.in));
        AccountOwerServices accountownerServices = new TrainerServices(new TrainerDao());

        // TODO: Why are we doing all of this!?
        this.welcomeMenu = new WelcomeMenu(terminalReader, trainerServices, logger);
        this.registerMenu = new RegisterMenu(terminalReader);
    }

    public void startup(){
        try {
            while(isRunning) {
                logger.info("Application successfully started");
                // registerMenu.render();
                welcomeMenu.render(); // comment in and out based on what you want to use
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void shutdown(){
        isRunning = false;
    }

}