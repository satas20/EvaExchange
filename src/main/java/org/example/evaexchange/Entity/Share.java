package org.example.evaexchange.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;
@Getter
@Setter
@Entity
public class Share {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 3)
    private String symbol;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @OneToMany(mappedBy = "share")
    private Set<PortfolioShare> portfolioShares;

    @OneToMany(mappedBy = "share")
    private Set<Trade> trades;


}