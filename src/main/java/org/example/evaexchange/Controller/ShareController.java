package org.example.evaexchange.Controller;

import org.example.evaexchange.Entity.Share;
import org.example.evaexchange.Entity.Trade;
import org.example.evaexchange.Service.ShareService;
import org.example.evaexchange.Service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/shares")
public class ShareController {

    private final ShareService shareService;
    private final TradeService tradeService;

    @Autowired
    public ShareController(ShareService shareService, TradeService tradeService) {
        this.shareService = shareService;
        this.tradeService = tradeService;
    }

    @PutMapping("/{id}/updateSharePrice")
    public ResponseEntity<Share> updateSharePrice(@PathVariable Long id, @RequestParam Double newPrice) {


        Share updatedShare = shareService.updateSharePrice(id, newPrice);
        return new ResponseEntity<>(updatedShare, HttpStatus.OK);

    }
    @GetMapping("/{id}/TradeHistory")
    public ResponseEntity<List<Trade>> getShareHistory(@PathVariable Long id) {
        List<Trade> tradeList=tradeService.getTradesByShareId(id);
        return new ResponseEntity<>(tradeList, HttpStatus.OK);
    }
    @GetMapping("/{id}/getShare")
    public ResponseEntity<Share> getSharePrice(@PathVariable Long id) {
         Share share=shareService.getShareById(id);
        return new ResponseEntity<>(share, HttpStatus.OK);
    }
    @GetMapping("/getShareList")
    public ResponseEntity<List<Share>> getShareList() {
        List<Share> shareList=shareService.getAllShares();
        return new ResponseEntity<>(shareList, HttpStatus.OK);
    }



}