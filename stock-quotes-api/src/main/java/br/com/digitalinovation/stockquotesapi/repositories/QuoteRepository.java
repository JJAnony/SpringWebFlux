package br.com.digitalinovation.stockquotesapi.repositories;

import br.com.digitalinovation.stockquotesapi.entities.Quote;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

    @RestResource(rel = "quotes", path = "quotes")
    List<Quote> findAllBySymbol(@Param("symbol") String symbol, Pageable pageable);

    Optional<Quote> findFirstBySymbolOrderByTimestempDesc(@Param("symbol") String symbol);
}
