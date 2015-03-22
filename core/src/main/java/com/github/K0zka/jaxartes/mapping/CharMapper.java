package com.github.K0zka.jaxartes.mapping;

public class CharMapper implements Mapper<Character> {
    @Override
    public Character map(String source) {
        return source.charAt(0);
    }
}
