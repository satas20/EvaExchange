package org.example.evaexchange.Mapper;

import org.example.evaexchange.Dto.TradeDto;
import org.example.evaexchange.Entity.Portfolio;
import org.example.evaexchange.Entity.Share;
import org.example.evaexchange.Entity.Trade;
import org.example.evaexchange.Repository.PortfolioRepo;
import org.example.evaexchange.Repository.ShareRepo;
import org.example.evaexchange.Service.PortfolioService;
import org.example.evaexchange.Service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TradeMapper {

    private final PortfolioService portfolioService;
    private final ShareService shareService;

    public TradeMapper(PortfolioService portfolioService, ShareService shareService) {
        this.portfolioService = portfolioService;
        this.shareService = shareService;
    }

    public TradeDto toDto(Trade trade) {
        TradeDto dto = new TradeDto();
        dto.setId(trade.getId());
        dto.setPortfolioId(trade.getPortfolio().getId());
        dto.setShareId(trade.getShare().getId());
        dto.setType(trade.getType());
        dto.setQuantity(trade.getQuantity());
        dto.setPrice(trade.getPrice());
        dto.setTimestamp(trade.getTimestamp());
        return dto;
    }

    public Trade toEntity(TradeDto dto) {
        Trade trade = new Trade();
        trade.setId(dto.getId());
        trade.setPortfolio(fetchPortfolioById(dto.getPortfolioId()));
        trade.setShare(fetchShareById(dto.getShareId()));
        trade.setType(dto.getType());
        trade.setQuantity(dto.getQuantity());
        trade.setPrice(dto.getPrice());
        trade.setTimestamp(dto.getTimestamp());
        return trade;
    }

    private Portfolio fetchPortfolioById(Long id) {
        if (portfolioService.getPortfolioById(id)==null) {
            throw new IllegalArgumentException("Portfolio not found for id: " + id);
        }
        return portfolioService.getPortfolioById(id);
    }

    private Share fetchShareById(Long id) {
        if( shareService.getShareById(id) == null) {
            throw new IllegalArgumentException("Share not found for id: " + id);
        }
        return shareService.getShareById(id);
    }
}