package org.example.evaexchange.Service;

import org.example.evaexchange.Entity.PortfolioShare;
import org.example.evaexchange.Entity.PortfolioShareId;
import java.util.List;

public interface PortfolioShareService {
    List<PortfolioShare> getAllPortfolioShares();
    PortfolioShare getPortfolioShareById(PortfolioShareId id);
    PortfolioShare createPortfolioShare(PortfolioShare portfolioShare);
    PortfolioShare updatePortfolioShare(PortfolioShareId id, PortfolioShare portfolioShare);
    void deletePortfolioShare(PortfolioShareId id);
}