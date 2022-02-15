package com.exsol.errorcodemodel;

import java.util.regex.Pattern;

import com.exasol.errorreporting.ExaError;

/**
 * This class parses {@link ErrorIdentifier}s from their string representation.
 */
class ErrorCodeParser {
    private static final Pattern ERROR_CODE_PATTERN = Pattern.compile("([^-]+)-([A-Z][A-Z0-9]{0,9}(?:-[A-Z][A-Z0-9]{0,9})?)-(\\d+)");

    /**
     * Read an {@link ErrorIdentifier} from it's string representation.
     *
     * @param errorCodeString error code's string representation (e.g. E-EX-1)
     * @return built {@link ErrorIdentifier}
     * @throws ErrorIdentifier.SyntaxException on syntax errors
     */
    public ErrorIdentifier parse(final String errorCodeString) throws ErrorIdentifier.SyntaxException {
        final var matcher = ERROR_CODE_PATTERN.matcher(errorCodeString);
        if (!matcher.matches()) {
            throw new ErrorIdentifier.SyntaxException(
                    ExaError.messageBuilder("E-ECMOJ-1").message("The error code {{error code}} has an invalid format.")
                            .parameter("error code", errorCodeString)
                            .mitigation("Use a code like 'E-EXA-1' or 'W-EXA-MOD-2', tags can have max. 10 chars.").toString());
        }
        final var errorType = parseErrorType(matcher.group(1), errorCodeString);
        final String errorTag = matcher.group(2);
        final var errorIndex = Integer.parseInt(matcher.group(3));
        return new ErrorIdentifier(errorType, errorTag, errorIndex);
    }

    private ErrorIdentifier.Type parseErrorType(final String errorTypeString, final String errorCode)
            throws ErrorIdentifier.SyntaxException {
        try {
            return ErrorIdentifier.Type.valueOf(errorTypeString);
        } catch (final IllegalArgumentException exception) {
            throw new ErrorIdentifier.SyntaxException(
                    ExaError.messageBuilder("E-ECMOJ-2").message("Illegal error code {{error code}}.")
                            .mitigation("The codes must start with 'W-', 'E-' or 'F-'.")
                            .parameter("error code", errorCode).toString());
        }
    }
}
