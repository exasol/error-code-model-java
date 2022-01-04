package com.exsol.errorcodemodel;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class ErrorCodeReportReaderTest {

    @Test
    void testReadReport(@TempDir final Path tempDir) throws ErrorCodeReportReader.ReadException {
        final ErrorCodeReport report = new ErrorCodeReport("my-demo-project", "1.2.3",
                List.of(ErrorMessageDeclaration.builder().identifier(//
                        "E-Test-1")//
                        .prependMessage("My message.")//
                        .addParameter("TEST", "A parameter")//
                        .setPosition("test.java", 2).build()));
        final Path reportFile = tempDir.resolve("error_code_report.json");
        new ErrorCodeReportWriter().writeReport(report, reportFile);
        final ErrorCodeReport readReport = new ErrorCodeReportReader().readReport(reportFile);
        assertThat(readReport, equalTo(report));
    }

    @Test
    void testReadMinimalReport(@TempDir final Path tempDir) throws IOException, ErrorCodeReportReader.ReadException {
        final Path reportFile = tempDir.resolve("report.json");
        Files.writeString(reportFile,
                "{\"$schema\":\"https://schemas.exasol.com/error_code_report-1.0.0.json\", \"errorCodes\": [] }");
        final ErrorCodeReport readReport = new ErrorCodeReportReader().readReport(reportFile);
        assertAll(//
                () -> assertThat(readReport.getProjectName(), nullValue()),
                () -> assertThat(readReport.getProjectVersion(), nullValue()),
                () -> assertThat(readReport.getErrorMessageDeclarations(), empty())//
        );
    }

    @Test
    void testReadMinimalErrorCode(@TempDir final Path tempDir) throws IOException, ErrorCodeReportReader.ReadException {
        final Path reportFile = tempDir.resolve("report.json");
        Files.writeString(reportFile,
                "{\"$schema\":\"https://schemas.exasol.com/error_code_report-1.0.0.json\", \"errorCodes\": [{\"identifier\":\"E-TEST-1\"}] }");
        final ErrorCodeReport readReport = new ErrorCodeReportReader().readReport(reportFile);
        final List<ErrorMessageDeclaration> errorMessageDeclarations = readReport.getErrorMessageDeclarations();
        assertAll(//
                () -> assertThat(readReport.getProjectName(), nullValue()),
                () -> assertThat(readReport.getProjectVersion(), nullValue()),
                () -> assertThat(errorMessageDeclarations.get(0).getIdentifier(), equalTo("E-TEST-1"))//
        );
    }
}