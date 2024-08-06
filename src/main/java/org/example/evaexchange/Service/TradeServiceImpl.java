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
import java.util.Map;

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
    public Trade buy(Map<String, Object> tradeRequest) {
        Integer portfolioIdInt = (Integer) tradeRequest.get("portfolioId");
        Long portfolioId = portfolioIdInt.longValue();

        Portfolio portfolio = portfolioRepo.findById(portfolioId)
                .orElseThrow(() -> new IllegalArgumentException("Portfolio not found for id: " + tradeRequest.get("portfolioId")));
        Share share = shareRepo.findBySymbol( tradeRequest.get("shareSymbol").toString())
                .orElseThrow(() -> new IllegalArgumentException("Share not found for id: " + tradeRequest.get("shareSymbol").toString()));

        Trade trade = new Trade();
        trade.setPortfolio(portfolio);
        trade.setShare(share);
        trade.setType(TradeType.BUY);
        if (tradeRequest.get("price") != null && tradeRequest.get("price") != "") {
            Double price = Double.valueOf(tradeRequest.get("price").toString());
            Double sharePrice = share.getPrice();
            if (price < sharePrice) {
                throw new IllegalArgumentException("Price cannot be less than share price");
            }
            Double tradeQuantity = Math.floor(price / sharePrice);
            trade.setQuantity(tradeQuantity);
            trade.setPrice(tradeQuantity * sharePrice);
        }
        else if (tradeRequest.get("quantity") != null && tradeRequest.get("quantity") != "") {
            trade.setQuantity(Double.valueOf(tradeRequest.get("quantity").toString()));
            trade.setPrice(trade.getQuantity() * share.getPrice());
        } else {
            trade.setQuantity(0.0);
            trade.setPrice(0.0);
        }



        Trade savedTrade = tradeRepo.save(trade);
        PortfolioShare portfolioShare = portfolioShareRepo.findByPortfolioIdAndShareId(portfolio.getId(), share.getId());

        if (portfolioShare == null) {
            portfolioShare = new PortfolioShare();
            portfolioShare.setId(new PortfolioShareId(portfolio.getId(), share.getId()));
            portfolioShare.setPortfolio(portfolio);
            portfolioShare.setShare(share);
            portfolioShare.setQuantity(0);
        }
        //TO DO : Calculate the quantity of shares  according to the trade value
        //TO DO : Use the MAPPER AND DTOS
        portfolioShare.setQuantity(portfolioShare.getQuantity() + trade.getQuantity());
        portfolioShareRepo.save(portfolioShare);

        return savedTrade;
    }


    @Override
    @Transactional
    public Trade sell(Map<String, Object> tradeRequest) {
        Integer portfolioIdInt = (Integer) tradeRequest.get("portfolioId");
        Long portfolioId = portfolioIdInt.longValue();

        Portfolio portfolio = portfolioRepo.findById(portfolioId)
                .orElseThrow(() -> new IllegalArgumentException("Portfolio not found for id: " + tradeRequest.get("portfolioId")));
        Share share = shareRepo.findBySymbol(tradeRequest.get("shareSymbol").toString())
                .orElseThrow(() -> new IllegalArgumentException("Share not found for symbol: " + tradeRequest.get("shareSymbol").toString()));

        Trade trade = new Trade();
        trade.setPortfolio(portfolio);
        trade.setShare(share);
        trade.setType(TradeType.SELL);
        PortfolioShare currentPortfolioShare= null;
        if (tradeRequest.get("quantity") != null && tradeRequest.get("quantity") != "") {
            if (Double.valueOf(tradeRequest.get("quantity").toString()) < 0) {
                throw new IllegalArgumentException("Quantity cannot be less than 0");
            }
            currentPortfolioShare = portfolioShareRepo.findByPortfolioIdAndShareId(portfolio.getId(), share.getId());
            if(currentPortfolioShare == null || (currentPortfolioShare.getQuantity() < Double.valueOf(tradeRequest.get("quantity").toString()))){
                throw new IllegalArgumentException("Not enough shares to sell");
            }

            trade.setQuantity(Double.valueOf(tradeRequest.get("quantity").toString()));
            trade.setPrice(trade.getQuantity() * share.getPrice());
        }
        else if (tradeRequest.get("price") != null && tradeRequest.get("price") != "") {
            Double price = Double.valueOf(tradeRequest.get("price").toString());
            Double sharePrice = share.getPrice();
            Double tradeQuantity = Math.floor(price / sharePrice);
            trade.setQuantity(tradeQuantity);
            trade.setPrice(tradeQuantity * sharePrice);
        }
        else {
            throw new IllegalArgumentException("Bad request");
        }


        currentPortfolioShare.setQuantity(currentPortfolioShare.getQuantity() - trade.getQuantity());
        portfolioShareRepo.save(currentPortfolioShare);

        return tradeRepo.save(trade);
    }

    @Override
    public List<Trade> getTradesByPortfolioId(Long id) {
        return tradeRepo.findAllByPortfolioId(id);
    }

    @Override
    public List<Trade> getTradesByShareId(Long id) {
        return tradeRepo.findAllByShareId(id);
    }

    @Override
    public List<Trade> getTradesByShareIdAndPortfolioId(Long shareId, Long portfolioId) {
        return tradeRepo.findAllByShareIdAndPortfolioId(shareId, portfolioId);
    }
}