package org.example.services;

import java.math.BigDecimal;

public interface ForexCalcService {
    boolean isSupported(String srcCurrency, String dstCurrency);

    BigDecimal convert(String srcCurrency, BigDecimal srcAmount, String dstCurrency);
}
