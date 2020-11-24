package org.example.foreignKeySubstitution.exception;

public class ForeignKeyNoExistException extends RuntimeException{

    /**
     * Constructor for ForeignKeyNoExistException.
     * @param msg the detail message
     */
    public ForeignKeyNoExistException(String msg) {
        super(msg);
    }

    /**
     * Constructor for ForeignKeyNoExistException.
     * @param msg the detail message
     * @param cause the root cause from the data access API in use
     */
    public ForeignKeyNoExistException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
