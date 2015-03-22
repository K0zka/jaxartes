package com.github.K0zka.jaxartes.mapping;

import java.util.UUID;

public class UuidMapper implements Mapper<UUID> {
    @Override
    public UUID map(String source) {
        return UUID.fromString(source);
    }
}
