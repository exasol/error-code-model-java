package com.exsol.errorcodemodel;

import java.util.Objects;

/**
 * This class represents an Exasol error code (e.g: E-EX-1). Each tag has a type (eg: E), a tag (e.g: EX) and an index
 * (e.g: 1).
 */
public final class ErrorIdentifier {
    private final Type type;
    private final String tag;
    private final int index;

    /**
     * Parse an error identifier string.
     * <p>
     * Example: {@code E-TEST-1}
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
     * Create a new instance of an {@link ErrorIdentifier}.
     * @param type type of error (warning, error, fatal)
     * @param tag error tag
     * @param index index number of the error
     */
    public ErrorIdentifier(final Type type, final String tag, final int index) {
        this.type = type;
        this.tag = tag;
        this.index = index;
    }

    /**
     * Get the error type.
     * @return type of the error
     */
    public Type getType() {
        return type;
    }

    /**
     * Get the error code tag.
     * @return error code tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * Get the error code index.
     * @return error code index.
     */
    public int getIndex() {
        return index;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        final ErrorIdentifier that = (ErrorIdentifier) other;
        return index == that.index && type == that.type && Objects.equals(tag, that.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, tag, index);
    }

    /**
     * Possible types of Exasol error codes.
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
        private static final long serialVersionUID = -4437252433544068436L;

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
        return this.type.toString() + "-" + this.tag + "-" + this.index;
    }
}
