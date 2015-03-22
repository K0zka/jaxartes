package com.github.K0zka.jaxartes.resolvers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;

@RunWith(MockitoJUnitRunner.class)
public class RequestBodyParameterResolverTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    ServletInputStream inputStream;

    @Test
    public void resolve() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Mockito.when(request.getInputStream()).thenReturn(inputStream);
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("{\"name\":\"bob\"}".getBytes());
        Mockito.when(inputStream.read()).thenAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                return byteArrayInputStream.read();
            }
        });
        Mockito.when(inputStream.available()).thenAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                return byteArrayInputStream.available();
            }
        });
        Mockito.when(inputStream.read(Matchers.any(byte[].class), Matchers.anyInt(), Matchers.anyInt())).thenAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                return byteArrayInputStream.read(
                        (byte[]) invocation.getArguments()[0],
                        (Integer) invocation.getArguments()[1],
                        (Integer) invocation.getArguments()[2]);
            }
        });
        Foo foo = (Foo) new RequestBodyParameterResolver(mapper).resolve(
                request,
                response,
                null,
                null,
                null,
                Foo.class);
        Assert.assertEquals("bob", foo.getName());
    }

    static class Foo {
        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
