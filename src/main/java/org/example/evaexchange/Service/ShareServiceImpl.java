package org.example.evaexchange.Service;

import org.example.evaexchange.Entity.Share;
import org.example.evaexchange.Repository.ShareRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ShareServiceImpl implements ShareService {

    @Autowired
    private ShareRepo shareRepo;

    @Override
    public List<Share> getAllShares() {
        return shareRepo.findAll();
    }

    @Override
    public Share getShareById(Long id) {
        return shareRepo.findById(id).orElse(null);
    }

    @Override
    public Share createShare(Share share) {
        return shareRepo.save(share);
    }

    @Override
    public Share updateShare(Long id, Share share) {
        if (shareRepo.existsById(id)) {
            share.setId(id);
            return shareRepo.save(share);
        }
        return null;
    }

    @Override
    public Share updateSharePrice(Long id, Double newPrice) {
        Share share = shareRepo.findById(id).orElse(null);
        if (share != null) {
            share.setPrice(newPrice);
            return shareRepo.save(share);
        }
        return null;
    }

    @Override
    public void deleteShare(Long id) {
        shareRepo.deleteById(id);
    }


    private final Random random = new Random();
    @Override
    @Scheduled(fixedRate = 15000) // 15 SN in milliseconds
    public void updateSharePrices() {
        System.out.println("Updating share prices...");
        List<Share> shares = shareRepo.findAll();
        for (Share share : shares) {
            Double newPrice = share.getPrice() + (random.nextDouble() * 10 - 5);
            share.setPrice(newPrice);
            shareRepo.save(share);
        }
    }
}