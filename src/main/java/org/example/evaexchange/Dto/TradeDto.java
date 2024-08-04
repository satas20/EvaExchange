package org.example.evaexchange.Dto;

import lombok.Getter;
import lombok.Setter;
import org.example.evaexchange.Entity.TradeType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class TradeDto {
    private Long id;
    private Long portfolioId;
    private Long shareId;
    private TradeType type;
    private int quantity;
    private BigDecimal price;
    private LocalDateTime timestamp;

}