package org.example.evaexchange.Controller;

import jakarta.validation.Valid;
import org.example.evaexchange.Dto.TradeDto;
import org.example.evaexchange.Entity.*;
import org.example.evaexchange.Service.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/trades")
public class TradeController {
    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    //TO DO: implement controller
    @PostMapping("/buy")
    public ResponseEntity<Share> buyShare(@RequestBody @Valid TradeDto tradeDTO) {
        //Share share = tradeService.buyShare(tradeDTO.getSymbol(), tradeDTO.getPrice());
        Share share = new Share();
        return new ResponseEntity<>(share, HttpStatus.CREATED);
    }

    @PostMapping("/sell")
    public ResponseEntity<Void> sellShare(@RequestBody @Valid TradeDto tradeDTO) {
        //tradeService.sellShare(tradeDTO.getSymbol());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}