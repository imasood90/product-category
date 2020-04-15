package com.example.task.config.base;

import com.consol.citrus.dsl.junit.JUnit4CitrusTestRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.http.message.HttpMessage;
import com.consol.citrus.message.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.io.IOException;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


public abstract class AbstractIT extends JUnit4CitrusTestRunner {

    protected static final String PRODUCT_URL = "/api/product/";

    @Autowired
    protected HttpClient serviceClient;

    @Autowired
    private ResourceLoader resourceLoader;


    protected String readFile(final String fileName) throws IOException {
        return new String(readAllBytes(
                get(resourceLoader.getResource(appendClassPath(fileName)).getURI())));
    }

    private String appendClassPath(final String fileName) {
        return String.format("classpath:%s", fileName);
    }

    protected void getRequest(final String url) {
        send(builder -> builder.endpoint(serviceClient)
                .message(new HttpMessage()
                        .path(url)
                        .method(HttpMethod.GET)
                        .contentType(APPLICATION_JSON_VALUE)
                        .accept(APPLICATION_JSON_VALUE)));
    }

    protected void getRequestQueryParam(final String url,String queryParamString) {
        send(builder -> builder.endpoint(serviceClient)
                .message(new HttpMessage()
                        .path(url)
                        .queryParams(queryParamString)
                        .method(HttpMethod.GET)
                        .contentType(APPLICATION_JSON_VALUE)
                        .accept(APPLICATION_JSON_VALUE)));
    }

    protected void postRequest(final String url, final String payload) {
        send(builder -> builder.endpoint(serviceClient)
                .message(new HttpMessage(payload)
                        .path(url)
                        .method(HttpMethod.POST)
                        .contentType(APPLICATION_JSON_VALUE)
                        .accept(APPLICATION_JSON_VALUE)));
    }


    protected void verifyResponsePayload(final HttpStatus httpStatus, final String payload) {
        receive(builder -> builder.endpoint(serviceClient)
                .message(new HttpMessage()
                        .status(httpStatus))
                .messageType(MessageType.JSON)
                .payload(payload));
    }

    protected void verifyResponse(final HttpStatus httpStatus,final String value) {
        receive(builder -> builder.endpoint(serviceClient)
                .message(new HttpMessage()
                        .status(httpStatus))
                        .messageType(MessageType.JSON)
                        .extractFromPayload("$."+value, "${productIdValue}"));
    }

}
