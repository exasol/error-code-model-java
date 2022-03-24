package com.exsol.errorcodemodel;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class ErrorCodeReportWriterTest {

    private static final ErrorMessageDeclaration AN_ERROR_CODE = ErrorMessageDeclaration.builder().identifier(//
            "E-Test-1")//
            .prependMessage("My message.")//
            .addParameter("TEST", "A parameter")//
            .setPosition("test.java", 2).build();

    @Test
    void testWriteReport(@TempDir final Path tempDir) throws IOException {
        final ErrorCodeReport report = new ErrorCodeReport("my-demo-project", "1.2.3", List.of(AN_ERROR_CODE));
        final Path reportFile = tempDir.resolve("error_code_report.json");
        new ErrorCodeReportWriter().writeReport(report, reportFile);
        assertThat(Files.readString(reportFile), equalTo(
                "{\"$schema\":\"https://schemas.exasol.com/error_code_report-1.0.0.json\",\"projectName\":\"my-demo-project\",\"projectVersion\":\"1.2.3\",\"errorCodes\":[{\"identifier\":\"E-Test-1\",\"message\":\"My message.\",\"messagePlaceholders\":[{\"placeholder\":\"TEST\",\"description\":\"A parameter\"}],\"sourceFile\":\"test.java\",\"sourceLine\":2,\"mitigations\":[]}]}"));
    }

    @Test
    void testWriteWithoutNameAndVersion(@TempDir final Path tempDir) throws IOException {
        final ErrorCodeReport report = new ErrorCodeReport(null, null, List.of(AN_ERROR_CODE));
        final Path reportFile = tempDir.resolve("error_code_report.json");
        new ErrorCodeReportWriter().writeReport(report, reportFile);
        assertThat(Files.readString(reportFile), equalTo(
                "{\"$schema\":\"https://schemas.exasol.com/error_code_report-1.0.0.json\",\"errorCodes\":[{\"identifier\":\"E-Test-1\",\"message\":\"My message.\",\"messagePlaceholders\":[{\"placeholder\":\"TEST\",\"description\":\"A parameter\"}],\"sourceFile\":\"test.java\",\"sourceLine\":2,\"mitigations\":[]}]}"));
    }
}