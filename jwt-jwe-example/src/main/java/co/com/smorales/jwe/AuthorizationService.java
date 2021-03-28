package co.com.smorales.jwe;

import java.text.ParseException;

public interface AuthorizationService {

    String obtainAccessToken(String sessionId);

    String verify(String jwt) throws ParseException, RejectedJwtException;

}
