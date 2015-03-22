package com.github.K0zka.jaxartes.mapping;

public class DoubleMapper implements Mapper<Double> {
    @Override
    public Double map(String source) {
        return Double.valueOf(source);
    }
}
