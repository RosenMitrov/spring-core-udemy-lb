package org.example.repositories.impl;

import jakarta.annotation.PostConstruct;
import org.example.repositories.ExRateRepositoryBase;
import org.example.util.BaseCurrencySupplier;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

import static org.example.util.BaseCurrencySupplier.BASE_CURRENCY_SUPPLIER;

@Repository
public class CsvExRateRepository extends ExRateRepositoryBase {

    protected CsvExRateRepository(@Qualifier(BASE_CURRENCY_SUPPLIER) BaseCurrencySupplier baseCurrencySupplier) {
        super(baseCurrencySupplier);
    }

    @PostConstruct
    @Override
    protected void init() {
        super.addExRates(new BufferedReader(new InputStreamReader(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("currencies.csv"))))
                .lines()
                .map(super::asExRate)
                .toList());

        super.addBaseCurrencyRate();
    }
}
