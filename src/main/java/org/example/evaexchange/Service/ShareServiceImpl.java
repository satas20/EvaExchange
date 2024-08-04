package org.example.evaexchange.Service;

import org.example.evaexchange.Entity.Share;
import org.example.evaexchange.Repository.ShareRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void deleteShare(Long id) {
        shareRepo.deleteById(id);
    }
}