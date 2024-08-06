package org.example.evaexchange.Service;

import jakarta.annotation.PostConstruct;
import org.example.evaexchange.Entity.*;
import org.example.evaexchange.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class DataGeneratorService {

    @Autowired
    private UserService userService;

    @Autowired
    private ShareService shareService;

    @Autowired
    private PortfolioService portfolioService;

    @PostConstruct
    public void generateData() {
        generateUsers();
        generateShares();
        generatePortfolios();
    }

    private void generateUsers() {
        for (int i = 1; i <= 5; i++) {
            User user = new User();
            user.setName("User" + i);
            user.setEmail("user" + i + "@example.com");
            userService.createUser(user);

        }
    }

    private void generateShares() {
        Random random = new Random();
        for (int i = 1; i <= 5; i++) {
            Share share = new Share();
            share.setSymbol("" + String.valueOf((char) (65 + i)));
            share.setPrice(random.nextDouble(900));
            shareService.createShare(share);
        }
    }

    private void generatePortfolios() {
        List<User> users = userService.getAllUsers();
        List<Portfolio> portfolios = new ArrayList<>();
        for (User user : users) {
            Portfolio portfolio = new Portfolio();
            portfolio.setUser(user);
            portfolio.setName("Portfolio of " + user.getName());
            portfolioService.createPortfolio(portfolio);
        }
    }
}