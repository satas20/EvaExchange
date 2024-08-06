package org.example.evaexchange.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @MapsId("portfolioId")
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    @ManyToOne
    @MapsId("shareId")
    @JoinColumn(name = "share_id")
    private Share share;

    private double quantity;

}