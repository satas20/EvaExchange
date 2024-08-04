package org.example.evaexchange.Mapper;

import org.example.evaexchange.Dto.PortfolioShareDto;
import org.example.evaexchange.Entity.PortfolioShare;
import org.example.evaexchange.Entity.PortfolioShareId;
import org.springframework.stereotype.Component;

@Component
public class PortfolioShareMapper {

    public PortfolioShareDto toDto(PortfolioShare portfolioShare) {
        PortfolioShareDto dto = new PortfolioShareDto();
        dto.setPortfolioId(portfolioShare.getId().getPortfolioId());
        dto.setShareId(portfolioShare.getId().getShareId());
        dto.setQuantity(portfolioShare.getQuantity());
        return dto;
    }

    public PortfolioShare toEntity(PortfolioShareDto dto) {
        PortfolioShare portfolioShare = new PortfolioShare();
        portfolioShare.setId(new PortfolioShareId(dto.getPortfolioId(), dto.getShareId()));
        portfolioShare.setQuantity(dto.getQuantity());
        return portfolioShare;
    }
}