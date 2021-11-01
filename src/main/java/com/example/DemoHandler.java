package com.example;

import io.micronaut.function.aws.MicronautRequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DemoHandler extends MicronautRequestHandler<Object, String> {

  private static final Logger LOGGER = LoggerFactory.getLogger(DemoHandler.class);

  @Override
  public String execute(final Object input) {
    LOGGER.debug("*** DEMO HANDLER INVOKED !!!");
    return "DEMO HANDLER OK!";
  }
}
