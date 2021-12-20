package com.exsol.errorcodemodel;

import lombok.Data;

/**
 * This class represents an Exasol error code (e.g: E-EX-1). Each tag has a type (eg: E), a tag (e.g: EX) and an index
 * (e.g: 1).
 */
@Data
public class ErrorIdentifier {
    private final Type type;
    private final String tag;
    private final int index;

    /**
     * Parse an error identifier string.
     * <p>
     * Example: E-TEST-1
     * </p>
     * 
     * @param errorCode error identifier string
     * @return parsed {@link ErrorIdentifier}
     * @throws SyntaxException if the code has an invalid syntax
     */
    public static ErrorIdentifier parse(final String errorCode) throws SyntaxException {
        return new ErrorCodeParser().parse(errorCode);
    }

    /**
     * Possible types of exasol error codes.
     */
    public enum Type {
        /** Error */
        E,
        /** Fatal */
        F,
        /** Warning */
        W
    }

    /**
     * Exception that is thrown on syntax errors in the {@link ErrorIdentifier}.
     */
    public static class SyntaxException extends Exception {
        /**
         * Create a new instance of {@link SyntaxException}.
         *
         * @param message exception message
         */
        public SyntaxException(final String message) {
            super(message);
        }

        /**
         * Create a new instance of {@link SyntaxException}.
         * 
         * @param message exception message
         * @param cause   causing exception
         */
        public SyntaxException(final String message, final Throwable cause) {
            super(message, cause);
        }
    }

    @Override
    public String toString() {
        return this.type.toString() + "-" + this.getTag() + "-" + this.getIndex();
    }
}
