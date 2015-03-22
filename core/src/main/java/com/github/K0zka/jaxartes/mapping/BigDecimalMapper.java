package com.github.K0zka.jaxartes.mapping;

import java.math.BigDecimal;

public class BigDecimalMapper implements Mapper<BigDecimal> {
    @Override
    public BigDecimal map(String source) {
        return new BigDecimal(source);
    }
}
