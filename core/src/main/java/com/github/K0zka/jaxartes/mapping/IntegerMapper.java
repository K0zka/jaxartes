package com.github.K0zka.jaxartes.mapping;

public class IntegerMapper implements Mapper<Integer> {
    @Override
    public Integer map(String source) {
        return Integer.valueOf(source);
    }
}
