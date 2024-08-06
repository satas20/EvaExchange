package org.example.evaexchange.Service;

import org.example.evaexchange.Entity.Portfolio;

import java.util.List;
import java.util.Map;

public interface PortfolioService {
    List<Portfolio> getAllPortfolios();
    Portfolio getPortfolioById(Long id);
    Portfolio createPortfolio(Portfolio portfolio);
    Portfolio updatePortfolio(Long id, Portfolio portfolio);
    void deletePortfolio(Long id);
    Map<String,Object> getPortfolioSummary(Long id);
}
