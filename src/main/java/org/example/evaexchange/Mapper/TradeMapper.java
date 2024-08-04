package org.example.evaexchange.Mapper;

import org.example.evaexchange.Dto.TradeDto;
import org.example.evaexchange.Entity.Portfolio;
import org.example.evaexchange.Entity.Share;
import org.example.evaexchange.Entity.Trade;
import org.example.evaexchange.Repository.PortfolioRepo;
import org.example.evaexchange.Repository.ShareRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TradeMapper {

    @Autowired
    private PortfolioRepo portfolioRepository;
    @Autowired
    private ShareRepo shareRepo;

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
        return portfolioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Portfolio not found for id: " + id));
    }

    private Share fetchShareById(Long id) {
        return shareRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Share not found for id: " + id));
    }
}