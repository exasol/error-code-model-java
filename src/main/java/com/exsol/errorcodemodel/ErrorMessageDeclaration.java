package com.exsol.errorcodemodel;

import java.util.LinkedList;
import java.util.List;

import lombok.*;

/**
 * This class represents declaration
 */
@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorMessageDeclaration {
    private final String identifier;
    private final String message;
    @With(AccessLevel.PRIVATE)
    private final String sourceFile;
    @With(AccessLevel.PRIVATE)
    private final int line;
    private final String declaringPackage;
    private final List<String> mitigations;
    private final List<NamedParameter> namedParameters;

    private ErrorMessageDeclaration(final Builder builder) {
        this.identifier = builder.identifier;
        this.message = builder.messageBuilder.toString();
        this.sourceFile = builder.sourceFile;
        this.declaringPackage = builder.declaringPackage;
        this.line = builder.line;
        this.mitigations = builder.mitigations;
        this.namedParameters = builder.namedParameters;
    }

    /**
     * Get a copy of this error message declaration without source position (file and line).
     * 
     * @return copy without source position
     */
    public ErrorMessageDeclaration withoutSourcePosition() {
        return this.withSourceFile(null).withLine(-1);
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
        private String sourceFile;
        private int line = -1;
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
         * @param line       linux number
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
