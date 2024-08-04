package org.example.evaexchange.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PortfolioShare {
    @EmbeddedId
    private PortfolioShareId id;

    @ManyToOne
    @MapsId("portfolioId")
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    @ManyToOne
    @MapsId("shareId")
    @JoinColumn(name = "share_id")
    private Share share;

    private int quantity;

}