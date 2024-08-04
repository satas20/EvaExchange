package org.example.evaexchange.Service;

import org.example.evaexchange.Entity.Trade;

import java.util.List;

public interface TradeService {
    List<Trade> getAllTrades();
    Trade getTradeById(Long id);
    Trade createTrade(Trade trade);
    Trade updateTrade(Long id, Trade trade);
    void deleteTrade(Long id);
    Trade buy(Trade trade);
    Trade sell(Trade trade);
}