package org.example.evaexchange.Service;

import org.example.evaexchange.Entity.Trade;

import java.util.List;
import java.util.Map;

public interface TradeService {
    List<Trade> getAllTrades();
    Trade getTradeById(Long id);
    Trade createTrade(Trade trade);
    Trade updateTrade(Long id, Trade trade);
    void deleteTrade(Long id);
    Trade buy(Map<String,Object> tradeRequest);
    Trade sell(Map<String,Object> tradeRequest);
    List<Trade> getTradesByPortfolioId(Long id);
    List<Trade> getTradesByShareId(Long id);
    List<Trade> getTradesByShareIdAndPortfolioId(Long shareId, Long portfolioId);
}