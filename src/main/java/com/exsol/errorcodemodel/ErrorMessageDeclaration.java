package com.exsol.errorcodemodel;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * This class represents an error message declaration as you would find it in the Java code.
 */

public final class ErrorMessageDeclaration {
    private static final String NO_SOURCE_FILE = null;
    private static final int NO_SOURCE_LINE = -1;
    private final String identifier;
    private final String message;
    private final String sourceFile;
    private final int line;
    private final String declaringPackage;
    private final List<String> mitigations;
    private final List<NamedParameter> namedParameters;

    private ErrorMessageDeclaration(final Builder builder) {
        this.identifier = builder.identifier;
        this.message = builder.messageBuilder.toString();
        this.sourceFile = builder.sourceFile;
        this.line = builder.line;
        this.declaringPackage = builder.declaringPackage;
        this.mitigations = builder.mitigations;
        this.namedParameters = builder.namedParameters;
    }

    private ErrorMessageDeclaration(final String identifier, final String message, final String sourceFile,
                                    final int line, final String declaringPackage, final List<String> mitigations,
                                    final List<NamedParameter> namedParameters) {
        this.identifier = identifier;
        this.message = message;
        this.sourceFile = sourceFile;
        this.line = line;
        this.declaringPackage = declaringPackage;
        this.mitigations = mitigations;
        this.namedParameters = namedParameters;
    }

    /**
     * Get the error code identifier.
     *
     * @return error code identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Get the error message.
     *
     * @return error message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Get the file this error code is defined in.
     *
     * @return file in which the error code is defined
     */
    public String getSourceFile() {
        return sourceFile;
    }

    /**
     * Get the line on which the error code is defined.
     *
     * @return line on which the error code is defined
     */
    public int getLine() {
        return line;
    }

    /**
     * Get the package in which the error code is defined.
     *
     * @return package in which the error code is defined
     */
    public String getDeclaringPackage() {
        return declaringPackage;
    }

    /**
     * Get the mitigations that users can try to solve this error.
     *
     * @return mitigation for this error
     */
    public List<String> getMitigations() {
        return mitigations;
    }

    /**
     * Get the list of named parameters.
     *
     * @return list of named parameters
     */
    public List<NamedParameter> getNamedParameters() {
        return namedParameters;
    }

    /**
     * Get a copy of this error message declaration without source position (file and line).
     * 
     * @return copy without source position
     */
    public ErrorMessageDeclaration withoutSourcePosition() {
        return new ErrorMessageDeclaration(this.identifier, this.message, NO_SOURCE_FILE, NO_SOURCE_LINE,
                this.declaringPackage, this.mitigations, this.namedParameters);
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        final ErrorMessageDeclaration that = (ErrorMessageDeclaration) other;
        return line == that.line && Objects.equals(identifier, that.identifier) && Objects.equals(message, that.message)
                && Objects.equals(sourceFile, that.sourceFile)
                && Objects.equals(declaringPackage, that.declaringPackage)
                && Objects.equals(mitigations, that.mitigations)
                && Objects.equals(namedParameters, that.namedParameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, message, sourceFile, line, declaringPackage, mitigations, namedParameters);
    }

    /**
     * Create a builder for {@link ErrorMessageDeclaration}.
     * 
     * @return builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder for {@link ErrorMessageDeclaration}.
     */
    public static class Builder {
        private final StringBuilder messageBuilder = new StringBuilder();
        private final List<String> mitigations = new LinkedList<>();
        private final List<NamedParameter> namedParameters = new LinkedList<>();
        private String identifier;
        private String sourceFile = NO_SOURCE_FILE;
        private int line = NO_SOURCE_LINE;
        private String declaringPackage;

        private Builder() {
        }

        /**
         * Set the error identifier.
         *
         * @param identifier error identifier
         * @return self for fluent programming
         */
        public Builder identifier(final String identifier) {
            this.identifier = identifier;
            return this;
        }

        /**
         * Set the position where the error message is declared.
         *
         * @param sourceFile name of the source file relative to the project's root directory
         * @param line       line number
         * @return self for fluent programming
         */
        public Builder setPosition(final String sourceFile, final int line) {
            this.sourceFile = sourceFile;
            this.line = line;
            return this;
        }

        /**
         * Set the declaring java-package.
         *
         * @param declaringPackage declaring java package
         * @return self for fluent programming
         */
        public Builder declaringPackage(final String declaringPackage) {
            this.declaringPackage = declaringPackage;
            return this;
        }

        /**
         * Prepend a message part.
         *
         * @param message message to prepend.
         * @return self for fluent programming
         */
        public Builder prependMessage(final String message) {
            this.messageBuilder.insert(0, message);
            return this;
        }

        /**
         * Prepend a mitigation.
         *
         * @param mitigation mitigation to prepend.
         * @return self for fluent programming
         */
        public Builder prependMitigation(final String mitigation) {
            this.mitigations.add(0, mitigation);
            return this;
        }

        /**
         * Append a mitigation.
         *
         * @param mitigation mitigation to prepend.
         * @return self for fluent programming
         */
        public Builder appendMitigation(final String mitigation) {
            this.mitigations.add(mitigation);
            return this;
        }

        /**
         * Add a parameter.
         *
         * @param name        parameter name
         * @param description description
         * @return self for fluent programming
         */
        public Builder addParameter(final String name, final String description) {
            this.namedParameters.add(new NamedParameter(name, description));
            return this;
        }

        /**
         * Build the {@link ErrorMessageDeclaration}.
         * 
         * @return built {@link ErrorMessageDeclaration}
         */
        public ErrorMessageDeclaration build() {
            return new ErrorMessageDeclaration(this);
        }
    }
}
