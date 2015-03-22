package com.github.K0zka.jaxartes.mapping;

public class BooleanMapper implements Mapper<Boolean> {
    @Override
    public Boolean map(String source) {
        return Boolean.valueOf(source);
    }
}
