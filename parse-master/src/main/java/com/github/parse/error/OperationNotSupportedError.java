
package com.github.parse.error;



public class OperationNotSupportedError extends Error {

    private static final long serialVersionUID = -5902785674999190917L;

    public OperationNotSupportedError () {
        super ();
    }

    public OperationNotSupportedError (final String message) {
        super (message);
    }

    public OperationNotSupportedError (final String message, final Throwable cause) {
        super (message, cause);
    }
}
