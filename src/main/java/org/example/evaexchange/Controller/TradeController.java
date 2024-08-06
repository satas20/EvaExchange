package org.example.evaexchange.Controller;

import jakarta.validation.Valid;
import org.example.evaexchange.Dto.TradeDto;
import org.example.evaexchange.Entity.*;
import org.example.evaexchange.Mapper.TradeMapper;
import org.example.evaexchange.Service.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/trades")
public class TradeController {
    private final TradeMapper tradeMapper;
    private final TradeService tradeService;
    private final ShareService shareService;

    public TradeController(TradeMapper tradeMapper, TradeService tradeService, ShareService shareService) {
        this.tradeMapper = tradeMapper;
        this.tradeService = tradeService;
        this.shareService = shareService;
    }

    @PostMapping("/buy")
    public ResponseEntity<String> buyShare(@RequestBody @Valid Map<String, Object> tradeRequest) {
        Trade trade;
        try {

            trade = tradeService.buy(tradeRequest);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>("Buy Trade created, Share: " + trade.getShare().getSymbol() + " Single Share Price: " + trade.getShare().getPrice() + " Buy amount " + trade.getQuantity() + " Total Trade Price: " + trade.getPrice(), HttpStatus.CREATED);
    }

    @PostMapping("/sell")
    public ResponseEntity<String> sellShare(@RequestBody @Valid Map<String, Object> tradeRequest) {
        Trade trade ;
        try {

            trade =tradeService.sell(tradeRequest);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<>("Sell Trade created, Share: " + trade.getShare().getSymbol() + " Single Share Price: " + trade.getShare().getPrice() + " Sell amount " + trade.getQuantity() + " Total Trade Price: " + trade.getPrice(), HttpStatus.CREATED);
    }


}