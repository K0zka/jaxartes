package com.github.K0zka.jaxartes.mapping;

import java.math.BigInteger;

public class BigIntegerMapper implements Mapper<BigInteger> {
    @Override
    public BigInteger map(String source) {
        return new BigInteger(source);
    }
}
