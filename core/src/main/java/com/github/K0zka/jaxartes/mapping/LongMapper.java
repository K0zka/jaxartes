package com.github.K0zka.jaxartes.mapping;

public class LongMapper implements Mapper<Long> {
    @Override
    public Long map(String source) {
        return Long.valueOf(source);
    }
}
