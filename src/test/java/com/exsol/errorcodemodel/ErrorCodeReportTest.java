package com.exsol.errorcodemodel;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class ErrorCodeReportTest {
    @Test
    void testEquals() {
        EqualsVerifier.simple().forClass(ErrorCodeReport.class).verify();
    }
}