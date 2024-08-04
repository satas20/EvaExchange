package org.example.evaexchange.Repository;

import org.example.evaexchange.Entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShareRepo extends JpaRepository<Share, Long> {
    Share findBySymbol(String symbol);
}
