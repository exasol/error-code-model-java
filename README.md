# Error Code Model for Java

[![Build Status](https://github.com/exasol/error-code-model-java/actions/workflows/ci-build.yml/badge.svg)](https://github.com/exasol/error-code-model-java/actions/workflows/ci-build.yml)
[![Maven Central](https://img.shields.io/maven-central/v/com.exasol/error-code-model-java)](https://search.maven.org/artifact/com.exasol/error-code-model-java)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aerror-code-model-java&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.exasol%3Aerror-code-model-java)

[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aerror-code-model-java&metric=security_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Aerror-code-model-java)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aerror-code-model-java&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Aerror-code-model-java)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aerror-code-model-java&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Aerror-code-model-java)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aerror-code-model-java&metric=sqale_index)](https://sonarcloud.io/dashboard?id=com.exasol%3Aerror-code-model-java)

[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aerror-code-model-java&metric=code_smells)](https://sonarcloud.io/dashboard?id=com.exasol%3Aerror-code-model-java)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aerror-code-model-java&metric=coverage)](https://sonarcloud.io/dashboard?id=com.exasol%3Aerror-code-model-java)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aerror-code-model-java&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=com.exasol%3Aerror-code-model-java)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Aerror-code-model-java&metric=ncloc)](https://sonarcloud.io/dashboard?id=com.exasol%3Aerror-code-model-java)

This repository contains a Java library for describing error message declarations.

The following syntax for error codes is supported ([ABNF](https://en.wikipedia.org/wiki/Augmented_Backus%E2%80%93Naur_form)):

```abnf
error-identifier = [ severity "-" ] project-short-tag [ "-" module-short-tag ] "-" error-number
severity = ( "F" / "E" / "W" )
project-short-tag = ALPHA 1*10ALPHANUM
module-short-tag = ALPHA 1*10ALPHANUM
error-number = 1*5ALPHANUM
```

The `serverity` is optional and defaults to `E`. The following severities are supported:
* `F`: failure
* `E`: error
* `W`: warning

## Additional Information

* JSON Schema: [error_code_report-1.0.0.json](https://schemas.exasol.com/error_code_report-1.0.0.json)
* [Changelog](doc/changes/changelog.md)
* [Dependencies](dependencies.md)
