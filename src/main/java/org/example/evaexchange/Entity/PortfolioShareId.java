package org.example.evaexchange.Entity;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Embeddable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PortfolioShareId implements Serializable {
    private Long portfolioId;
    private Long shareId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PortfolioShareId that = (PortfolioShareId) o;
        return Objects.equals(portfolioId, that.portfolioId) &&
                Objects.equals(shareId, that.shareId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(portfolioId, shareId);
    }
}