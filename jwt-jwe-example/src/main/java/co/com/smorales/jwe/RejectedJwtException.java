package co.com.smorales.jwe;

public class RejectedJwtException extends Exception {

    public RejectedJwtException(String message) {
        super(message);
    }

    public RejectedJwtException(String message, Throwable cause) {
        super(message, cause);
    }
}
