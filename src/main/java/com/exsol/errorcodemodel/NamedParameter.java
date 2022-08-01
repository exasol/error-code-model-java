package com.exsol.errorcodemodel;

import java.util.Objects;

/**
 * This class represents a parameter of an error code invocation.
 */
public final class NamedParameter {
    private final String name;
    private final String description;

    /**
     * Create a new instance of a {@link NamedParameter}.
     * @param name name of the parameter
     * @param description parameter description
     */
    public NamedParameter(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Get the parameter name.
     * @return parameter name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the parameter description.
     * @return parameter description
     */
    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        final NamedParameter that = (NamedParameter) other;
        return name.equals(that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
}
