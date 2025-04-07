package org.example.repositories;

import org.example.model.ExRate;
import org.example.util.BaseCurrencySupplier;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public abstract class ExRateRepositoryBase implements ExRateRepository {

    private final List<ExRate> exRates = new ArrayList<>();
    private final BaseCurrencySupplier baseCurrencySupplier;

    protected ExRateRepositoryBase(BaseCurrencySupplier baseCurrencySupplier) {
        this.baseCurrencySupplier = baseCurrencySupplier;
    }

    @Override
    public Optional<ExRate> findExRate(String currencyCode) {
        return exRates
                .stream()
                .filter(exRate -> Objects.equals(currencyCode, exRate.currencyCode()))
                .findAny();
    }

    protected abstract void init();

    protected void addExRates(Collection<ExRate> exRates) {
        this.exRates.addAll(exRates);
    }

    protected void addBaseCurrencyRate() {
        this.exRates.add(new ExRate(
                baseCurrencySupplier.get(),
                BigDecimal.ONE.setScale(5, RoundingMode.CEILING))
        );
    }

    protected ExRate asExRate(String s) {
        String[] line = s.split(",");
        return ExRate.asRate(line[0].trim(), line[1].trim());
    }
}
