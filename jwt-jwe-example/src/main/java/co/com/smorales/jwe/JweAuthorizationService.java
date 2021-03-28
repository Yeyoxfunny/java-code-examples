package co.com.smorales.jwe;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jose.proc.JWEDecryptionKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTClaimsVerifier;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;

import java.text.ParseException;
import java.util.*;

public class JweAuthorizationService implements AuthorizationService {

    private static final String ISSUER_URL = "https://www.midomain.com";
    private static final Set<String> REQUIRED_CLAIMS = Set.of("sub", "iat", "exp", "jti");

    private final RSAKey serverKey;
    private final JweAuthConfigurationProperties configurationProperties;
    private final ConfigurableJWTProcessor<SecurityContext> jwtProcessor;

    private final JWEAlgorithm algorithm;
    private final EncryptionMethod encryptionMethod;

    public JweAuthorizationService(RSAKey serverKey, JweAuthConfigurationProperties configurationProperties) {
        this.serverKey = serverKey;
        this.algorithm = JWEAlgorithm.parse(configurationProperties.getAlgorithm());
        this.encryptionMethod = EncryptionMethod.parse(configurationProperties.getEncryptionMethod());
        this.configurationProperties = configurationProperties;
        this.jwtProcessor = jwtProcessor(serverKey, this.algorithm, this.encryptionMethod);
    }

    private static ConfigurableJWTProcessor<SecurityContext> jwtProcessor(RSAKey serverKey, JWEAlgorithm algorithm, EncryptionMethod encryptionMethod) {
        JWEDecryptionKeySelector<SecurityContext> jweKeySelector =
                new JWEDecryptionKeySelector<>(algorithm, encryptionMethod, new ImmutableJWKSet<>(new JWKSet(serverKey)));

        DefaultJWTProcessor<SecurityContext> jwtProcessor = new DefaultJWTProcessor<>();
        jwtProcessor.setJWEKeySelector(jweKeySelector);
        jwtProcessor.setJWTClaimsSetVerifier(new DefaultJWTClaimsVerifier<>(
                new JWTClaimsSet.Builder().issuer(ISSUER_URL).build(), REQUIRED_CLAIMS));
        return jwtProcessor;
    }

    @Override
    public String obtainAccessToken(String sessionId) {
        Date now = new Date();
        JWTClaimsSet jwtClaims = new JWTClaimsSet.Builder()
                .issuer(ISSUER_URL)
                // Session ID must be stored in subject claim
                .subject(sessionId)
                .expirationTime(new Date(now.getTime() + configurationProperties.getExpiration().toMillis()))
                .notBeforeTime(now)
                .issueTime(now)
                .jwtID(UUID.randomUUID().toString())
                .build();
        return encryptedJwt(jwtClaims).serialize();
    }

    private EncryptedJWT encryptedJwt(JWTClaimsSet jwtClaims) {
        try {
            JWEHeader header = new JWEHeader(algorithm, encryptionMethod);
            EncryptedJWT jwt = new EncryptedJWT(header, jwtClaims);
            jwt.encrypt(new RSAEncrypter(serverKey.toRSAPublicKey()));
            return jwt;
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String verify(String jwt) throws ParseException, RejectedJwtException {
        try {
            JWTClaimsSet claimsSet = jwtProcessor.process(jwt, null);
            // Session ID must be stored in subject claim
            return claimsSet.getSubject();
        } catch (BadJOSEException e) {
            throw new RejectedJwtException(e.getMessage(), e);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }
}
