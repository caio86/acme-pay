package br.com.acmepay.application.domain.models;

import java.math.BigDecimal;

interface CardLimitConstants {

    BigDecimal M1 = BigDecimal.valueOf(0.1);
    BigDecimal M2 = BigDecimal.valueOf(0.3);
    BigDecimal M3 = BigDecimal.valueOf(0.5);

}
