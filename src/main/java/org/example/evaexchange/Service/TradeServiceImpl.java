package org.example.evaexchange.Service;

import org.example.evaexchange.Entity.*;
import org.example.evaexchange.Repository.PortfolioRepo;
import org.example.evaexchange.Repository.PortfolioShareRepo;
import org.example.evaexchange.Repository.ShareRepo;
import org.example.evaexchange.Repository.TradeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TradeServiceImpl implements TradeService {

    @Autowired
    private TradeRepo tradeRepo;

    @Autowired
    private PortfolioRepo portfolioRepo;

    @Autowired
    private ShareRepo shareRepo;

    @Autowired
    private PortfolioShareRepo portfolioShareRepo;

    @Override
    public List<Trade> getAllTrades() {
        return tradeRepo.findAll();
    }

    @Override
    public Trade getTradeById(Long id) {
        return tradeRepo.findById(id).orElse(null);
    }

    @Override
    public Trade createTrade(Trade trade) {
        return tradeRepo.save(trade);
    }

    @Override
    public Trade updateTrade(Long id, Trade trade) {
        if (tradeRepo.existsById(id)) {
            trade.setId(id);
            return tradeRepo.save(trade);
        }
        return null;
    }

    @Override
    public void deleteTrade(Long id) {
        tradeRepo.deleteById(id);
    }

    @Override
    @Transactional
    public Trade buy(Trade trade) {
        Portfolio portfolio = portfolioRepo.findById(trade.getPortfolio().getId())
                .orElseThrow(() -> new IllegalArgumentException("Portfolio not found for id: " + trade.getPortfolio().getId()));
        Share share = shareRepo.findById(trade.getShare().getId())
                .orElseThrow(() -> new IllegalArgumentException("Share not found for id: " + trade.getShare().getId()));

        trade.setPortfolio(portfolio);
        trade.setShare(share);
        trade.setType(TradeType.BUY);
        trade.setPrice(share.getPrice());


        Trade savedTrade = tradeRepo.save(trade);
        PortfolioShare portfolioShare = portfolioShareRepo.findByPortfolioIdAndShareId(portfolio.getId(), share.getId());

        if (portfolioShare == null) {
            portfolioShare = new PortfolioShare();
            portfolioShare.setId(new PortfolioShareId(portfolio.getId(), share.getId()));
            portfolioShare.setPortfolio(portfolio);
            portfolioShare.setShare(share);
            portfolioShare.setQuantity(0);
        }
        // TO DO : Calculate the quantity of shares  according to the trade value
        //TO DO : Use the MAPPER AND DTOS
        portfolioShare.setQuantity(portfolioShare.getQuantity() + trade.getQuantity());
        portfolioShareRepo.save(portfolioShare);

        return savedTrade;
    }

    @Override
    @Transactional
    public Trade sell(Trade trade) {
        Portfolio portfolio = portfolioRepo.findById(trade.getPortfolio().getId())
                .orElseThrow(() -> new IllegalArgumentException("Portfolio not found for id: " + trade.getPortfolio().getId()));
        Share share = shareRepo.findById(trade.getShare().getId())
                .orElseThrow(() -> new IllegalArgumentException("Share not found for id: " + trade.getShare().getId()));

        PortfolioShare portfolioShare = portfolioShareRepo.findByPortfolioIdAndShareId(portfolio.getId(), share.getId());
        if (portfolioShare == null || portfolioShare.getQuantity() < trade.getQuantity()) {
            throw new IllegalArgumentException("Insufficient shares to sell");
        }

        trade.setPortfolio(portfolio);
        trade.setShare(share);
        trade.setType(TradeType.SELL);
        trade.setPrice(share.getPrice());

        Trade savedTrade = tradeRepo.save(trade);

        portfolioShare.setQuantity(portfolioShare.getQuantity() - trade.getQuantity());
        portfolioShareRepo.save(portfolioShare);

        return savedTrade;
    }
}