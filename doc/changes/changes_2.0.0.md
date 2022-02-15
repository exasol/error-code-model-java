# Error Code Model Java 2.0.0, released 2022-02-??

Code name: Enforce stricter error code format

## Summary

**This is a breaking change:** Before, error codes with more than one module name (e.g. `E-EXA-MOD1-MOD2-42`) where allowed. To unify error codes we limit this now to at most one module name (e.g. `E-EXA-MOD1-42`) as specified in the [spec](https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/doc/requirements.md#verify-error-identifier).

```
error-identifier = severity "-" project-short-tag [ "-" module-short-tag ] "-" error-number
severity = ( "F" / "E" / "W" )
project-short-tag = ALPHA 1*10ALPHANUM
module-short-tag = ALPHA 1*10ALPHANUM
error-number = 1*5ALPHANUM
```

## Features

* #9: Added stricter code format validation

## Dependency Updates

### Test Dependency Updates

* Updated `nl.jqno.equalsverifier:equalsverifier:3.8.1` to `3.9`

### Plugin Dependency Updates

* Updated `io.github.zlika:reproducible-build-maven-plugin:0.13` to `0.15`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.8.1` to `3.10.0`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.0.0-M3` to `3.0.0`
* Updated `org.apache.maven.plugins:maven-gpg-plugin:1.6` to `3.0.1`
* Updated `org.apache.maven.plugins:maven-javadoc-plugin:3.3.1` to `3.3.2`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.7` to `2.9.0`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.5` to `0.8.7`
* Updated `org.sonatype.ossindex.maven:ossindex-maven-plugin:3.1.0` to `3.2.0`
* Updated `org.sonatype.plugins:nexus-staging-maven-plugin:1.6.8` to `1.6.10`
