# Error Code Model Java 2.1.2, released 2023-05-10

Code name: Replace javax.json with jakarta.json

## Summary

This release replaces JSON API `javax.json` with `jakarta.json` to avoid a failing duplicate check.

## Refactoring

* #22: Replace javax.json with jakarta.json

## Dependency Updates

### Compile Dependency Updates

* Updated `com.exasol:error-reporting-java:0.4.1` to `1.0.1`
* Added `jakarta.json:jakarta.json-api:2.1.1`
* Removed `javax.json:javax.json-api:1.1.4`
* Removed `org.glassfish:javax.json:1.1.4`

### Runtime Dependency Updates

* Added `org.eclipse.parsson:parsson:1.1.1`

### Test Dependency Updates

* Updated `nl.jqno.equalsverifier:equalsverifier:3.10.1` to `3.14.1`
* Removed `org.junit.jupiter:junit-jupiter-engine:5.9.0`
* Updated `org.junit.jupiter:junit-jupiter:5.9.0` to `5.9.3`

### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:1.1.1` to `1.2.3`
* Updated `com.exasol:project-keeper-maven-plugin:2.5.0` to `2.9.7`
* Updated `io.github.zlika:reproducible-build-maven-plugin:0.15` to `0.16`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.10.1` to `3.11.0`
* Updated `org.apache.maven.plugins:maven-deploy-plugin:3.0.0-M1` to `3.1.1`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.0.0` to `3.3.0`
* Updated `org.apache.maven.plugins:maven-javadoc-plugin:3.4.0` to `3.5.0`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.0.0-M5` to `3.0.0`
* Added `org.basepom.maven:duplicate-finder-maven-plugin:1.5.1`
* Updated `org.codehaus.mojo:flatten-maven-plugin:1.2.7` to `1.4.1`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.10.0` to `2.15.0`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.8` to `0.8.9`
