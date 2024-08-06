package org.example.evaexchange.Dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ShareDto {
    private Long id;
    private String symbol;
    private double price;

}