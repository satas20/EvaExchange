package org.example.evaexchange.Repository;

import org.example.evaexchange.Entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TradeRepo extends JpaRepository<Trade, Long> {
    List<Trade> findAllByPortfolioId(Long portfolioId);
    Trade findByPortfolioIdAndShareId(Long portfolioId, Long shareId);
    void deleteByPortfolioIdAndShareId(Long portfolioId, Long shareId);

    @Query("SELECT SUM(t.quantity) FROM Trade t WHERE t.portfolio.id = :portfolioId AND t.share.id = :shareId AND t.type = 'BUY'")
    int findTotalQuantityByPortfolioAndShare(Long portfolioId, Long shareId);
}