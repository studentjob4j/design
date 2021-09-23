package ru.job4j.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple Loggin Facade 4 Java
 * @author Shegai Evgenii
 * @since 23.09.2021
 * @version 1.0
 */

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
    }
}
