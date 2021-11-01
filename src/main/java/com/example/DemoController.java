package com.example;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/demo")
public class DemoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);

    @Get
    public String get() {
        LOGGER.debug("Debug message");
        LOGGER.info("Info message");
        LOGGER.error("Error message");

        return "DEMO OK!";
    }
}
