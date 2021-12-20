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

    public static ErrorIdentifier parse(final String errorCode) throws SyntaxException {
        return new ErrorCodeParser().parse(errorCode);
    }

    /**
     * Possible types of exasol error codes.
     */
    public enum Type {
        E, F, W
    }

    public static class SyntaxException extends Exception {
        public SyntaxException(final String message) {
            super(message);
        }

        public SyntaxException(final String message, final Throwable cause) {
            super(message, cause);
        }
    }

    @Override
    public String toString() {
        return this.type.toString() + "-" + this.getTag() + "-" + this.getIndex();
    }
}
