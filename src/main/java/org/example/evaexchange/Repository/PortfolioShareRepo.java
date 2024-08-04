package org.example.evaexchange.Repository;

import org.example.evaexchange.Entity.Portfolio;
import org.example.evaexchange.Entity.PortfolioShare;
import org.example.evaexchange.Entity.PortfolioShareId;
import org.example.evaexchange.Entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PortfolioShareRepo extends JpaRepository<PortfolioShare, PortfolioShareId> {
    List<PortfolioShare> findAllByPortfolioId(Long portfolioId);
    PortfolioShare findByPortfolioIdAndShareId(Long portfolioId, Long shareId);
    void deleteByPortfolioIdAndShareId(Long portfolioId, Long shareId);


}