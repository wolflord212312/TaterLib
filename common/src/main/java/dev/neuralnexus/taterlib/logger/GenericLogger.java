package dev.neuralnexus.taterlib.logger;

import java.util.logging.Logger;

/** A generic implementation of the AbstractLogger interface. */
public class GenericLogger implements AbstractLogger {
    private final Logger logger;

    public GenericLogger(String PROJECT_ID) {
        this.logger = Logger.getLogger(PROJECT_ID);
    }

    /** {@inheritDoc} */
    @Override
    public void info(String message) {
        this.logger.info(message);
    }

    /** {@inheritDoc} */
    @Override
    public void warn(String message) {
        this.logger.warning(message);
    }

    /** {@inheritDoc} */
    @Override
    public void error(String message) {
        this.logger.severe(message);
    }

    /** {@inheritDoc} */
    @Override
    public void debug(String message) {
        this.logger.fine(message);
    }

    /** {@inheritDoc} */
    @Override
    public void trace(String message) {
        this.logger.finest(message);
    }

    /** {@inheritDoc} */
    @Override
    public void fatal(String message) {
        this.logger.severe(message);
    }
}