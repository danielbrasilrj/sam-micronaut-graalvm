package com.example;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/second")
public class SecondController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecondController.class);

    @Get
    public String get() {
        LOGGER.debug("Debug message");
        LOGGER.info("Info message");
        LOGGER.error("Error message");

        return "SECOND CONTROLLER OK!";
    }
}
