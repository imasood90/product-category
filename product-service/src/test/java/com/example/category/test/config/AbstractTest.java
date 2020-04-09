package com.example.category.test.config;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Base class for Unit Tests
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWireMock(port = 0)
public abstract class AbstractTest {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Before
    public void before() {
        initMocks(this);
    }
}
