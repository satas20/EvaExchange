package org.example.evaexchange.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
@Entity
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    @ManyToOne
    @JoinColumn(name = "share_id")
    private Share share;

    @Enumerated(EnumType.STRING)
    private TradeType type;

    private int quantity;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    private LocalDateTime timestamp;

    // Getters and setters
}
