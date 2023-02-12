package com.smorales.log4j2;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        LOGGER.info("Hello world");
        LOGGER.error("Hello world");

        try {
            throw new RuntimeException("This is an unexpected exception");
        } catch (Exception e) {
            LOGGER.error("Error", e);
            LOGGER.error("Error {}", ExceptionUtils.getStackTrace(e));
        }
    }
}
