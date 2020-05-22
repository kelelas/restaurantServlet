package com.kelelas.model.exception;

import org.apache.log4j.Logger;

public class DBException extends RuntimeException {
    private static final Logger logger = Logger.getLogger(DBException.class);

    public DBException() {
        logger.error("DB Exception");
    }

    public DBException(String message) {
        super(message);
        logger.error(message);
    }

    public DBException(Throwable cause) {
        super(cause);
        logger.error(cause.getStackTrace());
    }
}
