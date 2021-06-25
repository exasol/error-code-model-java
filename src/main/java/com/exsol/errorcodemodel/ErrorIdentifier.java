package com.exsol.errorcodemodel;

import java.util.Objects;

/**
 * This class represents an Exasol error code (e.g: E-EX-1). Each tag has a type (eg: E), a tag (e.g: EX) and an index
 * (e.g: 1).
 */
public class ErrorIdentifier {
    private final Type type;
    private final String tag;
    private final int index;

    /**
     * Create a new instance of {@link ErrorIdentifier}.
     * 
     * @param type  error type
     * @param tag   error tag
     * @param index error index
     */
    public ErrorIdentifier(final Type type, final String tag, final int index) {
        this.type = type;
        this.tag = tag;
        this.index = index;
    }

    public static ErrorIdentifier parse(final String errorCode) throws SyntaxException {
        return new ErrorCodeParser().parse(errorCode);
    }

    /**
     * Get the type.
     *
     * @return type
     */
    public Type getType() {
        return this.type;
    }

    /**
     * Get the tag.
     *
     * @return tag
     */
    public String getTag() {
        return this.tag;
    }

    /**
     * Get the index.
     *
     * @return index
     */
    public int getIndex() {
        return this.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.type, this.tag, this.index);
    }

    @Override
    public String toString() {
        return this.type.toString() + "-" + this.getTag() + "-" + this.getIndex();
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ErrorIdentifier)) {
            return false;
        }
        final var errorCode = (ErrorIdentifier) other;
        return this.index == errorCode.index && this.type == errorCode.type && Objects.equals(this.tag, errorCode.tag);
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
}
