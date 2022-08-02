package com.exsol.errorcodemodel;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class ErrorMessageDeclarationTest {
    @Test
    void test() {
        EqualsVerifier.simple().forClass(ErrorMessageDeclaration.class).verify();
    }

    @Test
    void testWithoutSourcePosition() {
        final ErrorMessageDeclaration declaration = ErrorMessageDeclaration.builder().identifier("E-TEST-1")
                .prependMessage("my message").setPosition("test.java", 12).build();
        final ErrorMessageDeclaration result = declaration.withoutSourcePosition();
        assertAll(//
                () -> assertThat(result.getSourceFile(), nullValue()), //
                () -> assertThat(result.getLine(), equalTo(-1)), //
                () -> assertThat(result.getIdentifier(), equalTo("E-TEST-1")),
                () -> assertThat(result.getMessage(), equalTo("my message"))//
        );
    }

    @Test
    void testEquals() {
        EqualsVerifier.simple().forClass(ErrorMessageDeclaration.class).verify();
    }
}
