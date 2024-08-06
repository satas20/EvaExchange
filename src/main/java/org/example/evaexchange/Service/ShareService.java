package org.example.evaexchange.Service;

import org.example.evaexchange.Entity.Share;
import org.springframework.scheduling.annotation.Scheduled;

import java.math.BigDecimal;
import java.util.List;

public interface ShareService {
    List<Share> getAllShares();
    Share getShareById(Long id);
    Share createShare(Share share);
    Share updateShare(Long id, Share share);
    Share updateSharePrice(Long id, Double newPrice);
    void deleteShare(Long id);

    void updateSharePrices();
}