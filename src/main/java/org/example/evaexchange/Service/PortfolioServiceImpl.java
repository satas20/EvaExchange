package org.example.evaexchange.Service;

import org.example.evaexchange.Entity.Portfolio;
import org.example.evaexchange.Entity.PortfolioShare;
import org.example.evaexchange.Entity.Trade;
import org.example.evaexchange.Repository.PortfolioRepo;
import org.example.evaexchange.Repository.PortfolioShareRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    @Autowired
    private PortfolioRepo portfolioRepo;

    @Autowired
    PortfolioShareRepo portfolioShareRepo;

    @Autowired
    TradeService tradeService;

    @Autowired
    ShareService shareService;

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

    @Override
    public HashMap<String,Object>  getPortfolioSummary(Long id) {
        HashMap<String, Object> summaryMap=  new HashMap<>();
        List<PortfolioShare> portfolioShareshares= portfolioShareRepo.findByPortfolioId(id);
        Double totalValue =0.0;
        double totalProfit =0.0;
       for(PortfolioShare portfolioShareshare:portfolioShareshares){
           //I am going to calculate the profit of each share
           List<Trade>tradeList    =tradeService.getTradesByShareIdAndPortfolioId(portfolioShareshare.getShare().getId(),id);

              Double profit =0.0;
              for(Trade trade:tradeList){
                if(trade.getType().toString().equals("BUY")){
                    profit += trade.getShare().getPrice()-trade.getPrice();

                }
                else if(trade.getType().toString().equals("SELL")){
                    profit += trade.getPrice()- trade.getShare().getPrice();
                }
              }
              totalProfit += profit;
           Double shareValue = portfolioShareshare.getQuantity()*(portfolioShareshare.getShare().getPrice());
           totalValue += shareValue;
            Map<String, Object> shareMap = new HashMap<>();
            shareMap.put("TotalShare Value", String.format("%.2f", shareValue));
            shareMap.put("Current Share Price", String.format("%.2f", portfolioShareshare.getShare().getPrice()));
            shareMap.put("Profit",String.format("%.2f", profit));
            shareMap.put("Quantity", portfolioShareshare.getQuantity());

            summaryMap.put(portfolioShareshare.getShare().getSymbol(), shareMap);
       }

        summaryMap.put("totalValue",String.format("%.2f", totalValue) );
        summaryMap.put("totalProfit",String.format("%.2f", totalProfit) );
        return  summaryMap;
    }
}