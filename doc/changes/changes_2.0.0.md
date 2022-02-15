# Error Code Model Java 2.0.0, released 2022-02-15

Code name: Enforce stricter error code format

## Summary

**This is a breaking change:** Before, error codes with more than one module name (e.g. `E-EXA-MOD1-MOD2-42`) where allowed. To unify error codes we limit this now to at most one module name (e.g. `E-EXA-MOD1-42`) as specified in the [spec](https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/doc/requirements.md#verify-error-identifier). Tags can now have at most 10 characters and the severity (`F`, `W`, `E`) is now optional, defaulting to `E`. This allows using error codes like `SQL-1234`. See the ABNF grammar in the [readme](../../README.md).

## Features

* #9: Added stricter code format validation

## Dependency Updates

### Test Dependency Updates

* Updated `nl.jqno.equalsverifier:equalsverifier:3.8.1` to `3.9`

### Plugin Dependency Updates

* Updated `io.github.zlika:reproducible-build-maven-plugin:0.13` to `0.15`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.8.1` to `3.10.0`
* Updated `org.apache.maven.plugins:maven-deploy-plugin:2.7` to `3.0.0-M2`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.0.0-M3` to `3.0.0`
* Updated `org.apache.maven.plugins:maven-gpg-plugin:1.6` to `3.0.1`
* Updated `org.apache.maven.plugins:maven-javadoc-plugin:3.3.1` to `3.3.2`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.7` to `2.9.0`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.5` to `0.8.7`
* Updated `org.sonatype.ossindex.maven:ossindex-maven-plugin:3.1.0` to `3.2.0`
