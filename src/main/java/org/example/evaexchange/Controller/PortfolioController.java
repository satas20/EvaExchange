package org.example.evaexchange.Controller;

import lombok.Getter;
import org.example.evaexchange.Entity.Trade;
import org.example.evaexchange.Service.PortfolioService;
import org.example.evaexchange.Service.ShareService;
import org.example.evaexchange.Service.TradeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {
    private final TradeService tradeService;
    private final ShareService shareService;
    private final PortfolioService portfolioService;

    public PortfolioController(TradeService tradeService, ShareService shareService, PortfolioService portfolioService) {
        this.tradeService = tradeService;
        this.shareService = shareService;
        this.portfolioService = portfolioService;
    }

    @GetMapping("/{id}/TradeHistory")
    public ResponseEntity<List<Trade>> getShareHistory(@PathVariable Long id) {
        List<Trade> tradeList=tradeService.getTradesByPortfolioId(id);
        return new ResponseEntity<>(tradeList, HttpStatus.OK);
    }
    @GetMapping("/{id}/Summary")
    public ResponseEntity<Map<String,Object>> getSummary(@PathVariable Long id) {
        Map<String, Object> resultMap = portfolioService.getPortfolioSummary(id);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

}
