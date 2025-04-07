package org.example.repositories.impl;

import jakarta.annotation.PostConstruct;
import org.example.model.ExRate;
import org.example.repositories.ExRateRepositoryBase;
import org.example.util.BaseCurrencySupplier;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.example.util.BaseCurrencySupplier.BASE_CURRENCY_SUPPLIER;

@Repository
public class InMemoryExRateRepository extends ExRateRepositoryBase {


    protected InMemoryExRateRepository(@Qualifier(BASE_CURRENCY_SUPPLIER) BaseCurrencySupplier baseCurrencySupplier) {
        super(baseCurrencySupplier);
    }

    @PostConstruct
    @Override
    protected void init() {
        super.addExRates(
                List.of(
                        ExRate.asRate("KRW", "18.7332"),
                        ExRate.asRate("MYR", "5.0736"),
                        ExRate.asRate("NZD", "1.7888"),
                        ExRate.asRate("PHP", "60.590"),
                        ExRate.asRate("SGD", "1.4762"),
                        ExRate.asRate("THB", "38.159"),
                        ExRate.asRate("ZAR", "19.7927"),
                        //e.g. 1 EUR is 1.9558 BGN
                        ExRate.asRate("BGN", "1.9558")));

        super.addBaseCurrencyRate();
    }
}
