package com.exsol.errorcodemodel;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.exsol.errorcodemodel.ErrorIdentifier.SyntaxException;
import com.exsol.errorcodemodel.ErrorIdentifier.Type;

class ErrorCodeParserTest {
    private static final ErrorCodeParser ERROR_CODE_READER = new ErrorCodeParser();

    @ParameterizedTest
    @CsvSource(value = { //
            "E-EXA-1, E, EXA, 1", //
            "F-EXA-E1-1, F, EXA-E1, 1", //
            "F-EXA-E1-E2-1, F, EXA-E1-E2, 1", //
            "W-EXA-0, W, EXA, 0", //
            "W-exa-5, W, exa, 5"//
    })
    void testValidCodes(final String code, final Type expectedType, final String expectedTag, final int expectedIndex)
            throws SyntaxException {
        assertThat(ERROR_CODE_READER.parse(code),
                equalTo(new ErrorIdentifier(expectedType, expectedTag, expectedIndex)));
    }

    @ParameterizedTest
    @ValueSource(strings = { "E-EXA-X", "E-4", "E--5" })
    void testInvalidSyntax(final String code) {
        final ErrorIdentifier.SyntaxException exception = assertThrows(ErrorIdentifier.SyntaxException.class,
                () -> ERROR_CODE_READER.parse(code));
        assertThat(exception.getMessage(), equalTo("E-ECMOJ-1: The error code '" + code + "' has an invalid format."));
    }

    @ParameterizedTest
    @ValueSource(strings = { "Q-EXA-0", "q-exa-1", "e-exa-0" })
    void testReadWrongType(final String code) {
        final ErrorIdentifier.SyntaxException exception = assertThrows(ErrorIdentifier.SyntaxException.class,
                () -> ERROR_CODE_READER.parse(code));
        assertThat(exception.getMessage(),
                equalTo("E-ECMOJ-2: Illegal error code '" + code + "'. The codes must start with 'W-', 'E-' or 'F-'."));
    }
}
