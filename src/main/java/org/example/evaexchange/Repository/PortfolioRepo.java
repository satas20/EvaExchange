package org.example.evaexchange.Repository;

import org.example.evaexchange.Entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepo extends JpaRepository<Portfolio, Long> {
}