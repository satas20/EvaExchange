package org.example.evaexchange.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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


    private Double price;

    @OneToMany(mappedBy = "share")
    @JsonIgnore
    private Set<PortfolioShare> portfolioShares;

    @OneToMany(mappedBy = "share")
    @JsonIgnore
    private Set<Trade> trades;

    public void setPrice(Double price) {
        this.price = BigDecimal.valueOf(price).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}