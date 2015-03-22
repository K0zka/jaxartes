package com.github.K0zka.jaxartes.mapping;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * A registry for parameter mappers
 */
public class Mappers {

    @SuppressWarnings("serial")
	final static Map<Class<?>, Mapper<?>> mappers = Collections.<Class<?>, Mapper<?>>unmodifiableMap(new HashMap<Class<?>, Mapper<?>>() {
        {
            put(String.class, new StringMapper());
            put(UUID.class, new UuidMapper());
            put(BigDecimal.class, new BigDecimalMapper());
            put(BigInteger.class, new BigIntegerMapper());

            put(Long.TYPE, new LongMapper());
            put(Long.class, new LongMapper());
            put(Boolean.class, new BooleanMapper());
            put(Boolean.TYPE, new BooleanMapper());
            put(Double.class, new DoubleMapper());
            put(Double.TYPE, new DoubleMapper());
            put(Integer.class, new IntegerMapper());
            put(Integer.TYPE, new IntegerMapper());
            put(Float.class, new FloatMapper());
            put(Float.TYPE, new FloatMapper());
            put(Byte.class, new ByteMapper());
            put(Byte.TYPE, new ByteMapper());
            put(Character.class, new CharMapper());
            put(Character.TYPE, new CharMapper());
        }
    });

    /**
     * Convert the source string to a target java type.
     * @param clazz		type of the java object expected
     * @param source	the source string
     * @return	the java object converted from the source string
     */
    public static <T> T mapTo(final Class<T> clazz, final String source) {
        @SuppressWarnings("unchecked")
		final Mapper<T> mapper = (Mapper<T>) mappers.get(clazz);
        if (mapper == null) {
            throw new IllegalArgumentException("No mapper for class " + clazz);
        }
        return mapper.map(source);
    }
}
