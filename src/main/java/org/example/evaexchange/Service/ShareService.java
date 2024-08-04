package org.example.evaexchange.Service;

import org.example.evaexchange.Entity.Share;
import java.util.List;

public interface ShareService {
    List<Share> getAllShares();
    Share getShareById(Long id);
    Share createShare(Share share);
    Share updateShare(Long id, Share share);
    void deleteShare(Long id);
}