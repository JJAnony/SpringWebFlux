package br.com.digitalinovation.stockquotesapi.utils;

import br.com.digitalinovation.stockquotesapi.entities.Quote;
import br.com.digitalinovation.stockquotesapi.repositories.QuoteRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Log4j2
@Component
@EnableScheduling
public class SchedulerQuote {

    @Autowired
    private QuoteRepository repository;

    @Scheduled(fixedDelay = 2000)
    public void createQuote() {
        repository.findFirstBySymbolOrderByTimestempDesc("TESTE")
                .map(this::generateNewQuote).orElseGet(this::initQuote);
    }

    private Quote initQuote() {
        return repository.save(Quote.builder()
                .symbol("TESTE")
                .openValue(0.22222)
                .closeValue(0.22222)
                .timestemp(new Date())
                .build());
    }

    private Quote generateNewQuote(Quote quote) {
        return repository.save(Quote.builder()
                .symbol(quote.getSymbol())
                .openValue(quote.getOpenValue() * new RandomDataGenerator().nextUniform(-0.10, 0.10))
                .closeValue(quote.getCloseValue() * new RandomDataGenerator().nextUniform(-0.10, 0.10))
                .timestemp(new Date())
                .build());
    }


}
