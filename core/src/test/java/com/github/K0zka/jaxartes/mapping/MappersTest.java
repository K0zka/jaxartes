package com.github.K0zka.jaxartes.mapping;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class MappersTest {

    final String input;
    final Class target;
    final Object expected;


    public MappersTest(String input, Class target, Object expected) {
        this.input = input;
        this.target = target;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static List<Object[]> parameters() {
        return Arrays.<Object[]>asList(
                new Object[]{"1",Integer.class, 1},
                new Object[]{"blah", String.class, "blah"},
                new Object[]{"true", Boolean.class, true},
                new Object[]{"123.456", BigDecimal.class, new BigDecimal("123.456")}
                );
    }

    @Test
    public void mapTo() {
        Assert.assertEquals(expected, Mappers.mapTo(target, input));
    }

}
