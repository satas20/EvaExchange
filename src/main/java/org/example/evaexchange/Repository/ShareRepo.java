package org.example.evaexchange.Repository;

import org.example.evaexchange.Entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShareRepo extends JpaRepository<Share, Long> {
    Optional<Share> findBySymbol(String symbol);

}
