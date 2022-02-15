package com.exsol.errorcodemodel;

import java.util.List;

import lombok.Data;
import lombok.With;

/**
 * This class represents an error-code-report in Java.
 */
@Data
public class ErrorCodeReport {
    /** Project name */
    @With
    private final String projectName;

    /** Project version */
    @With
    private final String projectVersion;

    final List<ErrorMessageDeclaration> errorMessageDeclarations;
}
