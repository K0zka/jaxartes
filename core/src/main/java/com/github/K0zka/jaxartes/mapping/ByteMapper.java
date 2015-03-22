package com.github.K0zka.jaxartes.mapping;

public class ByteMapper implements Mapper<Byte> {
    @Override
    public Byte map(String source) {
        return source.getBytes()[0];
    }
}
