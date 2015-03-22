package com.github.K0zka.jaxartes.mapping;

public class FloatMapper implements Mapper<Float> {
    @Override
    public Float map(String source) {
        return Float.valueOf(source);
    }
}
