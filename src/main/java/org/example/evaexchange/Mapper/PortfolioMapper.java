package org.example.evaexchange.Mapper;

import org.example.evaexchange.Dto.PortfolioDto;
import org.example.evaexchange.Entity.Portfolio;
import org.springframework.stereotype.Component;

@Component
public class PortfolioMapper {

    public PortfolioDto toDto(Portfolio portfolio) {
        PortfolioDto dto = new PortfolioDto();
        dto.setId(portfolio.getId());
        dto.setName(portfolio.getName());
        return dto;
    }

    public Portfolio toEntity(PortfolioDto dto) {
        Portfolio portfolio = new Portfolio();
        portfolio.setId(dto.getId());
        portfolio.setName(dto.getName());
        return portfolio;
    }
}