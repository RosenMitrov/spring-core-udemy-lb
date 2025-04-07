package org.example.services.impl;

import org.example.model.ExRate;
import org.example.repositories.ExRateRepository;
import org.example.services.ForexCalcService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class ForexServiceImplService implements ForexCalcService {


    private final List<ExRateRepository> exRateRepositories;

    public ForexServiceImplService(List<ExRateRepository> exRateRepositories) {
        this.exRateRepositories = exRateRepositories;
    }

    @Override
    public boolean isSupported(String srcCurrency,
                               String dstCurrency) {
        return findExRate(srcCurrency, dstCurrency).isPresent();
    }

    @Override
    public BigDecimal convert(String srcCurrency,
                              BigDecimal srcAmount,
                              String dstCurrency) {
        ExRates exRates = findExRate(srcCurrency, dstCurrency)
                .orElseThrow(() -> new IllegalArgumentException(
                        "The conversion between " + srcCurrency + " and " + dstCurrency
                                + " is not possible. Please us isSupport method first."
                ));

        return exRates.dstRate.rate().divide(exRates.srcRate().rate(), RoundingMode.CEILING)
                .multiply(srcAmount);
    }

    private Optional<ExRates> findExRate(String srcCurr,
                                         String dstCurr) {
        ExRate srcRate = null;
        ExRate dstRate = null;

        for (ExRateRepository repository : exRateRepositories) {
            if (srcRate == null) {
                srcRate = repository.findExRate(srcCurr).orElse(null);
            }

            if (dstRate == null) {
                dstRate = repository.findExRate(dstCurr).orElse(null);
            }

            if (srcRate != null && dstRate != null) {
                return Optional.of(new ExRates(srcRate, dstRate));
            }
        }
        return Optional.empty();
    }

    private record ExRates(ExRate srcRate, ExRate dstRate) {
    }
}
