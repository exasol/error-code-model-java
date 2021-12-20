package com.exsol.errorcodemodel;

import com.exasol.errorreporting.ExaError;

import lombok.Data;

/**
 * This class represents a parameter of an {@link ExaError} invocation.
 */
@Data
public class NamedParameter {
    private final String name;
    private final String description;
}
