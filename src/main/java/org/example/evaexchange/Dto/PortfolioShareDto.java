package org.example.evaexchange.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PortfolioShareDto {
    private Long portfolioId;
    private Long shareId;
    private double quantity;

}