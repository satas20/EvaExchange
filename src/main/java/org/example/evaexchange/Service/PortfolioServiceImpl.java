package org.example.evaexchange.Service;

import org.example.evaexchange.Entity.Portfolio;
import org.example.evaexchange.Repository.PortfolioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    @Autowired
    private PortfolioRepo portfolioRepo;

    @Override
    public List<Portfolio> getAllPortfolios() {
        return portfolioRepo.findAll();
    }

    @Override
    public Portfolio getPortfolioById(Long id) {
        return portfolioRepo.findById(id).orElse(null);
    }

    @Override
    public Portfolio createPortfolio(Portfolio portfolio) {
        return portfolioRepo.save(portfolio);
    }

    @Override
    public Portfolio updatePortfolio(Long id, Portfolio portfolio) {
        if (portfolioRepo.existsById(id)) {
            portfolio.setId(id);
            return portfolioRepo.save(portfolio);
        }
        return null;
    }

    @Override
    public void deletePortfolio(Long id) {
        portfolioRepo.deleteById(id);
    }
}