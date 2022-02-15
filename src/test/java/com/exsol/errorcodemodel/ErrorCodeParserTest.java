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
                        "W-EXA-0, W, EXA, 0", //
                        "W-EXA1-MOD1-0, W, EXA1-MOD1, 0", //
                        "E-A-6, E, A, 6", //
                        "E-A-B-6, E, A-B, 6", //
                        "E-LONGERRORC-1, E, LONGERRORC, 1", //
                        "E-A-LONGERRORC-1, E, A-LONGERRORC, 1", //
                        "E-SQL-1234, E, SQL, 1234", //
                        "F-SQL-1234, F, SQL, 1234", //
                        "SQL-1234, E, SQL, 1234", //
                        "A-0, E, A, 0", //
        })
        void testValidCodes(final String code, final Type expectedType, final String expectedTag,
                        final int expectedIndex) throws SyntaxException {
                assertThat(ERROR_CODE_READER.parse(code),
                                equalTo(new ErrorIdentifier(expectedType, expectedTag, expectedIndex)));
        }

        @ParameterizedTest
        @ValueSource(strings = { "E-EXA-X", "E-EXA-", "-EXA-1", "--EXA-1", "E-1XA-1", "E-EXA-1MOD-1", "E-TOOLONGERRO-1",
                        "E-EXA-TOOLONGERRO-2", "F-EXA-E1-E2-1", "E-EXA-MOD-MOD-1", "W-lower-2", "W-?CODE-2",
                        "W-CODE?-3", "E--5" })
        void testInvalidSyntax(final String code) {
                final ErrorIdentifier.SyntaxException exception = assertThrows(ErrorIdentifier.SyntaxException.class,
                                () -> ERROR_CODE_READER.parse(code));
                assertThat(exception.getMessage(), equalTo("E-ECMOJ-1: The error code '" + code
                                + "' has an invalid format. Use a code like 'EXA-1', 'E-EXA-1' or 'W-EXA-MOD-2', tags can have max. 10 chars."));
        }

        @ParameterizedTest
        @ValueSource(strings = { "Q-EXA-0", "q-EXA-1", "e-EXA-0" })
        void testReadWrongType(final String code) {
                final ErrorIdentifier.SyntaxException exception = assertThrows(ErrorIdentifier.SyntaxException.class,
                                () -> ERROR_CODE_READER.parse(code));
                assertThat(exception.getMessage(), equalTo("E-ECMOJ-2: Illegal error code '" + code
                                + "'. The codes must start with 'W-', 'E-' or 'F-'."));
        }
}
