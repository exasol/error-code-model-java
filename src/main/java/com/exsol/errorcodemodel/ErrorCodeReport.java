package com.exsol.errorcodemodel;

import java.util.List;
import java.util.Objects;

/**
 * This class represents an error code report as Java object.
 */
public class ErrorCodeReport {
    private final String projectName;
    private final String projectVersion;
    final List<ErrorMessageDeclaration> errorMessageDeclarations;

    /**
     * Create a new instance of an error code report.
     * @param projectName name of the project the error definitions belong to
     * @param projectVersion version of the project
     * @param errorMessageDeclarations error message declarations
     */
    public ErrorCodeReport(final String projectName, final String projectVersion,
                            final List<ErrorMessageDeclaration> errorMessageDeclarations)
    {
        this.projectName = projectName;
        this.projectVersion = projectVersion;
        this.errorMessageDeclarations = errorMessageDeclarations;
    }

    /**
     * Get the name of the project the error code belongs to.
     * @return project name
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Get the version of the project the error code belongs to.
     * @return project version
     */
    public String getProjectVersion() {
        return projectVersion;
    }

    /**
     * Get a list of error message declarations
     * @return error message declarations
     */
    public List<ErrorMessageDeclaration> getErrorMessageDeclarations() {
        return errorMessageDeclarations;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        final ErrorCodeReport that = (ErrorCodeReport) other;
        return Objects.equals(projectName, that.projectName) && Objects.equals(projectVersion, that.projectVersion)
                && Objects.equals(errorMessageDeclarations, that.errorMessageDeclarations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectName, projectVersion, errorMessageDeclarations);
    }
}
